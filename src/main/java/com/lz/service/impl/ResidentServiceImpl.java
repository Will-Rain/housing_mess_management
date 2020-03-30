package com.lz.service.impl;

import com.lz.dao.HouseDao;
import com.lz.dao.ResidentDao;
import com.lz.entity.House;
import com.lz.entity.Resident;
import com.lz.service.ResidentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * (Resident)表服务实现类
 *
 * @author makejava
 * @since 2020-03-03 11:31:01
 */
@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    @Resource
    private ResidentDao residentDao;

    @Resource
    private HouseDao houseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Resident queryById(Integer id) {
        return this.residentDao.queryById(id);
    }

    @Override
    public Resident queryByIdentityCard(String identityCard) {
        return this.residentDao.queryByIdentityCard(identityCard);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Resident> queryAllByLimit(String buildingId, String unitId, String houseId, int offset, int limit) {
        return this.residentDao.queryAllByLimit(buildingId, unitId, houseId, offset, limit);
    }

    @Override
    public List<Resident> searchByName(String name, int offset, int limit) {
        return this.residentDao.searchByName("%" + name + "%", offset, limit); //拼接模糊查询
    }

    /**
     * 新增数据
     *
     * @param resident 实例对象
     * @return 实例对象
     */
    @Override
    public Resident insert(Resident resident) { //house表中人数需要改变
        if (resident.getParty().equals("") || resident.getParty() == null)
            resident.setParty("无");

        this.residentDao.insert(resident);

        House house = houseDao.queryById(resident.getHouse().getId());

//        int cnt = 0;
//        for(Resident r : residentDao.queryAllByLimit("","",house.getId(),0,0)){
//            cnt++;
//        }
        house.setHousePeopleCount(house.getHousePeopleCount() + 1);
        houseDao.update(house);

        return resident;
    }

    /**
     * 修改数据
     *
     * @param resident 实例对象
     * @return 实例对象
     */
    @Override
    public Resident update(Resident resident) {
        this.residentDao.update(resident);
        return this.queryById(resident.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        House house = houseDao.queryById(residentDao.queryById(id).getHouse().getId());

        //删除照片
        String photo = residentDao.queryById(id).getPhoto();
        String path = "E://IDEAcodes/residentPhoto/" + photo; //真实路径
//        String path = "/home/lz/IDEAcodes/residentPhoto/" + photo; //真实路径
        File file = new File(path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            System.out.println("执行了删除");
             if(!file.delete())
                 return false;
        }


        house.setHousePeopleCount(house.getHousePeopleCount() - 1);
        if (this.residentDao.deleteById(id) > 0 && houseDao.update(house) > 0) {

            return true;
        } else
            return false;
    }

    @Override
    public List<Resident> queryHeadOfHousehold(String buildingId, String unitId, String houseId) {
        return this.residentDao.queryHeadOfHousehold(buildingId, unitId, houseId);
    }

    @Override
    public List<Map<String, Object>> statisticalGender(String buildingId, String unitId) {
        return this.residentDao.statisticalGender(buildingId, unitId);
    }

    @Override
    public List<Map<String, Object>> statisticalAge(String buildingId, String unitId) {
        return this.residentDao.statisticalAge(buildingId, unitId);
    }

    @Override
    public List<Map<String, Object>> statisticalCensusRegister(String buildingId, String unitId) {
        return this.residentDao.statisticalCensusRegister(buildingId,unitId);
    }

    @Override
    public List<Map<String, Object>> statisticalEducational(String buildingId, String unitId) {
        return this.residentDao.statisticalEducational(buildingId, unitId);
    }

    @Override
    public List<Map<String, Object>> statisticalParty(String buildingId, String unitId) {
        return this.residentDao.statisticalParty(buildingId, unitId);
    }

    @Override
    public List<Map<String, Object>> statisticalPeopleCount() {
        return this.residentDao.statisticalPeopleCount();
    }

    //获取居民数量
    @Override
    public String getResidentCount() {
        return this.residentDao.getResidentCount();
    }


}