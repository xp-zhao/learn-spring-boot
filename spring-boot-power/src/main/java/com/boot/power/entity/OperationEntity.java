package com.boot.power.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 功能操作表
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@TableName("tbl_operation")
public class OperationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 操作编码
     */
    private String operationCode;

    /**
     * 拦截的 url 前缀
     */
    private String prefixUrl;

    /**
     * 父操作 id
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getPrefixUrl() {
        return prefixUrl;
    }

    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "OperationEntity{" +
        "id=" + id +
        ", operationName=" + operationName +
        ", operationCode=" + operationCode +
        ", prefixUrl=" + prefixUrl +
        ", parentId=" + parentId +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
