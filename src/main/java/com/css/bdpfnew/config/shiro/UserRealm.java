package com.css.bdpfnew.config.shiro;

import com.css.bdpfnew.constant.CommonConfig;
import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.service.LoginService;
import com.css.bdpfnew.service.SysPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Set;

/**
 * 实现AuthorizingRealm接口用户用户认证
 * @description: 自定义Realm
 * @date: 2017/10/24 10:06
 */
public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private CommonConfig commonConfig;
    @Autowired
    private LoginService loginService;
    @Autowired
    @Lazy
    private SysPermissionService sysPermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        SysUser user = (SysUser) session.getAttribute(Constants.SESSION_USER_INFO);

        Set<String> permissions = new HashSet<String>();

        if(commonConfig.isDev){
            // 正常登录所需代码块
            permissions.addAll(sysPermissionService.findFunctionByUser(user));
        }else {
            // CA登录所需代码块
            Set<SysRole> roles = user.getRoles();
            permissions.addAll(sysPermissionService.findPermission(roles));
        }


        logger.info("permission的值为:" + permissions);
        logger.info("本用户权限为:" + permissions.toString());
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String loginName = (String) authcToken.getPrincipal();
        // 获取用户密码
        String password = new String((char[]) authcToken.getCredentials());

        SimpleAuthenticationInfo authenticationInfo = null;
        if(commonConfig.isDev){
            // 正常登录所需代码块
            SysUser user = loginService.getUser(loginName, password);
            if (user == null) {
                //没找到帐号
                throw new UnknownAccountException();
            }
            authenticationInfo = new SimpleAuthenticationInfo(
                    user.getUsername(),
                    user.getPassword(),
                    //ByteSource.Util.bytes("salt"), //salt=username+salt,采用明文访问时，不需要此句
                    getName()
            );
            // session中不需要保存密码
            user.setPassword("");
            // 将用户信息放入session中
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
        }else {
            // CA登录所需代码块
            //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
            authenticationInfo = new SimpleAuthenticationInfo(
                    loginName,
                    password,
                    getName()
            );
        }
        return authenticationInfo;
    }
}
