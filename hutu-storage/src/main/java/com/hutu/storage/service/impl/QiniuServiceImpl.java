package com.hutu.storage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hutu.common.exception.GlobalException;
import com.hutu.common.utils.StringPool;
import com.hutu.storage.config.QiniuProperties;
import com.hutu.storage.service.StorageService;
import com.hutu.storage.util.StorageUtils;
import com.hutu.storage.vo.FileInfoVo;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 七牛云上传实现(默认上传方式)
 *
 * @author hutu
 * @date 2020-01-17 17:52
 */
@Primary
@Service
@Slf4j
public class QiniuServiceImpl implements StorageService, InitializingBean {

	private final UploadManager uploadManager;

	private final Auth auth;

	private StringMap putPolicy;

	private final QiniuProperties qiniuProperties;
	@Autowired
	public QiniuServiceImpl(UploadManager uploadManager, Auth auth,QiniuProperties qiniuProperties) {
		this.uploadManager = uploadManager;
		this.auth = auth;
		this.qiniuProperties = qiniuProperties;
	}

	/**
	 * 七牛云上传文件
	 *
	 * @param file 文件
	 * @return 七牛上传Response
	 */
	@Override
	public FileInfoVo upload(File file) {
		try {
			Response response = this.uploadManager.put(file, file.getName(), getUploadToken());
			int retry = 0;
			// 重试三次
			while (response.needRetry() && retry < 3) {
				response = this.uploadManager.put(file, StorageUtils.generatorFileName(file.getName()), getUploadToken());
				retry++;
			}
			if (response.isOK()) {
				JSONObject jsonObject = JSON.parseObject(response.bodyString());
				String yunFileName = jsonObject.getString("key");
				String yunFilePath = StrUtil.appendIfMissing(qiniuProperties.getPrefix(), StringPool.SLASH) + yunFileName;
				log.info("文件上传至七牛云】地址 {}",yunFilePath);
				return new FileInfoVo(file.getName(), yunFileName, yunFilePath);
			}
			throw new GlobalException("上传失败");
		} catch (QiniuException e) {
			throw new GlobalException("上传失败", e);
		}
	}

	@Override
	public void afterPropertiesSet() {
		this.putPolicy = new StringMap();
		putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
	}

	/**
	 * 获取上传凭证
	 *
	 * @return 上传凭证
	 */
	private String getUploadToken() {
		return this.auth.uploadToken(qiniuProperties.getBucket(), null, 3600, putPolicy);
	}
}
