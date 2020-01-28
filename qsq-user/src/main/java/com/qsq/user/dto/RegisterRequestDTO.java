package com.qsq.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qsq.user.po.SysUser;
import com.qsq.user.po.SysUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/6 21:46
 * No, again
 * 〈登录请求〉
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO implements Serializable {

    /**
     * 用户
     */
    private SysUser userInfo;

    /**
     * 角色
     */
    private List<SysUserRole> roleInfoList;

}