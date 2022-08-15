package com.app.wechat.service;

import com.app.wechat.domain.dto.ChangPasswordDto;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.LoginOutDto;
import com.app.wechat.domain.base.Result;

public interface IUserService {
    /**
     * 会员登录
     * @param userDto
     * @return
     * @throws Exception
     */
    Result<Object> login(LoginDto userDto) throws Exception;

    /**
     * 会员登出
     * @param loginOutDto
     * @return
     */
    Result<Object> loginOut(LoginOutDto loginOutDto) throws Exception;

    /**
     * 修改密码
     * @param changPasswordDto
     * @return
     * @throws Exception
     */
    Result<Object> changPassword(ChangPasswordDto changPasswordDto) throws Exception;

    Result<Object> getVarCode() throws Exception;
}
