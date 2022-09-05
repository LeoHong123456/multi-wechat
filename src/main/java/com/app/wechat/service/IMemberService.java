package com.app.wechat.service;

import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.RegisterDto;
import com.app.wechat.domain.entity.MultiMember;
import com.app.wechat.domain.entity.SysFile;
import com.app.wechat.domain.vo.LoginVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Administrator
 */
public interface IMemberService extends IService<MultiMember> {

    /**
     * 会员登录
     * @param loginDto
     * @return
     * @throws Exception
     */
    Result<LoginVo> login(LoginDto loginDto) throws Exception;

    /**
     * 会员注册
     * @param registerDto
     * @return
     */
    Result<LoginVo> register(RegisterDto registerDto) throws Exception;
}
