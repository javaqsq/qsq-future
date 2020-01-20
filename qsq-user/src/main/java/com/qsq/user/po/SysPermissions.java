package com.qsq.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qsq.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysPermissions extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "permissions_id", type = IdType.AUTO)
    private Integer permissionsId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String permissionsName;

    /**
     * 上级菜单编号
     */
    private Integer parentId;

    /**
     * 请求地址
     */
    @NotBlank(message = "请求地址不能为空")
    private String requestUrl;

    /**
     * 排序号
     */
    private Integer seqNumber;

    /**
     * 菜单级别(1:一级菜单;2:二级菜单;3:三级菜单;)
     */
    private Boolean level;

    /**
     * 备注
     */
    @NotBlank(message = "权限描述不能为空")
    private String description;

    /**
     * 请求方式
     */
    private String method;
}
