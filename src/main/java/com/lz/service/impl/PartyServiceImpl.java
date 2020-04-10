package com.lz.service.impl;

import com.lz.entity.Party;
import com.lz.dao.PartyDao;
import com.lz.service.PartyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Party)表服务实现类
 *
 * @author makejava
 * @since 2020-04-03 18:47:01
 */
@Service("partyService")
public class PartyServiceImpl implements PartyService {
    @Resource
    private PartyDao partyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Party queryById(Integer id) {
        return this.partyDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Party> queryAllByLimit(int offset, int limit) {
        return this.partyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param party 实例对象
     * @return 实例对象
     */
    @Override
    public Party insert(Party party) {
        this.partyDao.insert(party);
        return party;
    }

    /**
     * 修改数据
     *
     * @param party 实例对象
     * @return 实例对象
     */
    @Override
    public Party update(Party party) {
        this.partyDao.update(party);
        return this.queryById(party.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.partyDao.deleteById(id) > 0;
    }

    @Override
    public List<String> selectDistinctParty() {
        return this.partyDao.selectDistinctParty();
    }
}