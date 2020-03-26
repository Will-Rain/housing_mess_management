package com.lz.dao;

import com.lz.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * (Administrator)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-24 18:10:33
 */
@Mapper
@Repository
public interface AdministratorDao {

    /**
     * 通过ID查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    Administrator queryByName(String name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Administrator> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param administrator 实例对象
     * @return 对象列表
     */
    List<Administrator> queryAll(Administrator administrator,@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param administrator 实例对象
     * @return 影响行数
     */
    int insert(Administrator administrator);

    /**
     * 修改数据
     *
     * @param administrator 实例对象
     * @return 影响行数
     */
    int update(Administrator administrator);

    /**
     * 通过主键删除数据
     *
     * @param name 主键
     * @return 影响行数
     */
    int deleteByName(String name);


    List<String> selectDistinctName();
    List<String> selectDistinctPassword(String name);
    List<String> selectDistinctRole(String name, String password);

    List<Map<String,Object>> selectToChart(Administrator administrator);
    //获取管理员数量
    String getAdminCount();
}