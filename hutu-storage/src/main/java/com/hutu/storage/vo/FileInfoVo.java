package com.hutu.storage.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 上传返回对象
 *
 * @author hutu
 * @date 2020-01-15 10:15
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoVo {

    String originalFileName;

    String fileName;

    String filePath;
}
