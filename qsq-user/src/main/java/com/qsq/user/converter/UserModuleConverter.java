package com.qsq.user.converter;

import com.qsq.user.dto.RegisterRequestDTO;
import com.qsq.user.po.SysUser;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

/**
 * @author QSQ
 * @create 2020/1/7 16:12
 * No, again
 * 〈〉
 */
@UtilityClass
public class UserModuleConverter {

    private final static String DEFAULT_AVATAR_URI = "E:/java-code/future-project-shop/file-path/avatar/20200113225310dota2剑圣.jpg";

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
                .avatar(StringUtils.isEmpty(requestDTO.getAvatar()) ? DEFAULT_AVATAR_URI : requestDTO.getAvatar())
                .build();

    }

}