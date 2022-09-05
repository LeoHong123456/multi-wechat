package com.app.wechat.service.impl;

import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.RegisterDto;
import com.app.wechat.domain.entity.MultiMember;
import com.app.wechat.domain.enums.RestCodeEnum;
import com.app.wechat.domain.vo.LoginVo;
import com.app.wechat.mapper.MultiMemberMapper;
import com.app.wechat.service.IMemberService;
import com.app.wechat.utils.MD5Util;
import com.app.wechat.utils.StringsUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Administrator
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MultiMemberMapper, MultiMember> implements IMemberService {

    @Override
    public Result<LoginVo> login(LoginDto loginDto) throws Exception {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        String ip = loginDto.getIp();
        String ipAddr = loginDto.getIpAddr();

        String sessionId = MD5Util.getMD5(StringsUtil.randomString(26));
        String encryPwd = MD5Util.getMD5(Objects.requireNonNull(MD5Util.getMD5(password.concat("multi_key"))));
        MultiMember member = this.lambdaQuery().eq(MultiMember::getUsername, username).eq(MultiMember::getPassword, encryPwd).eq(MultiMember::getStatus, 1).one();
        if (ObjectUtils.allNull(member)) {
            return Result.failure(RestCodeEnum.FAIL_TO_LOGIN_ERROR);
        }
        this.lambdaUpdate().set(MultiMember::getLoginIp, ip)
                .set(MultiMember::getLoginAddress, ipAddr)
                .set(MultiMember::getSessionId, sessionId)
                .eq(MultiMember::getUsername, username).update();
        return Result.success(new LoginVo(username, sessionId));
    }

    @Override
    public Result<LoginVo> register(RegisterDto registerDto) throws Exception {
        String username = registerDto.getUsername();
        String email = registerDto.getEmail();
        String pwd = registerDto.getPwd();
        String rpwd = registerDto.getRpwd();
        String ip = registerDto.getIp();
        String ipAddr = registerDto.getIpAddr();
        if (!StringUtils.equals(pwd, rpwd)) {
            return Result.failure(RestCodeEnum.FAIL_TO_PASSWORD_ERROR);
        }
        MultiMember member = this.lambdaQuery().eq(MultiMember::getUsername, username).one();
        if (ObjectUtils.allNotNull(member)) {
            return Result.failure(RestCodeEnum.FAIL_TO_USERNAME_IS_EXTIS);
        }
        MultiMember one = this.lambdaQuery().select(MultiMember::getEmail).eq(MultiMember::getEmail, email).one();
        if (ObjectUtils.allNotNull(one)) {
            return Result.failure(RestCodeEnum.FAIL_TO_EMAIL_IS_EXTIS);
        }
        String sessionId = MD5Util.getMD5(StringsUtil.randomString(26));
        String encryPwd = MD5Util.getMD5(Objects.requireNonNull(MD5Util.getMD5(pwd.concat("multi_key"))));
        MultiMember multiMember = new MultiMember();
        multiMember.setUsername(username);
        multiMember.setEmail(email);
        multiMember.setPassword(encryPwd);
        multiMember.setSessionId(sessionId);
        multiMember.setLoginIp(ip);
        multiMember.setLoginAddress(ipAddr);
        save(multiMember);
        return Result.success(new LoginVo(username, sessionId));
    }
}
