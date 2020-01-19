package com.hutu.storage.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.hutu.common.exception.GlobalException;
import com.hutu.common.utils.StringPool;

/**
 * 工具类
 * @author hutu
 * @date 2020-01-19 11:30
 */
public class StorageUtils {

    /**
     * 生成处理后的文件名
     * @param originalFilename 待生成的文件
     * @return 生成的文件名
     */
    public static String generatorFileName(String originalFilename) {
        if (originalFilename.isEmpty()) {
            throw new GlobalException("文件名不能为空");
        }
        String rawFileName = StrUtil.subBefore(originalFilename, StringPool.DOT, true);
        String fileType = StrUtil.subAfter(originalFilename, StringPool.DOT, true);
        return rawFileName + StringPool.DASH + DateUtil.current(false) + StringPool.DOT + fileType;
    }
}
