package com.lz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lz.entity.Party;
import com.lz.service.PartyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Party)表控制层
 *
 * @author makejava
 * @since 2020-04-03 18:47:01
 */
@RestController
@RequestMapping("/party")
public class PartyController {
    /**
     * 服务对象
     */
    @Resource
    private PartyService partyService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getAllParty")
    public String getAllResident(int page, int limit) {
//        System.out.println(buildingId + "||" + unitId + "||" + houseId);
        List<Party> list = partyService.queryAllByLimit(limit * (page - 1), limit); //查询全部数据

        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", list.size());
        obj.put("data", JSONObject.parse(JSONArray.toJSONString(list,
                SerializerFeature.DisableCircularReferenceDetect))); //分页查询

        return obj.toJSONString();
    }

    @RequestMapping("/selectDistinctParty")
    public List<Party> selectDistinctParty(){
        return partyService.queryAllByLimit(0,0);
    }
}