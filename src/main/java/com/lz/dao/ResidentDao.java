package com.lz.dao;

import com.lz.entity.Resident;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * (Resident)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-03 11:31:01
 */
@Mapper
@Component
public interface ResidentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Resident queryById(Integer id);

    Resident queryByIdentityCard(String identityCard);


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Resident> queryAllByLimit(@Param("buildingId") String buildingId, @Param("unitId") String unitId,
                                   @Param("houseId") String houseId, @Param("offset") int offset,
                                   @Param("limit") int limit);



    List<Resident> searchByName(@Param("name") String name, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param resident 实例对象
     * @return 对象列表
     */
//    List<Resident> queryAll(Resident resident);

    /**
     * 新增数据
     *
     * @param resident 实例对象
     * @return 影响行数
     */
    int insert(Resident resident);

    /**
     * 修改数据
     *
     * @param resident 实例对象
     * @return 影响行数
     */
    int update(Resident resident);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    // 查询户主信息
    List<Resident> queryHeadOfHousehold(String buildingId, String unitId, String houseId);

//   int queryResidentCount(String buildingId, String unitId, String houseId);


    //按性别统计
    List<Map<String,Object>> statisticalGender(String buildingId, String unitId);
    //按年龄统计
    List<Map<String,Object>> statisticalAge(String buildingId, String unitId);

    //按户籍统计
    List<Map<String,Object>> statisticalCensusRegister(String buildingId, String unitId);

    //按受教育程度统计
    List<Map<String,Object>> statisticalEducational(String buildingId, String unitId);
    //按所属党派统计
    List<Map<String,Object>> statisticalParty(String buildingId, String unitId);

    //人数统计
    List<Map<String,Object>> statisticalPeopleCount();

    //年龄对比统计
    List<Map<String,Object>> statisticalAgeCompare();

    //获取居民数量
    String getResidentCount();


}