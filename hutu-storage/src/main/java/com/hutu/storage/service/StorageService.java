package com.hutu.storage.service;

import com.hutu.storage.vo.FileInfoVo;

import java.io.File;

/**
 * 文件存储
 * @author hutu
 * @date 2019-12-16 18:10
 */
public interface StorageService {
    /**
     * 上传
     * @param file 待上传文件
     * @return 结果
     */
    FileInfoVo upload(File file);
}
