package com.qsq.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.auth.po.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
@Component
public interface AuthServerMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户的权限列表
     *
     * @param id 用户id
     * @return 用户权限
     */
    List<String> getUserRoleByUserId(@Param("id") Integer id);
}
