package com.hutu.picture.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.hutu.picture.constant.PictureConstants;
import com.hutu.picture.service.SMMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;

/**
 * sm.ms图床实现
 */
@Service
@Slf4j
public class SMMSServiceImpl implements SMMSService {

    @Override
    public String upload(File file) {
        HashMap<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("smfile", file);
        return HttpUtil.post(PictureConstants.SM_MS_URL, paramMap);
    }
}
