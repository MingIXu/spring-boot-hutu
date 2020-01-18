package com.hutu.storage.service.impl;

import cn.hutool.http.GlobalHeaders;
import cn.hutool.http.HttpUtil;
import com.hutu.storage.constant.PictureConstants;
import com.hutu.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;

/**
 * sm.ms图床实现
 * @author hutu
 * @date 2020-01-17 18:31
 */
@Service("pictureService")
@Slf4j
public class PictureServiceImpl implements StorageService {

    @Override
    public String upload(File file) {
        GlobalHeaders.INSTANCE.header("Authorization",PictureConstants.AUTHORIZATION);
        HashMap<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("smfile", file);
        return HttpUtil.post(PictureConstants.SM_MS_URL, paramMap);
    }
}
