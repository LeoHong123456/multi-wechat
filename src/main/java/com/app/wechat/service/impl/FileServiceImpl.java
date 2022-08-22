package com.app.wechat.service.impl;

import com.app.wechat.configuration.RedisConfig;
import com.app.wechat.contants.CacheDbConstant;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.vo.FileVo;
import com.app.wechat.service.IFileService;
import com.app.wechat.utils.JSONUtil;
import com.app.wechat.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FileServiceImpl implements IFileService {
    @Resource
    private RedisConfig redisConfig;

    @Override
    public Result<List<FileVo>> wechatList() {
        RedisTemplate<String, Object> redisTemplate = redisConfig.getRedisTemplateByDb(CacheDbConstant.CACHE_DB_1);
        Set<Object> keys = RedisUtils.keys("*", redisTemplate);
        List<FileVo> list = new ArrayList<>();
        keys.forEach(item->{
            Object object = RedisUtils.get(item.toString(), redisTemplate);
            String objStr = JSONUtil.bean2Json(object);
            list.add(JSONUtil.json2Bean(objStr, FileVo.class));
        });
        return Result.success(list);
    }
}
