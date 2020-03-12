package com.lz.controller;

import com.lz.service.AdministratorService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/***
 * @author will
 * @date 2020/02/22 14:16
 */
@Controller
public class JumpController {
    @Resource
    private AdministratorService administratorService;

//    @RequestMapping({"index.html","index"})
//    public String index(){
//        return "index";
//    }

    @RequestMapping("pages/login.html")
    public String toLogin(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        if(subject!=null)
            subject.logout();
        return "pages/login";
    }

    //登录验证
//    @RequestMapping("/login")
//    @ResponseBody
//    public int login(@RequestParam("username") String name, String password, HttpSession session){
//        System.out.println(name+"----"+password);
//        int msg;  //好像封装的ajax只能返回数字类型的字符串
//        Administrator administrator = administratorService.queryByName(name);
//        if(administrator!=null && password.equals(administrator.getPassword())){
//            msg = 1; //账号、密码都正确
//            session.setAttribute("name",name);
//            session.setAttribute("password",password);
//            session.setAttribute("role",administrator.getRole());
//        }
//        else if(administrator!=null){
//            msg = 2; //密码错误
//        }
//        else
//            msg = 0; //账号不存在
//        return msg;
//    }


    @RequestMapping("/login")
    @ResponseBody
    public int login(@RequestParam("username") String name, String password, Model model){
        System.out.println(name+"----"+password);
        int msg;
        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        // 执行登录方法，若果没有异常就ok了
        // 如果有异常的话就捕获
        try {
            //进行认证
            subject.login(token);
            msg = 1;
            return msg;
        }catch (UnknownAccountException uae){
            msg = 0;
            return msg;
        }catch (IncorrectCredentialsException ice){
            msg = 2;
            return msg;
        }
    }

}
