package com.hutu.generator.service;

import com.hutu.generator.form.GenConfig;
import com.hutu.generator.form.TableRequest;

import java.util.List;

public interface CodeGenService {
    /**
     * 生成代码
     *
     * @param genConfig 生成配置
     * @return 代码压缩文件
     */
    byte[] generatorCode(GenConfig genConfig);

    /**
     * 分页查询表信息
     *
     * @param request 请求参数
     * @return 表名分页信息
     */
    List listTables(TableRequest request);
}
