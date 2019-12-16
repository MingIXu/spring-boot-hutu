package com.hutu.picture.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hutu.common.entity.R;
import com.hutu.common.utils.StringPool;
import com.hutu.picture.service.IQiNiuService;
import com.hutu.picture.service.SMMSService;
import com.qiniu.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/upload")
public class UploadController {
    @Value("${spring.servlet.multipart.location}")
    private String fileTempPath;

    @Value("${qiniu.prefix}")
    private String prefix;

    private final IQiNiuService qiNiuService;
    @Autowired
    SMMSService smmsService;

    @Autowired
    public UploadController(IQiNiuService qiNiuService) {
        this.qiNiuService = qiNiuService;
    }

    @PostMapping(value = "/local", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R local(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.error(400, "文件内容为空");
        }
        String fileName = file.getOriginalFilename();
        String rawFileName = StrUtil.subBefore(fileName, StringPool.DOT, true);
        String fileType = StrUtil.subAfter(fileName, StringPool.DOT, true);
        String localFilePath = StrUtil.appendIfMissing(fileTempPath, StringPool.SLASH) + rawFileName + StringPool.DASH + DateUtil.current(false) + StringPool.DOT + fileType;
        try {
            file.transferTo(new File(localFilePath));
        } catch (IOException e) {
            log.error("【文件上传至本地】失败，绝对路径：{}", localFilePath);
            return R.error("文件上传失败");
        }

        log.info("【文件上传至本地】绝对路径：{}", localFilePath);
        return R.ok("上传成功").put("fileName", fileName).put("filePath", localFilePath);
    }

    @PostMapping(value = "/yun", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R yun(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.error(400, "文件内容为空");
        }
        String fileName = file.getOriginalFilename();
        String rawFileName = StrUtil.subBefore(fileName, StringPool.DOT, true);
        String fileType = StrUtil.subAfter(fileName, StringPool.DOT, true);
        String localFilePath = StrUtil.appendIfMissing(fileTempPath, StringPool.SLASH) + rawFileName + StringPool.DASH + DateUtil.current(false) + StringPool.DOT + fileType;
        try {
            file.transferTo(new File(localFilePath));
            Response response = qiNiuService.uploadFile(new File(localFilePath));
            if (response.isOK()) {
                JSONObject jsonObject = JSON.parseObject(response.bodyString());

                String yunFileName = jsonObject.getString("key");
                String yunFilePath = StrUtil.appendIfMissing(prefix, StringPool.SLASH) + yunFileName;

                FileUtil.del(new File(localFilePath));

                log.info("【文件上传至七牛云】绝对路径：{}", yunFilePath);
                return R.ok("上传成功").put("fileName", fileName).put("filePath", yunFilePath);
            } else {
                log.error("【文件上传至七牛云】失败，{}", JSONUtil.toJsonStr(response));
                FileUtil.del(new File(localFilePath));
                return R.error("文件上传失败");
            }
        } catch (IOException e) {
            log.error("【文件上传至七牛云】失败，绝对路径：{}", localFilePath);
            return R.error("文件上传失败");
        }
    }

    @PostMapping(value = "/smms", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R uploadSmms(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.error(400, "文件内容为空");
        }
        String fileName = file.getOriginalFilename();
        String rawFileName = StrUtil.subBefore(fileName, StringPool.DOT, true);
        String fileType = StrUtil.subAfter(fileName, StringPool.DOT, true);
        String localFilePath = StrUtil.appendIfMissing(fileTempPath, StringPool.SLASH) + rawFileName + StringPool.DASH + DateUtil.current(false) + StringPool.DOT + fileType;
        try {
            file.transferTo(new File(localFilePath));
            String response = smmsService.upload(new File(localFilePath));
			FileUtil.del(new File(localFilePath));
			log.info("【文件上传至sm.ms】绝对路径：{}", response);
			JSONObject jsonObject = JSON.parseObject(response);
			String filePath = ((JSONObject) jsonObject.get("data")).getString("url");
			return R.ok("上传成功").put("fileName", fileName).put("filePath", filePath);
        } catch (IOException e) {
            log.error("【文件上传至sm.ms】失败，绝对路径：{}", localFilePath);
            return R.error("文件上传失败");
        }
    }
}
