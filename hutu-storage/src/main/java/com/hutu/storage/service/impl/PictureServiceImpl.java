package com.hutu.storage.service.impl;

import cn.hutool.http.GlobalHeaders;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hutu.common.exception.GlobalException;
import com.hutu.storage.constant.PictureConstants;
import com.hutu.storage.service.StorageService;
import com.hutu.storage.vo.FileInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;

/**
 * sm.ms图床实现 api文档： https://doc.sm.ms/#api-Image-Upload
 *
 * @author hutu
 * @date 2020-01-17 18:31
 */
@Service
@Slf4j
public class PictureServiceImpl implements StorageService {

    @Override
    public FileInfoVo upload(File file) {
        FileInfoVo fileInfoVo = new FileInfoVo();
        fileInfoVo.setOriginalFileName(file.getName());
        GlobalHeaders.INSTANCE.header("Authorization", PictureConstants.AUTHORIZATION);
        HashMap<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("smfile", file);
        String result = HttpUtil.post(PictureConstants.SM_MS_URL, paramMap);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (jsonObject.getBool("success")) {
            JSONObject data = (JSONObject) jsonObject.get("data");
            String fileUrl = data.getStr("url");
            String fileName = data.getStr("storename");
            fileInfoVo.setFileName(fileName);
            fileInfoVo.setFilePath(fileUrl);
            log.info("【文件上传至sm.ms】绝对路径：{}", fileUrl);
            return fileInfoVo;
        }
        throw new GlobalException("上传失败");
    }
}
