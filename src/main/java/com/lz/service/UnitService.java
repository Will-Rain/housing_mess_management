package com.lz.service;

import com.lz.entity.Unit;

import java.util.List;

/**
 * (Unit)表服务接口
 *
 * @author makejava
 * @since 2020-03-01 14:51:16
 */
public interface UnitService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Unit queryById(String id);
    Unit queryByNumber(int buildingNumber, int unitNumber);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Unit> queryAllByLimit(String buildingId, int offset, int limit);

    /**
     * 新增数据
     *
     * @param unit 实例对象
     * @return 实例对象
     */
    Unit insert(Unit unit);

    /**
     * 修改数据
     *
     * @param unit 实例对象
     * @return 实例对象
     */
    Unit update(Unit unit);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<Integer> selectDistinctUnitNumber(String buildingId);

    //查询住房数量
    List<Unit> houseCountTable(String buildingId, String unitId, int offset, int limit);

}