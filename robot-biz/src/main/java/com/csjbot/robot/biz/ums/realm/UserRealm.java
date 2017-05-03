package com.csjbot.robot.biz.ums.realm;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.csjbot.robot.base.exception.DisableAccountException;
import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.ums.service.UserService;

public class UserRealm extends AuthorizingRealm {

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    @Autowired
    private UserService userService;

    /**
     * 从 DB中取出权限相关信息 交由 Realm
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	SimpleAuthenticationInfo authenticationInfo = null;
    	try {
    		String username = (String) token.getPrincipal();
            User user = userService.getByUsername(username, -1);
            if (user == null) {
                user = userService.getByUsername(username, -1);
            }
            if (user == null) {
                throw new UnknownAccountException();// 没找到帐号
            }
            if (user.getStatus() == 2) {
                throw new LockedAccountException(); // 帐号锁定
            }
            if (user.getStatus() == 0) {
                throw new DisableAccountException();// 账号停用
            }
            // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
            authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), // 用户名
                    user.getPassword(), // 密码
                    ByteSource.Util.bytes(user.getSalt()),// salt=username+salt
                    // ByteSource.Util.bytes(tbUser.getUsername()+tbUser.getSalt()),// salt=username+salt
                    getName() // realm name
                    );
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        
        return authenticationInfo;
    }

    /**
     * 支持or and not 关键词 不支持and or混用
     * 
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains(OR_OPERATOR)) {
            String[] permissions = permission.split(OR_OPERATOR);
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permission.contains(AND_OPERATOR)) {
            String[] permissions = permission.split(AND_OPERATOR);
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if (permission.startsWith(NOT_OPERATOR)) {
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
        } else {
            return super.isPermitted(principals, permission);
        }
    }
}
