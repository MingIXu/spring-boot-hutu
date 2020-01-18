package com.hutu.storage.service.impl;

import com.hutu.common.exception.GlobalException;
import com.hutu.storage.service.StorageService;
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

	@Value("${qiniu.bucket}")
	private String bucket;

	private StringMap putPolicy;

	@Autowired
	public QiniuServiceImpl(UploadManager uploadManager, Auth auth) {
		this.uploadManager = uploadManager;
		this.auth = auth;
	}

	/**
	 * 七牛云上传文件
	 *
	 * @param file 文件
	 * @return 七牛上传Response
	 * @throws QiniuException 七牛异常
	 */
	@Override
	public String upload(File file) {
		try {
			Response response = this.uploadManager.put(file, file.getName(), getUploadToken());
			int retry = 0;
			while (response.needRetry() && retry < 3) {
				response = this.uploadManager.put(file, file.getName(), getUploadToken());
				retry++;
			}
			if (response.isOK()) {
				return response.bodyString();
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
		return this.auth.uploadToken(bucket, null, 3600, putPolicy);
	}
}
