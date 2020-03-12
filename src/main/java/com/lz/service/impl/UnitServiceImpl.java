package com.lz.service.impl;

import com.lz.dao.BuildingDao;
import com.lz.dao.HouseDao;
import com.lz.dao.UnitDao;
import com.lz.entity.Building;
import com.lz.entity.House;
import com.lz.entity.Unit;
import com.lz.service.UnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Unit)表服务实现类
 *
 * @author makejava
 * @since 2020-03-01 14:51:16
 */
@Service("unitService")
public class UnitServiceImpl implements UnitService {
    @Resource
    private UnitDao unitDao;

    @Resource
    private BuildingDao buildingDao;

    @Resource
    private HouseDao houseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Unit queryById(String id) {
        return this.unitDao.queryById(id);
    }
    @Override
    public Unit queryByNumber(int buildingNumber, int unitNumber){
        return this.unitDao.queryByNumber(buildingNumber, unitNumber);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Unit> queryAllByLimit(String buildingId, int offset, int limit) {
        return this.unitDao.queryAllByLimit(buildingId, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param unit 实例对象
     * @return 实例对象
     */
    @Override
    public Unit insert(Unit unit) {//需要在这儿给building的单元数量赋值 +1
        Building building = this.buildingDao.queryById(unit.getBuilding().getId());
        building.setUnitCount(this.unitDao.selectUnitCount(unit.getBuilding().getId())+1);
        this.buildingDao.update(building);
        this.unitDao.insert(unit);

        for (int j = 1; j <= unit.getLayerCount(); j++) {
            for (int k = 1; k <= unit.getHouseCount(); k++) {
                String houseNumber;
                String houseId;
                if (k < 10)
                    houseNumber = j + "0" + k;
                else
                    houseNumber = j + "" + k ;
                if(j < 10)
                    houseId = unit.getId() + "0" + houseNumber;
                else
                    houseId = unit.getId() + houseNumber;

                House house = new House(houseId, Integer.parseInt(houseNumber), "三居室", 120, 0, "A", "", 5, 0, null, unit);
                houseDao.insert(house);
            }
        }

        return unit;
    }

    /**
     * 修改数据
     *
     * @param unit 实例对象
     * @return 实例对象
     */
    @Override
    public Unit update(Unit unit) { //可以用oldbuildingId和newbuildingId来试试,等会儿
        this.unitDao.update(unit);
        return this.queryById(unit.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {  //需要在这儿给building的单元数量赋值 -1
        Unit unit = this.unitDao.queryById(id);
        Building building = this.buildingDao.queryById(unit.getBuilding().getId());
        building.setUnitCount(this.unitDao.selectUnitCount(unit.getBuilding().getId())-1);
        this.buildingDao.update(building);
        return this.unitDao.deleteById(id) > 0;
    }

    @Override
    public List<Integer> selectDistinctUnitNumber(String buildingId){
        return this.unitDao.selectDistinctUnitNumber(buildingId);
    }

    @Override
    public List<Unit> houseCountTable(String buildingId, String unitId, int offset, int limit) {
        return this.unitDao.houseCountTable(buildingId,unitId,offset,limit);
    }
}