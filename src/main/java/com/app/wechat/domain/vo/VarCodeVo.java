package com.app.wechat.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class VarCodeVo implements Serializable {
    private String varCodeId;
    private String varCode;
}
