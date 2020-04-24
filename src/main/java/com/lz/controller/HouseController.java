package com.lz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lz.entity.House;
import com.lz.entity.Unit;
import com.lz.service.HouseService;
import com.lz.service.UnitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (House)表控制层
 *
 * @author makejava
 * @since 2020-03-02 10:57:54
 */
@RestController
@RequestMapping("house")
public class HouseController {
    /**
     * 服务对象
     */
    @Resource
    private HouseService houseService;

    @Resource
    private UnitService unitService;

    @RequestMapping("/getAllHouse")
    public String getAllHouse(int page, int limit, String buildingId, String unitId){
//        System.out.println(buildingId + "===>>>" + unitId);
        List<House> list = houseService.queryAllByLimit(buildingId, unitId,0,0); //查询全部数据

        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", list.size());
        obj.put("data", JSONObject.parse(JSONArray.toJSONString(houseService.queryAllByLimit(buildingId, unitId, limit * (page - 1), limit), SerializerFeature.DisableCircularReferenceDetect))); //分页查询

        return obj.toJSONString();
    }

    @RequestMapping("/searchHouse")
    public String searchHouse(String buildingId, String unitId, String houseType,
                              String saleInfo, int page, int limit){
//        System.out.println(buildingId + "===>>>" + unitId);
        if(null == saleInfo || "".equals(saleInfo))
            saleInfo = "-1"; // -1表示不用比较该字段
        List<House> list = houseService.searchHouseByLimit(buildingId,unitId,houseType,Integer.parseInt(saleInfo),0,0); //查询全部数据

        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", list.size());
        obj.put("data", JSONObject.parse(JSONArray.toJSONString(houseService.searchHouseByLimit(buildingId,unitId,houseType,Integer.parseInt(saleInfo), limit * (page - 1), limit), SerializerFeature.DisableCircularReferenceDetect))); //分页查询

        return obj.toJSONString();
    }


    @RequestMapping("/updateHouse")
    public int updateHouse(@RequestBody House house){
//        System.out.println(house.toString());
        int msg = 0; //修改失败
        if(houseService.update(house) !=null){
            msg = 1; //修改成功
        }
        return msg;
    }

    @RequestMapping("/addHouse")
    public int addHouse(@RequestBody House house){
//        System.out.println(house.toString());

        Unit unit = unitService.queryById(house.getUnit().getId());
        int msg = 0; //插入失败

        if(house.getHouseNumber()/100 > unit.getLayerCount()){
            msg = 3; //超出该单元的所设定的楼层数了
            return msg;
        }
        if(house.getHouseNumber()%100 > unit.getHouseCount()){
            msg = 4; //超出该楼层的所设定的房间数量了
            return msg;
        }

        //拼接 id
        String id;
        if(house.getHouseNumber()/100 < 10)
            id = unit.getId() + "0" + house.getHouseNumber();
        else
            id = unit.getId() + house.getHouseNumber();
        house.setId(id);

        if(houseService.queryById(id)!=null){
            msg = 2; //房屋已存在
            return msg;
        }
        if(houseService.insert(house)!=null)
            msg = 1; //添加成功
        return msg;
    }

    @RequestMapping("/deleteHouse")
    public int deleteHouse(String[] idArray){
        int msg = 1; //成功
        for (String id : idArray) {
            if(!houseService.deleteById(id))
                msg = 0;
        }
        return msg;
    }

    @RequestMapping("/selectDistinctHouseNumber")
    public List<Integer> selectDistinctHouseNumber(String unitId){
        return houseService.selectDistinctHouseNumber(unitId);
    }

    @RequestMapping("/selectDistinctHouseId")
    public String selectDistinctUnitId(String buildingNumber,String unitNumber, String houseNumber){
        return houseService.selectDistinctIdByNumber(Integer.parseInt(buildingNumber),Integer.parseInt(unitNumber),Integer.parseInt(houseNumber)).getId();
    }

    // 房屋危险性等级
    @RequestMapping("/riskChart")
    public List<Map<String,Object>> riskChart(String buildingId,String unitId){
        return houseService.riskChart(buildingId, unitId);
    }
    @RequestMapping("/riskTable")
    public String riskTable(int page, int limit, String buildingId, String unitId){
//        System.out.println(buildingId + "||" + unitId);
        List<House> list = houseService.riskTable(buildingId, unitId, 0, 0); //查询全部数据

        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", list.size());
        obj.put("data", JSONObject.parse(JSONArray.toJSONString(houseService.riskTable(buildingId, unitId, limit * (page - 1), limit),
                SerializerFeature.DisableCircularReferenceDetect))); //分页查询
        return obj.toJSONString();
    }

    // 住房使用情况
    @RequestMapping("/useChart")
    public List<Map<String,Object>> useChart(String buildingId,String unitId){
        return houseService.useChart(buildingId, unitId);
    }

    // 住房出售情况
    @RequestMapping("/saleChart")
    public List<Map<String,Object>> saleChart(String buildingId,String unitId){
        return houseService.saleChart(buildingId, unitId);
    }
    @RequestMapping("/saleTable")
    public String saleTable(int page, int limit, String buildingId, String unitId){
        List<House> list = houseService.saleTable(buildingId, unitId, 0, 0); //查询全部数据

        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", list.size());
        obj.put("data", JSONObject.parse(JSONArray.toJSONString(houseService.saleTable(buildingId, unitId, limit * (page - 1), limit),
                SerializerFeature.DisableCircularReferenceDetect))); //分页查询
        return obj.toJSONString();
    }

    //获取住房数量
    @RequestMapping("/getHouseCount")
    public String getHouseCount() {
        return houseService.getHouseCount();
    }

    @GetMapping("/ResidentCheckInNumberChart")
    public List<Map<String, Object>> ResidentCheckInNumberChart() {
        return houseService.ResidentCheckInNumberChart();
    }


}