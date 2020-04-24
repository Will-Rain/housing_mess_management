package com.lz.dao;

import com.lz.entity.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * (House)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-02 10:57:54
 */
@Mapper
@Component
public interface HouseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    House queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<House> queryAllByLimit(@Param("buildingId") String buildingId,
                                @Param("unitId") String unitId,
                                @Param("offset") int offset,
                                @Param("limit") int limit);

    List<House> searchHouseByLimit(@Param("buildingId") String buildingId,
                                   @Param("unitId") String unitId,
                                   @Param("houseType") String houseType,
                                   @Param("saleInfo") int saleInfo,
                                   @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param house 实例对象
     * @return 对象列表
     */
//    List<House> queryAll(House house);

    /**
     * 新增数据
     *
     * @param house 实例对象
     * @return 影响行数
     */
    int insert(House house);

    /**
     * 修改数据
     *
     * @param house 实例对象
     * @return 影响行数
     */
    int update(House house);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<Integer> selectDistinctHouseNumber(String unitId);

    House selectDistinctIdByNumber(int buildingNumber, int unitNumber, int houseNumber);

    //查询房屋危险性等级
    List<Map<String,Object>> riskChart(String buildingId, String unitId);
    List<House> riskTable(@Param("buildingId") String buildingId, @Param("unitId") String unitId,
                             @Param("offset") int offset, @Param("limit") int limit);

    //查询住房使用情况
    List<Map<String,Object>> useChart(String buildingId, String unitId);

    //查询住房出售情况
    List<Map<String,Object>> saleChart(String buildingId, String unitId);
    List<House> saleTable(@Param("buildingId") String buildingId, @Param("unitId") String unitId,
                          @Param("offset") int offset, @Param("limit") int limit);

    //获取住房数量
    String getHouseCount();


    //近十年居民数量变化图
    List<Map<String,Object>> ResidentCheckInNumberChart();
}