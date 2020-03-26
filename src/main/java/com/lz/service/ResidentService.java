package com.lz.service;

import com.lz.entity.Resident;

import java.util.List;
import java.util.Map;

/**
 * (Resident)表服务接口
 *
 * @author makejava
 * @since 2020-03-03 11:31:01
 */
public interface ResidentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Resident queryById(Integer id);


    Resident queryByIdentityCard(String identityCard);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Resident> queryAllByLimit(String buildingId, String unitId, String houseId, int offset, int limit);


    List<Resident> searchByName(String name, int offset, int limit);
    /**
     * 新增数据
     *
     * @param resident 实例对象
     * @return 实例对象
     */
    Resident insert(Resident resident);

    /**
     * 修改数据
     *
     * @param resident 实例对象
     * @return 实例对象
     */
    Resident update(Resident resident);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    // 查询户主信息
    List<Resident>  queryHeadOfHousehold(String buildingId, String unitId, String houseId);


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

    //获取居民数量
    String getResidentCount();
}