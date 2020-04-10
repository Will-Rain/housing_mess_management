package com.lz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lz.entity.Party;
import com.lz.service.PartyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/getAllParty")
    public String getAllParty(int page, int limit) {
//        System.out.println(buildingId + "||" + unitId + "||" + houseId);
        List<Party> list = partyService.queryAllByLimit(0,0); //查询全部数据

        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", list.size());
        obj.put("data", JSONObject.parse(JSONArray.toJSONString(partyService.queryAllByLimit(limit * (page - 1), limit),
                SerializerFeature.DisableCircularReferenceDetect))); //分页查询

        return obj.toJSONString();
    }

    @RequestMapping("/updateParty")
    public int updateParty(@RequestBody Party party){
        int msg = 0; //修改失败
        if (partyService.selectDistinctParty().indexOf(party.getParty()) >= 0) {
            msg = 2; //此党派已存在
            return msg;
        }
        if(partyService.update(party) !=null){
            msg = 1; //修改成功
        }
        return msg;
    }

    @RequestMapping("/addParty")
    public int addParty(@RequestBody Party party){
        int msg = 0; //插入失败

        if (partyService.selectDistinctParty().indexOf(party.getParty()) >= 0) {
            msg = 2; //此党派已存在
            return msg;
        }
        if (partyService.insert(party) != null) {
            msg = 1; //插入成功
        }
        return msg;
    }

    @RequestMapping("/deleteParty")
    public int deleteParty(String[] idArray){
        int msg = 1; //成功
        for (String id : idArray) {
            if(!partyService.deleteById(Integer.parseInt(id)))
                msg = 0;
        }
        return msg;
    }

    @RequestMapping("/selectDistinctParty")
    public List<Party> selectDistinctParty(){
        return partyService.queryAllByLimit(0,0);
    }
}