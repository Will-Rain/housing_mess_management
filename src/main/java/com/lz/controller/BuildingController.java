package com.lz.controller;

import com.alibaba.fastjson.JSONObject;
import com.lz.entity.Building;
import com.lz.service.BuildingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * (Building)表控制层
 *
 * @author makejava
 * @since 2020-02-29 13:33:16
 */
@RestController
@RequestMapping("/building")
public class BuildingController {
    /**
     * 服务对象
     */
    @Resource
    private BuildingService buildingService;

    @RequestMapping("/getAllBuilding")
    public String getAllBuilding(int page,int limit,Building building){
//        System.out.println(building);
        List<Building> list = buildingService.queryAll(building,0,0); //查询全部数据

        //这是 layui 表格所必须的参数
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0); // 状态码必须为 0
        obj.put("msg", ""); // 提示文本
        obj.put("count", list.size()); // 全部数据长度
        obj.put("data", buildingService.queryAll(building,limit * (page - 1), limit)); //分页查询

        return obj.toJSONString();
    }

    @RequestMapping("/addBuilding")
    public int addBuilding(Building building) throws ParseException {
//        System.out.println(building.toString());

        //把从数据库中取出来的日期（变成格林尼治的date类型了）进行格式化后，赋给id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(building.getConstructionTime());
        String[] dateArray = dateString.split("-");
        dateString = "";
        for (String s : dateArray) {
            dateString += s;
        }

        if(building.getBuildingNumber() < 10)
            building.setId(dateString + "0" + building.getBuildingNumber());
        else
            building.setId(dateString + building.getBuildingNumber().toString());

        int msg = 0; //修改失败
        if(buildingService.queryById(building.getId())!=null || buildingService.queryByNumber(building.getBuildingNumber())!=null){
            msg = 2; //楼宇已存在
            return msg;
        }
        if(buildingService.insert(building)!=null)
            msg = 1; //添加成功
        return msg;
    }



    @RequestMapping("/updateBuilding")
    public int updateBuilding(Building building){
        int msg = 0; //修改失败
        if(buildingService.update(building) !=null){
            msg = 1; //修改成功
        }
        return msg;
    }

    @RequestMapping("/deleteBuilding")
    public int deleteBuilding(String[] idArray){
        int msg = 1; //成功
        for (String id : idArray) {
            if(!buildingService.deleteById(id))
                msg = 0;
        }
        return msg;
    }

    @RequestMapping("/selectDistinctNumber")
    public List<Integer> selectDistinctNumber(){
        return buildingService.selectDistinctNumber();
    }

    @RequestMapping("/selectDistinctId")
    public String selectDistinctId(String number){
        return buildingService.queryByNumber(Integer.parseInt(number)).getId();
    }
}