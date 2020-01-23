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
 * 角色表
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRole extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    @NotBlank(message = "角色名不能为空")
    private String roleName;

    @NotBlank(message = "角色描述")
    private String description;


}
