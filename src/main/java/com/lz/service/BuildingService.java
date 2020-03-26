package com.lz.service;

import com.lz.entity.Building;

import java.util.List;

/**
 * (Building)表服务接口
 *
 * @author makejava
 * @since 2020-02-29 13:33:16
 */
public interface BuildingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Building queryById(String id);
    Building queryByNumber(int buildingNumber);


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Building> queryAllByLimit(int offset, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param building 实例对象
     * @return 对象列表
     */
    List<Building> queryAll(Building building,int offset,int limit);


    /**
     * 新增数据
     *
     * @param building 实例对象
     * @return 实例对象
     */
    Building insert(Building building);

    /**
     * 修改数据
     *
     * @param building 实例对象
     * @return 实例对象
     */
    Building update(Building building);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
    //获得唯一的楼宇号
    List<Integer> selectDistinctNumber();

    //获取楼宇数量
    String getBuildingCount();

}