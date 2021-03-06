package com.lz.dao;

import com.lz.entity.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Building)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-29 13:33:16
 */
@Mapper
@Repository
public interface BuildingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Building queryById(String id);
    Building queryByNumber(int buildingNumber);//根据number 查询id，填充到隐藏域

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Building> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param building 实例对象
     * @return 对象列表
     */
    List<Building> queryAll(Building building,@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param building 实例对象
     * @return 影响行数
     */
    int insert(Building building);

    /**
     * 修改数据
     *
     * @param building 实例对象
     * @return 影响行数
     */
    int update(Building building);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<Integer> selectDistinctNumber();
    //获取楼宇数量
    String getBuildingCount();
}