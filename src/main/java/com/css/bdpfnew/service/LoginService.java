package com.css.bdpfnew.service;

import com.alibaba.fastjson.JSONObject;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;

/**
 * 
 * @description: 登录Service
 * @date: 2017/10/24 11:02
 */
public interface LoginService {
    /**
     * 登录
     * @param username
     * @param password
     */
    String authLogin(String username, String password);

    /**
     * 登录,通过ca无密码登录
     * @param username
     */
    String authLogin(String username);

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    SysUser getUser(String username, String password);

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return
     */
    JSONObject getInfo();

    /**
     * 退出登录
     *
     * @return
     */
    JSONObject logout();

    SysUser findUserBySession();
}
