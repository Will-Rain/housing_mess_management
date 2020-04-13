package com.lz.config;

import com.lz.entity.Administrator;
import com.lz.service.AdministratorService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/***
 * @author will
 * @date 2020/03/06 9:18
 */

//自定义的 UserRealm
public class UserRealm extends AuthorizingRealm {
    @Resource
    AdministratorService administratorService;//调用service层操作数据库

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("执行了==>授权 doGetAuthorizationInfo");

        //进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前登录的对象(已通过认证)
        Subject subject = SecurityUtils.getSubject();
        Administrator administrator = (Administrator) subject.getPrincipal();
        //给用户添加权限
        info.addStringPermission(administrator.getRole()+"");//权限存在于数据库中

        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("执行了==>认证 doGetAuthenticationInfo");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //获取数据库中信息
        Administrator administrator = administratorService.queryByName(token.getUsername());

        if(administrator==null)
            return null; //抛出异常 UnknownAccountException

        //登录成功后，获得shiro的session，以便在前端HTML中进行判断
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
//        session.setAttribute("loginStudent",student);
        session.setAttribute("name",administrator.getName());
        session.setAttribute("password",administrator.getPassword());
        session.setAttribute("role",administrator.getRole());

        //返回授权
        return new SimpleAuthenticationInfo(administrator,administrator.getPassword(),"");
    }
}