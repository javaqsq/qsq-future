package com.qsq.common.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @param <D> service
 * @param <T> 对象
 * @author qsq
 * @date 2019年10月16日21:07:34
 */
public class BaseController<D extends IService<T>, T> {

    @Autowired
    public D service;


    /**
     * 初始化 分页信息的页码和当前页
     *
     * @param q   查询的参数
     * @param <P> 查询结果的response 返回类
     * @param <Q> 查询的类 , 必须继承PageQuery
     * @return 分页对象
     */
    protected <P, Q extends PageQuery> Page<P> converterDTOToPageInit(Q q) {
        Page<P> page = new Page<>();
        page.setCurrent(q.getPage() == null ? 1L : q.getPage());
        page.setSize(q.getLimit() == null ? 10L : q.getLimit());
        return page;
    }

    protected boolean isHaveAdminRole(List<String> roles) {
        return roles.contains("admin");
    }

}