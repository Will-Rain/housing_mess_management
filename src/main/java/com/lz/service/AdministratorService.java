package com.lz.service;

import com.lz.entity.Administrator;

import java.util.List;
import java.util.Map;

/**
 * (Administrator)表服务接口
 *
 * @author makejava
 * @since 2020-02-24 18:10:33
 */
public interface AdministratorService {

    /**
     * 通过ID查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    Administrator queryByName(String name);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param administrator 实例对象
     * @return 对象列表
     */
    List<Administrator> queryAll(Administrator administrator,int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Administrator> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param administrator 实例对象
     * @return 实例对象
     */
    Administrator insert(Administrator administrator);

    /**
     * 修改数据
     *
     * @param administrator 实例对象
     * @return 实例对象
     */
    Administrator update(Administrator administrator);

    /**
     * 通过主键删除数据
     *
     * @param name 主键
     * @return 是否成功
     */
    boolean deleteByName(String name);

    List<String> selectDistinctName();
    List<String> selectDistinctPassword(String name);
    List<String> selectDistinctRole(String name, String password);

    List<Map<String,Object>> selectToChart(Administrator administrator);

}