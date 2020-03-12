package com.lz.controller;

import com.alibaba.fastjson.JSONObject;
import com.lz.entity.Administrator;
import com.lz.service.AdministratorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * (Administrator)表控制层
 *
 * @author makejava
 * @since 2020-02-24 18:10:39
 */
@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    /**
     * 服务对象
     */
    @Resource
    private AdministratorService administratorService;


    @RequestMapping("/getAdmin")
    public Administrator getAdmin(String name){
        return administratorService.queryByName(name);
    }

    @RequestMapping("/addAdmin")
    public int addAdmin(Administrator administrator){
//        System.out.println(administrator.toString());
        int msg = 0; //修改失败
        if(administratorService.queryByName(administrator.getName())!=null){
            msg = 2; //账号已存在
            return msg;
        }
        if(administratorService.queryByName(administrator.getName())==null && administratorService.insert(administrator)!=null)
            msg = 1; //添加成功

        return msg;
    }

//    @RequiresPermissions("1")
    //修改密码
    @RequestMapping("/updateAdmin")
    public int updateAdmin(Administrator administrator){
        int msg = 0; //修改失败
        if(administratorService.update(administrator) !=null){
            msg = 1; //修改成功
        }
        return msg;
    }


    @RequestMapping("/deleteAdmin")
    public int deleteAdmin(String[] nameArray, HttpSession session){
        int msg = 1; //成功
        int flag = 0;
        for (String name : nameArray) {
            if(!administratorService.deleteByName(name))
                msg = 0;
            if(name.equals(session.getAttribute("name"))){
                flag = 1;
            }
        }
        if(flag==1)
            session.invalidate();
        return msg;
    }


    @RequestMapping("/getAllAdmin")
    public String getAllAdmin(int page,int limit,Administrator administrator){
//        System.out.println(administrator);
        List<Administrator> list = administratorService.queryAll(administrator,0,0); //查询全部数据

        //这是 layui 表格所必须的参数
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0); // 状态码必须为 0
        obj.put("msg", ""); // 提示文本
        obj.put("count", list.size()); // 全部数据长度
        obj.put("data", administratorService.queryAll(administrator,limit * (page - 1), limit)); //分页查询

        return obj.toJSONString();
    }


    @RequestMapping("/selectDistinct")
    public List<String> selectDistinct(String data, String name, String password, HttpSession session){
//        System.out.println("data = "+data);
        List<String> list = null;
        if(data.equals("name")){
            list = administratorService.selectDistinctName();
        }
        if(data.equals("password")){
            list = administratorService.selectDistinctPassword(name);
        }
        if(data.equals("role")){
            list = administratorService.selectDistinctRole(name,password);
        }
        return list;
    }

    //对象数组形式
    @RequestMapping("/getAdminChart")
    public List<Map<String,Object>> getAdminChart(Administrator administrator){
//        System.out.println("administrator = "+ administrator);
        List<Map<String,Object>> list = administratorService.selectToChart(administrator);

        return list;
    }

//    //二维数组形式
//    @RequestMapping("/getAdminChart")
//    public Map<String,List<String>> getAdminChart(Administrator administrator, HttpSession session){
//        System.out.println("administrator = "+ administrator);
//        List<Map<String,Object>> list = administratorService.selectToChart(administrator);
//        Map<String,List<String>> map = new HashMap<>();
//        List<String> list1 = new ArrayList<String>();
//        List<String> list2 = new ArrayList<String>();
//        for (Map<String,Object> m:list) {
//                list1.add(""+m.get("role"));
//                list2.add(""+m.get("count"));
//        }
//
//        map.put("role",list1);
//        map.put("count",list2);
//
//        return map;
//    }

}