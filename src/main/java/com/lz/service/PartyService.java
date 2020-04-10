package com.lz.service;

import com.lz.entity.Party;
import java.util.List;

/**
 * (Party)表服务接口
 *
 * @author makejava
 * @since 2020-04-03 18:47:01
 */
public interface PartyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Party queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Party> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param party 实例对象
     * @return 实例对象
     */
    Party insert(Party party);

    /**
     * 修改数据
     *
     * @param party 实例对象
     * @return 实例对象
     */
    Party update(Party party);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    List<String> selectDistinctParty();

}