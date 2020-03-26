package com.lz.service.impl;

import com.lz.dao.AdministratorDao;
import com.lz.entity.Administrator;
import com.lz.service.AdministratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Administrator)表服务实现类
 *
 * @author makejava
 * @since 2020-02-24 18:10:33
 */
@Service("administratorService")
public class AdministratorServiceImpl implements AdministratorService {
    @Resource
    private AdministratorDao administratorDao;

    /**
     * 通过ID查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    @Override
    public Administrator queryByName(String name) {
        return this.administratorDao.queryByName(name);
    }

    @Override
    public List<Administrator> queryAll(Administrator administrator,int offset,int limit) {
        return this.administratorDao.queryAll(administrator,offset,limit);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Administrator> queryAllByLimit(int offset, int limit) {
        return this.administratorDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param administrator 实例对象
     * @return 实例对象
     */
    @Override
    public Administrator insert(Administrator administrator) {
        this.administratorDao.insert(administrator);
        return administrator;
    }

    /**
     * 修改数据
     *
     * @param administrator 实例对象
     * @return 实例对象
     */
    @Override
    public Administrator update(Administrator administrator) {
        this.administratorDao.update(administrator);
        return this.queryByName(administrator.getName());
    }

    /**
     * 通过主键删除数据
     *
     * @param name 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByName(String name) {
        return this.administratorDao.deleteByName(name) > 0;
    }

    @Override
    public List<String> selectDistinctName() {
        return this.administratorDao.selectDistinctName();
    }

    @Override
    public List<String> selectDistinctPassword(String name) {
        return this.administratorDao.selectDistinctPassword(name);
    }

    @Override
    public List<String> selectDistinctRole(String name, String password) {
        return this.administratorDao.selectDistinctRole(name, password);
    }

    @Override
    public List<Map<String, Object>> selectToChart(Administrator administrator) {
        return this.administratorDao.selectToChart(administrator);
    }

    @Override
    public String getAdminCount() {
        return this.administratorDao.getAdminCount();
    }
}