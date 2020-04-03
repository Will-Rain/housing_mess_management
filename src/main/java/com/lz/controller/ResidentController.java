package com.lz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lz.entity.Resident;
import com.lz.service.ResidentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * (Resident)表控制层
 *
 * @author makejava
 * @since 2020-03-03 11:31:01
 */
@RestController
@RequestMapping("/resident")
public class ResidentController {
    /**
     * 服务对象
     */
    @Resource
    private ResidentService residentService;

    @RequestMapping("/getAllResident")
    public String getAllResident(int page, int limit, String buildingId, String unitId, String houseId) {
//        System.out.println(buildingId + "||" + unitId + "||" + houseId);
        List<Resident> list = residentService.queryAllByLimit(buildingId, unitId, houseId, 0, 0); //查询全部数据

        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", list.size());
        obj.put("data", JSONObject.parse(JSONArray.toJSONString(residentService.queryAllByLimit(buildingId, unitId, houseId, limit * (page - 1), limit),
                SerializerFeature.DisableCircularReferenceDetect))); //分页查询

        return obj.toJSONString();
    }


    @RequestMapping("/searchByName")
    public String searchByName(int page, int limit, String name) {
//        System.out.println("name = " + name);
        List<Resident> list = residentService.searchByName(name, 0, 0); //查询全部数据

        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", list.size());
        obj.put("data", JSONObject.parse(JSONArray.toJSONString(residentService.searchByName(name, limit * (page - 1), limit),
                SerializerFeature.DisableCircularReferenceDetect))); //分页查询

        return obj.toJSONString();
    }


//先提交form表单，当数据库add成功后，调用click，异步上传图片
//图片名称由身份证号来查找
    @RequestMapping("/upload")
    public String fileUpload(MultipartFile file, String identityCard) throws IOException {
//        System.out.println("identityCard = " + identityCard);
//        System.out.println("file = " + file);

        //上传路径保存设置
        String path = "E://IDEAcodes/residentPhoto/"; //真实路径
//        String path = "/home/lz/IDEAcodes/residentPhoto/"; //真实路径

        if (file != null) {
            String fileName = file.getOriginalFilename();
            String suffix = "";
            if (fileName != null) {
                suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            }
//            System.out.println("suffix = " + suffix);

            File realPath = new File(path);
            if (!realPath.exists()) {
                realPath.mkdir();
            }
//            //上传文件地址
//            System.out.println("上传文件保存地址："+realPath);

            Resident resident = residentService.queryByIdentityCard(identityCard);
            String photo = resident.getId() + "." + suffix;
            //通过MultipartFile的方法直接写文件（注意这个时候）
            file.transferTo(new File(realPath + "/" + photo));

            //更新图片信息
            resident.setPhoto(photo);
            residentService.update(resident);
        }

        JSONObject obj = new JSONObject();
        obj.put("msg", "上传成功");
        return obj.toJSONString();
    }


    @RequestMapping("/addResident")
    public int addResident(@RequestBody Resident resident) {
//        System.out.println(resident.toString());

        int msg = 0; //插入失败

        if (residentService.queryByIdentityCard(resident.getIdentityCard()) != null) {
            msg = 2; //此居民已存在
            return msg;
        }

        //注意：list判空：list.size == 0
        if (resident.getIsHeadOfHousehold() == 1 &&
                residentService.queryHeadOfHousehold("", "", resident.getHouse().getId()).size() > 0) {
            msg = 3; //此房屋已存在户主
            return msg;
        }
        if(resident.getIsHeadOfHousehold() ==0 &&
                residentService.queryHeadOfHousehold("", "", resident.getHouse().getId()).size() == 0) {
            msg = 4; //请先指定户主
            return msg;
        }
        if (residentService.insert(resident) != null) {
            msg = 1; //插入成功
        }

        return msg;
    }


    @RequestMapping("/updateResident")
    public int updateResident(@RequestBody Resident resident) {
        System.out.println(resident);
        int msg = 0; //修改失败
        if (residentService.update(resident) != null) {
            msg = 1; //修改成功
        }
        return msg;
    }

    @RequestMapping("/deleteResident")
    public int deleteHouse(String[] idArray) {
        System.out.println("idArray = " + idArray);
        int msg = 1; //成功
        for (String id : idArray) {
            if (!residentService.deleteById(Integer.parseInt(id)))
                msg = 0;
        }
        return msg;
    }

    //按性别统计
    @RequestMapping("/statisticalGender")
    public List<Map<String, Object>> statisticalGender(String buildingId, String unitId) {
        return residentService.statisticalGender(buildingId, unitId);
    }

    //按年龄统计
    @RequestMapping("/statisticalAge")
    public List<Map<String, Object>> statisticalAge(String buildingId, String unitId) {
        return residentService.statisticalAge(buildingId, unitId);
    }

    //按党派统计
    @RequestMapping("/statisticalCensusRegister")
    public List<Map<String, Object>> statisticalCensusRegister(String buildingId, String unitId) {
        return residentService.statisticalCensusRegister(buildingId, unitId);
    }


    //按学历统计
    @RequestMapping("/statisticalEducational")
    public List<Map<String, Object>> statisticalEducational(String buildingId, String unitId) {
        return residentService.statisticalEducational(buildingId, unitId);
    }

    //按党派统计
    @RequestMapping("/statisticalParty")
    public List<Map<String, Object>> statisticalParty(String buildingId, String unitId) {
        return residentService.statisticalParty(buildingId, unitId);
    }

    //人数统计图
    @RequestMapping("/statisticalPeopleCount")
    public List<Map<String, Object>> statisticalPeopleCount() {
        return residentService.statisticalPeopleCount();
    }

    //获取居民数量
    @RequestMapping("/getResidentCount")
    public String getResidentCount() {
        return residentService.getResidentCount();
    }
}