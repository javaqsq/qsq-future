package com.qsq.user.dto;

import com.qsq.common.model.PageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QSQ
 * @create 2020/1/19 22:34
 * No, again
 * 〈〉
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionListRequestDTO extends PageQuery {

    private String permission;

}