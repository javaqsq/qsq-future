package com.qsq.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QSQ
 * @create 2020/1/15 21:33
 * No, again
 * 〈前端查询分页信息〉
 */
@Data
public class PageQuery {

    /**
     * 一页的大小
     */
    private Long limit = 15L;


    /**
     * 查询第几页
     */
    private Long page = 1L;

}