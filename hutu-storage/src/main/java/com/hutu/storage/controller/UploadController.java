package com.hutu.storage.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.hutu.common.entity.R;
import com.hutu.common.exception.GlobalException;
import com.hutu.common.utils.StringPool;
import com.hutu.storage.config.QiniuProperties;
import com.hutu.storage.service.StorageService;
import com.hutu.storage.service.impl.PictureServiceImpl;
import com.hutu.storage.util.StorageUtils;
import com.hutu.storage.vo.FileInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 上传api
 * @author hutu
 * @date 2020-01-19 10:53
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    private final MultipartProperties multipartProperties;

    private final QiniuProperties qiniuProperties;

    private final StorageService qiniuService;

    private final PictureServiceImpl pictureService;

    @Autowired
    public UploadController(PictureServiceImpl pictureService, StorageService qiniuService, QiniuProperties qiniuProperties, MultipartProperties multipartProperties) {
        this.qiniuService = qiniuService;
        this.pictureService = pictureService;
        this.qiniuProperties = qiniuProperties;
        this.multipartProperties = multipartProperties;
    }

    @PostMapping(value = "/local", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R local(@RequestParam("file") MultipartFile file) {
        File dest = new File(getLocalFilePath(file));
        try {
            file.transferTo(new File(getLocalFilePath(file)));
        } catch (IOException e) {
            log.error("【文件上传至本地】失败，绝对路径：{}", dest.getAbsolutePath());
            return R.error("文件上传失败");
        }
        log.info("【文件上传至本地】绝对路径：{}", dest.getAbsolutePath());
        return R.ok(new FileInfoVo(file.getOriginalFilename(),dest.getName(),dest.getAbsolutePath()));
    }

    @PostMapping(value = "/yun", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R yun(@RequestParam("file") MultipartFile file) {
        File dest = new File(getLocalFilePath(file));
        try {
            file.transferTo(dest);
            return R.ok(qiniuService.upload(dest));
        } catch (IOException e) {
            log.error("【文件上传至七牛云】失败，绝对路径：{}", dest.getAbsolutePath());
            return R.error("文件上传失败");
        }finally {
            FileUtil.del(dest);
        }
    }

    @PostMapping(value = "/smms", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R uploadSmms(@RequestParam("file") MultipartFile file) {
        File dest = new File(getLocalFilePath(file));
        try {
            file.transferTo(dest);
            return R.ok(pictureService.upload(dest));
        } catch (IOException e) {
            log.error("【文件上传至sm.ms】失败，绝对路径：{}", dest.getAbsolutePath());
            return R.error("文件上传失败");
        } finally {
            FileUtil.del(dest);
        }
    }


    private String getLocalFilePath(MultipartFile file) {
        if (file.isEmpty()) {
            throw new GlobalException("文件内容为空");
        }
        return StrUtil.appendIfMissing(multipartProperties.getLocation(), StringPool.SLASH) + StorageUtils.generatorFileName(file.getOriginalFilename());
    }
}
