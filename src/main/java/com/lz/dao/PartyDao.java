package com.lz.dao;

import com.lz.entity.Party;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * (Party)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-03 18:47:01
 */
@Mapper
@Component
public interface PartyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Party queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Party> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param party 实例对象
     * @return 对象列表
     */
    List<Party> queryAll(Party party);

    /**
     * 新增数据
     *
     * @param party 实例对象
     * @return 影响行数
     */
    int insert(Party party);

    /**
     * 修改数据
     *
     * @param party 实例对象
     * @return 影响行数
     */
    int update(Party party);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<String> selectDistinctParty();

}