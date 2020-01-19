package com.qsq.user.dto;

import com.qsq.common.model.PageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author QSQ
 * @create 2020/1/19 22:31
 * No, again
 * 〈〉
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleListRequestDTO extends PageQuery {

    private String role ;

}