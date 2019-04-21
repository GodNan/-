package com.css.bdpfnew.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.LoginRepository;
import com.css.bdpfnew.service.LoginService;
import com.css.bdpfnew.util.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


	private final LoginRepository loginRepository;

	@Autowired
	public LoginServiceImpl(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}


    /**
     * 登录，通过shiro
     * @param username
     * @param password
     * @return
     */
    @Override
    public String authLogin(String username, String password) throws AuthenticationException {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        currentUser.login(token);
        //设置登录有效期
        currentUser.getSession().setTimeout(30*60*1000);
        String sessionId = (String) currentUser.getSession().getId();
        return sessionId;
    }

    @Override
    public String authLogin(String username) throws AuthenticationException {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, genRandomNum());
        currentUser.login(token);
        //设置登录有效期
        currentUser.getSession().setTimeout(30*60*1000);
        String sessionId = (String) currentUser.getSession().getId();
        return sessionId;
    }

    /**
     * 生成随机8位字符串
     * @return
     */
    private String genRandomNum(){
        int  maxNum = 36;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < 8){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public SysUser getUser(String username, String password) {
        SysUser user = loginRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return
     */
    @Override
    public JSONObject getInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString(Constants.USER_NAME);
        JSONObject returnData = new JSONObject();
//        JSONObject userPermission = permissionService.getUserPermission(username);
//        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
//        returnData.put("userPermission", userPermission);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public JSONObject logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
        }
        return CommonUtil.successJson();
    }

    @Override
    public SysUser findUserBySession() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        SysUser userInfo = (SysUser) session.getAttribute(Constants.SESSION_USER_INFO);
        return userInfo;
    }
}
