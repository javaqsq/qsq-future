package com.qsq.auth.converter;

import com.qsq.auth.dto.RegisterRequestDTO;
import com.qsq.auth.po.SysUser;
import lombok.experimental.UtilityClass;

/**
 * @author QSQ
 * @create 2020/1/7 16:12
 * No, again
 * 〈〉
 */
@UtilityClass
public class AuthModuleConverter {

    /**
     * 转换
     *
     * @param requestDTO
     * @return
     */
    public SysUser registerToSysUser(RegisterRequestDTO requestDTO) {
        return SysUser.builder()
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .nickname(requestDTO.getNickname())
                .mobile(requestDTO.getMobile())
                .birthday(requestDTO.getBirthday())
                .sex(requestDTO.getSex())
                .email(requestDTO.getEmail())
                .lockSign(requestDTO.getLockSign())
                .address(requestDTO.getAddress())
                .build();

    }

}