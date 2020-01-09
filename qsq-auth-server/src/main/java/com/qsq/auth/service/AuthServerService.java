package com.qsq.auth.service;

import com.qsq.auth.dto.LoginRequestDTO;
import com.qsq.auth.dto.UserResponseDTO;
import com.qsq.common.auth.model.UserInfo;

/**
 * @author QSQ
 * @create 2020/1/7 9:15
 * No, again
 * 〈认证服务的数据〉
 */
public interface AuthServerService {


    /**
     * 用户授权
     *
     * @param requestDTO
     * @return
     */
    UserResponseDTO getUserInfoByRequestDTO(LoginRequestDTO requestDTO);


}
