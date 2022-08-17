package com.app.wechat.service.impl;

import com.app.wechat.configuration.RedisConfig;
import com.app.wechat.contants.CacheDbConstant;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.ChangPasswordDto;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.LoginOutDto;
import com.app.wechat.domain.entity.User;
import com.app.wechat.domain.enums.RestCodeEnum;
import com.app.wechat.domain.vo.VarCodeVo;
import com.app.wechat.service.IUserService;
import com.app.wechat.utils.JSONUtil;
import com.app.wechat.utils.MD5Util;
import com.app.wechat.utils.RedisUtils;
import com.app.wechat.utils.StringsUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private RedisConfig redisConfig;
    @Resource
    private DefaultKaptcha captchaProducer;

    @Override
    public Result<Object> login(LoginDto userDto) throws Exception {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        String varCode = userDto.getVarCode();
        String varCodeId = userDto.getVarCodeId();

        RedisTemplate<String, Object> varCodeTemplat = redisConfig.getRedisTemplateByDb(CacheDbConstant.CACHE_DB_2);
        Object varCodeDb = RedisUtils.get(varCodeId, varCodeTemplat);
        if(!StringUtils.equals(varCode, varCodeDb.toString())){
            return Result.failure(RestCodeEnum.VAR_CODE_ERROR);
        }
        RedisTemplate<String, Object> redisTemplate = redisConfig.getRedisTemplateByDb(CacheDbConstant.CACHE_DB_0);
        Object o = RedisUtils.get(username, redisTemplate);
        if(ObjectUtils.allNotNull(o)){
            User user = JSONUtil.json2Bean(o.toString(), User.class);
            String encryptPwd = MD5Util.getMD5(MD5Util.getMD5(password)+"wechat");
            if(StringUtils.equals(encryptPwd, user.getPassword())){
                String remitno = StringsUtil.randomString(12);
                user.setSessionId(MD5Util.getMD5(remitno));
                RedisUtils.set(username, JSONUtil.bean2Json(user), redisTemplate);
                return Result.success(RestCodeEnum.LOGIN_SUCCESS, user);
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
    public Result<VarCodeVo> getVarCode() throws Exception {
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        String varcodeText = captchaProducer.createText();
        BufferedImage challenge = captchaProducer.createImage(varcodeText);
        ImageIO.write(challenge, "jpg", jpegOutputStream);
        String encodeBase64String = Base64.encodeBase64String(jpegOutputStream.toByteArray());
        String varcodeId = StringsUtil.randomString(25);
        RedisTemplate<String, Object> redisTemplate = redisConfig.getRedisTemplateByDb(CacheDbConstant.CACHE_DB_2);
        RedisUtils.set(varcodeId, varcodeText, 200L, redisTemplate);
        return Result.success(new VarCodeVo(varcodeId,"data:image/png;base64," + encodeBase64String));
    }
}
