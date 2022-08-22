package com.app.wechat.service.impl;
import com.app.wechat.configuration.RedisConfig;
import com.app.wechat.contants.CacheDbConstant;
import com.app.wechat.domain.dto.UploadDto;
import com.app.wechat.service.IUploadService;
import com.app.wechat.utils.JSONUtil;
import com.app.wechat.utils.RedisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UploadServiceImpl implements IUploadService {
    @Resource
    private RedisConfig redisConfig;
    @Override
    public void saveFile(UploadDto uploadDto) {
        String id = uploadDto.getId();
        RedisTemplate<String, Object> redisTemplate = redisConfig.getRedisTemplateByDb(CacheDbConstant.CACHE_DB_1);
        RedisUtils.set(id, JSONUtil.bean2Json(uploadDto), redisTemplate);
    }
}
