package com.lz.service;

import com.lz.entity.House;

import java.util.List;
import java.util.Map;

/**
 * (House)表服务接口
 *
 * @author makejava
 * @since 2020-03-02 10:57:54
 */
public interface HouseService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    House queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<House> queryAllByLimit(String buildingId,String unitId,int offset, int limit);

    /**
     * 新增数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    House insert(House house);

    /**
     * 修改数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    House update(House house);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<Integer> selectDistinctHouseNumber(String unitId);

    House selectDistinctIdByNumber(int buildingNumber, int unitNumber, int houseNumber);

    //查询房屋危险性等级
    List<Map<String,Object>> riskChart(String buildingId, String unitId);
    List<House> riskTable(String buildingId, String unitId, int offset, int limit);

    //查询住房使用情况
    List<Map<String,Object>> useChart(String buildingId, String unitId);

    //查询住房出售情况
    List<Map<String,Object>> saleChart(String buildingId, String unitId);
    List<House> saleTable(String buildingId, String unitId, int offset, int limit);

    //获取住房数量
    String getHouseCount();
}