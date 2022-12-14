package com.app.wechat.service;

import com.app.wechat.domain.dto.ChangPwdDto;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.LoginOutDto;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.vo.VarCodeVo;

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
     * @param changPwdDto
     * @return
     * @throws Exception
     */
    Result<Object> changPassword(ChangPwdDto changPwdDto) throws Exception;

    Result<VarCodeVo> getVarCode() throws Exception;
}
