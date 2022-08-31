package com.app.wechat.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
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
     * 文件状态（1：上架，2：下架）
     */
    private Integer fileOnline;

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建时间
     */
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFileOnline() {
        return fileOnline;
    }

    public void setFileOnline(Integer fileOnline) {
        this.fileOnline = fileOnline;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysFile{" +
        "id = " + id +
        ", fileName = " + fileName +
        ", fileId = " + fileId +
        ", filePath = " + filePath +
        ", fileOnline = " + fileOnline +
        ", operator = " + operator +
        ", sign = " + sign +
        ", memo = " + memo +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        "}";
    }
}
