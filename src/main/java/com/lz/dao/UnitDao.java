package com.lz.dao;

import com.lz.entity.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Unit)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-01 14:51:16
 */
@Mapper
@Repository
public interface UnitDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Unit queryById(String id);
    Unit queryByNumber(int buildingNumber, int unitNumber);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Unit> queryAllByLimit(@Param("buildingId") String buildingId,@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param unit 实例对象
     * @return 对象列表
     */
//    List<Unit> queryAll(Unit unit);

    /**
     * 新增数据
     *
     * @param unit 实例对象
     * @return 影响行数
     */
    int insert(Unit unit);

    /**
     * 修改数据
     *
     * @param unit 实例对象
     * @return 影响行数
     */
    int update(Unit unit);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    //查询某楼宇下的单元数量
    int selectUnitCount(String buildingId);

    List<Integer> selectDistinctUnitNumber(String buildingId);

    //查询住房数量
    List<Unit> houseCountTable(String buildingId, String unitId,
                               @Param("offset") int offset, @Param("limit") int limit);
}