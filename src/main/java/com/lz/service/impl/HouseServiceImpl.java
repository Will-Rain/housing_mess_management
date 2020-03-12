package com.lz.service.impl;

import com.lz.dao.HouseDao;
import com.lz.entity.House;
import com.lz.service.HouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (House)表服务实现类
 *
 * @author makejava
 * @since 2020-03-02 10:57:54
 */
@Service("houseService")
public class HouseServiceImpl implements HouseService {
    @Resource
    private HouseDao houseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public House queryById(String id) {
        return this.houseDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<House> queryAllByLimit(String buildingId, String unitId,int offset, int limit) {
        return this.houseDao.queryAllByLimit(buildingId,unitId,offset, limit);
    }

    /**
     * 新增数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    @Override
    public House insert(House house) {
        this.houseDao.insert(house);
        return house;
    }

    /**
     * 修改数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    @Override
    public House update(House house) {
        this.houseDao.update(house);
        return this.queryById(house.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.houseDao.deleteById(id) > 0;
    }

    @Override
    public List<Integer> selectDistinctHouseNumber(String unitId) {
        return this.houseDao.selectDistinctHouseNumber(unitId);
    }

    @Override
    public House selectDistinctIdByNumber(int buildingNumber, int unitNumber, int houseNumber) {
        return this.houseDao.selectDistinctIdByNumber(buildingNumber,unitNumber,houseNumber);
    }

    //查询房屋危险性等级
    @Override
    public List<Map<String, Object>> riskChart(String buildingId, String unitId) {
        return this.houseDao.riskChart(buildingId, unitId);
    }
    @Override
    public List<House> riskTable(String buildingId, String unitId, int offset, int limit) {
        return this.houseDao.riskTable(buildingId, unitId, offset, limit);
    }

    @Override
    public List<Map<String, Object>> useChart(String buildingId, String unitId) {
        return this.houseDao.useChart(buildingId, unitId);
    }

    @Override
    public List<Map<String, Object>> saleChart(String buildingId, String unitId) {
        return this.houseDao.saleChart(buildingId,unitId);
    }

    @Override
    public List<House> saleTable(String buildingId, String unitId, int offset, int limit) {
        return this.houseDao.saleTable(buildingId, unitId, offset, limit);
    }


}