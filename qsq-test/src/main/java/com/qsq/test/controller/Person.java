package com.qsq.test.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author QSQ
 * @create 2020/1/6 15:20
 * No, again
 * 〈〉
 */
@Data
@AllArgsConstructor
public class Person {

    private String id;

    private String name;

    private String role;

    public CheckPerson hasRole(String role) {

        return CheckPerson.builder()
                .flag(StringUtils.equals(role, this.role))
                .msg(StringUtils.equals(role, this.role) ? "成功" : "没有权限")
                .build();
    }


}