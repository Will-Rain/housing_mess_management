package com.lz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lz.entity.Unit;
import com.lz.service.BuildingService;
import com.lz.service.UnitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Unit)表控制层
 *
 * @author makejava
 * @since 2020-03-01 14:51:16
 */
@RestController
@RequestMapping("/unit")
public class UnitController {
    /**
     * 服务对象
     */
    @Resource
    private BuildingService buildingService;

    @Resource
    private UnitService unitService;


    @RequestMapping("/getAllUnit")
    public String getAllUnit(int page, int limit, String buildingId){
//        System.out.println(buildingId);
        List<Unit> list = unitService.queryAllByLimit(buildingId,0,0); //查询全部数据

        //这是 layui 表格所必须的参数
        JSONObject obj=new JSONObject();

        //前台通过key值获得对应的value值
        obj.put("code", 0); // 状态码必须为 0
        obj.put("msg", ""); // 提示文本
        obj.put("count", list.size()); // 全部数据长度
        obj.put("data",JSONObject.parse(JSONArray.toJSONString(unitService.queryAllByLimit(buildingId,limit * (page - 1), limit), SerializerFeature.DisableCircularReferenceDetect))); //分页查询

        return obj.toJSONString();
    }



    @RequestMapping("/updateUnit")
    public int updateUnit(String id, int elevatorCount, String buildingId){
        Unit unit = unitService.queryById(id);
        unit.setElevatorCount(elevatorCount);
//        System.out.println(unit.toString());
        int msg = 0; //修改失败
        if(unitService.update(unit) !=null){
            msg = 1; //修改成功
        }
        return msg;
    }


    @RequestMapping("/addUnit")
    public int addUnit(@RequestParam("buildingNumber") String buildingNumberStr,@RequestParam("unitNumber") String unitNumberStr,
                      @RequestParam("houseCount") String houseCountStr, @RequestParam("elevatorCount") String elevatorCountStr,
                       @RequestParam("layerCount") String layerCountStr){
//        System.out.println("buildingNumber = "+buildingNumberStr);
        int buildingNumber = Integer.parseInt(buildingNumberStr);
        int unitNumber = Integer.parseInt(unitNumberStr);
        int houseCount = Integer.parseInt(houseCountStr);
        int elevatorCount = Integer.parseInt(unitNumberStr);
        int layCount = Integer.parseInt(layerCountStr);
        Unit unit = new Unit();
        unit.setBuilding(buildingService.queryByNumber(buildingNumber));
        unit.setElevatorCount(elevatorCount);
        unit.setUnitNumber(unitNumber);
        unit.setLayerCount(layCount);
        unit.setHouseCount(houseCount); //每层住户数量


        if(unitNumber < 10) //拼接unit.id = buildi.id + unit.unitNumber
            unit.setId(buildingService.queryByNumber(buildingNumber).getId() + "0" + unitNumber);
        else
            unit.setId(buildingService.queryByNumber(buildingNumber).getId() + unitNumber);

        int msg = 0; //修改失败
        if(unitService.queryById(unit.getId())!=null){
            msg = 2; //单元已存在
            return msg;
        }
        if(unitService.insert(unit)!=null)
            msg = 1; //添加成功
        return msg;
    }

    @RequestMapping("/deleteUnit")
    public int deleteUnit(String[] idArray){
        int msg = 1; //成功
        for (String id : idArray) {
            if(!unitService.deleteById(id))
                msg = 0;
        }
        return msg;
    }

    @RequestMapping("/selectDistinctUnitNumber")
    public List<Integer> selectDistinctUnitNumber(String buildingId){
        return unitService.selectDistinctUnitNumber(buildingId);
    }

    @RequestMapping("/selectDistinctUnitId")
    public String selectDistinctUnitId(String buildingNumber,String unitNumber){
        return unitService.queryByNumber(Integer.parseInt(buildingNumber),Integer.parseInt(unitNumber)).getId();
    }


    // 住房数量
    @RequestMapping("/houseCountTable")
    public String houseCountTable(int page, int limit, String buildingId, String unitId){
//        System.out.println(buildingId);
        List<Unit> list = unitService.houseCountTable(buildingId, unitId, 0,0); //查询全部数据

        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", list.size());
        obj.put("data",JSONObject.parse(JSONArray.toJSONString(unitService.houseCountTable(buildingId, unitId, limit * (page - 1), limit),
                SerializerFeature.DisableCircularReferenceDetect))); //分页查询

        return obj.toJSONString();
    }
}