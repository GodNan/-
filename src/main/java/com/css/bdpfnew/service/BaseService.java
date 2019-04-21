package com.css.bdpfnew.service;

import com.css.bdpfnew.model.page.PageBean;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: GodNan
 * @Date: 2018/6/8 15:31
 * @Version: 1.0
 * @Description:
 */
public interface BaseService <T, ID extends Serializable>{
    /**
     * 查询实体对象分页
     *
     * @param pageBean
     *            分页信息
     * @return 实体对象分页
     */
    Page<T> findPage(PageBean pageBean);

    /**
     * 查询实体集合
     * @param pageBean
     * @return
     */
    List<T> findAllByPage(PageBean pageBean);
}
