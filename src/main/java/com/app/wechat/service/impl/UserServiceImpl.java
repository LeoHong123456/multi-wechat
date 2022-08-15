package com.app.wechat.service.impl;

import com.app.wechat.configuration.RedisConfig;
import com.app.wechat.contants.CacheDbConstant;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.ChangPasswordDto;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.LoginOutDto;
import com.app.wechat.domain.entity.User;
import com.app.wechat.domain.enums.RestCodeEnum;
import com.app.wechat.service.IUserService;
import com.app.wechat.utils.JSONUtil;
import com.app.wechat.utils.MD5Util;
import com.app.wechat.utils.RedisUtils;
import com.app.wechat.utils.StringsUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private RedisConfig redisConfig;

    @Override
    public Result<Object> login(LoginDto userDto) throws Exception {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        String varcode = userDto.getVarcode();
        RedisTemplate<String, Object> redisTemplate = redisConfig.getRedisTemplateByDb(CacheDbConstant.CACHE_DB_0);
        Object o = RedisUtils.get(username, redisTemplate);
        if(ObjectUtils.allNotNull(o)){
            User user = JSONUtil.json2Bean(o.toString(), User.class);
            String encryptPwd = MD5Util.getMD5(MD5Util.getMD5(password)+"wechat");
            if(StringUtils.equals(encryptPwd, user.getPassword())){
                String remitno = StringsUtil.randomString(12);
                user.setSessionId(MD5Util.getMD5(remitno));
                RedisUtils.set(username, JSONUtil.bean2Json(user), redisTemplate);
                return Result.success(user);
            }
        }
        return Result.failure(RestCodeEnum.FAIL_TO_LOGIN_ERROR);
    }

    @Override
    public Result<Object> loginOut(LoginOutDto loginOutDto) throws Exception {
        return null;
    }

    @Override
    public Result<Object> changPassword(ChangPasswordDto changPasswordDto) throws Exception {
        return null;
    }

    @Override
    public Result<Object> getVarCode() throws Exception {

        return null;
    }
}
