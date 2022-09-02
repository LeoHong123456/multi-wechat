package com.app.wechat.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2022-08-31
 */
@TableName("sys_file")
@Data
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型(1:微信，2:抖音,3:抖音)
     */
    private Integer fileType;

    /**
     * 文件状态（1：上架，2：下架）
     */
    private Integer fileOnline;

    /**
     * 文件版本号
     */
    private String version;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 签名
     */
    private String sign;

    /**
     * 文件描述
     */
    private String memo;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 促销价
     */
    private BigDecimal promotionPrice;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建时间
     */
    private LocalDateTime updateTime;

}
