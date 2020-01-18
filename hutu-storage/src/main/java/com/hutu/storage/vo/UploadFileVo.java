package com.hutu.storage.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 上传返回对象
 * @author hutu
 * @date 2020-01-15 10:15
 */
@Setter
@Getter
@AllArgsConstructor
public class UploadFileVo {
    String fileName;
    String filePath;
}
