package com.boot.power.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.power.common.validater.ValidateGroup;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@TableName("tbl_role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "角色 id 不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Query.class})
    private Integer id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空", groups = {ValidateGroup.Add.class, ValidateGroup.Update.class})
    private String roleName;

    /**
     * 创建时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
        return "RoleEntity{" +
                "id=" + id +
                ", roleName=" + roleName +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                "}";
    }
}
