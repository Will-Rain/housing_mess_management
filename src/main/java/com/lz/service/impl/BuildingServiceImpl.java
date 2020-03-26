package com.lz.service.impl;

import com.lz.dao.BuildingDao;
import com.lz.dao.HouseDao;
import com.lz.dao.UnitDao;
import com.lz.entity.Building;
import com.lz.entity.House;
import com.lz.entity.Unit;
import com.lz.service.BuildingService;
import com.lz.service.UnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Building)表服务实现类
 *
 * @author makejava
 * @since 2020-02-29 13:33:16
 */
@Service("buildingService")
public class BuildingServiceImpl implements BuildingService {
    @Resource
    private BuildingDao buildingDao;
    @Resource
    private UnitDao unitDao;
    @Resource
    private UnitService unitService;
    @Resource
    private HouseDao houseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Building queryById(String id) {
        return this.buildingDao.queryById(id);
    }

    @Override
    public Building queryByNumber(int buildingNumber) {
        return this.buildingDao.queryByNumber(buildingNumber);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Building> queryAllByLimit(int offset, int limit) {
        return this.buildingDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Building> queryAll(Building building, int offset, int limit) {
        return this.buildingDao.queryAll(building,offset,limit);
    }

    /**
     * 新增数据
     *
     * @param building 实例对象
     * @return 实例对象
     */
    @Override
    public Building insert(Building building) {
        this.buildingDao.insert(building);
        for(int i=1;i<=building.getUnitCount();i++) {
            String unitid;
            if (i < 10) //拼接unit.id = buildi.id + unit.unitNumber
                unitid = building.getId() + "0" + i;
            else
                unitid = building.getId() + i;
            Unit unit = new Unit(unitid, i, 6, 2, 1, building);
            unitDao.insert(unit);

            for (int j = 1; j <= unit.getLayerCount(); j++) {
                for (int k = 1; k <= unit.getHouseCount(); k++) {
                    String houseNumber;
                    if (k < 10)
                        houseNumber = j + "0" + k;
                    else
                        houseNumber = j + "" + k ;
                    String houseId;
                    if(j < 10)
                        houseId = unit.getId() + "0" + houseNumber;
                    else
                        houseId = unit.getId() + houseNumber;

                    House house = new House(houseId, Integer.parseInt(houseNumber), "三居室", 120, 0, "A", "", 5, 0, null, unit);
//                    System.out.println(house.toString());
                    houseDao.insert(house);
                }
            }
        }

        return building;
    }

    /**
     * 修改数据
     *
     * @param building 实例对象
     * @return 实例对象
     */
    @Override
    public Building update(Building building) {
        this.buildingDao.update(building);
        return this.queryById(building.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.buildingDao.deleteById(id) > 0;
    }

    @Override
    public List<Integer> selectDistinctNumber() {
        return this.buildingDao.selectDistinctNumber();
    }

    @Override
    public String getBuildingCount() {
        return this.buildingDao.getBuildingCount();
    }
}