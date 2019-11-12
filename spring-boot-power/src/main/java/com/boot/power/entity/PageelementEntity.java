package com.boot.power.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 页面元素表
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@TableName("tbl_pageelement")
public class PageelementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码元素 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 元素名称
     */
    private String elementName;

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

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
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
        return "PageelementEntity{" +
        "id=" + id +
        ", elementName=" + elementName +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
