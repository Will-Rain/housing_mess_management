package com.lz.service.impl;

import com.lz.dao.HouseDao;
import com.lz.entity.House;
import com.lz.service.HouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * (House)表服务实现类
 *
 * @author makejava
 * @since 2020-03-02 10:57:54
 */
@Service("houseService")
public class HouseServiceImpl implements HouseService {
    @Resource
    private HouseDao houseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public House queryById(String id) {
        return this.houseDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<House> queryAllByLimit(String buildingId, String unitId,int offset, int limit) {
        return this.houseDao.queryAllByLimit(buildingId,unitId,offset, limit);
    }

    // 搜索住房
    @Override
    public List<House> searchHouseByLimit(String buildingId, String unitId, String houseType, int saleInfo, int offset, int limit) {
        return this.houseDao.searchHouseByLimit(buildingId, unitId, houseType, saleInfo, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    @Override
    public House insert(House house) {
        this.houseDao.insert(house);
        return house;
    }

    /**
     * 修改数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    @Override
    public House update(House house) {
        this.houseDao.update(house);
        return this.queryById(house.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.houseDao.deleteById(id) > 0;
    }

    @Override
    public List<Integer> selectDistinctHouseNumber(String unitId) {
        return this.houseDao.selectDistinctHouseNumber(unitId);
    }

    @Override
    public House selectDistinctIdByNumber(int buildingNumber, int unitNumber, int houseNumber) {
        return this.houseDao.selectDistinctIdByNumber(buildingNumber,unitNumber,houseNumber);
    }

    //查询房屋危险性等级
    @Override
    public List<Map<String, Object>> riskChart(String buildingId, String unitId) {
        return this.houseDao.riskChart(buildingId, unitId);
    }
    @Override
    public List<House> riskTable(String buildingId, String unitId, int offset, int limit) {
        return this.houseDao.riskTable(buildingId, unitId, offset, limit);
    }

    @Override
    public List<Map<String, Object>> useChart(String buildingId, String unitId) {
        return this.houseDao.useChart(buildingId, unitId);
    }

    @Override
    public List<Map<String, Object>> saleChart(String buildingId, String unitId) {
        return this.houseDao.saleChart(buildingId,unitId);
    }

    @Override
    public List<House> saleTable(String buildingId, String unitId, int offset, int limit) {
        return this.houseDao.saleTable(buildingId, unitId, offset, limit);
    }

    @Override
    public String getHouseCount() {
        return this.houseDao.getHouseCount();
    }

    @Override
    public List<Map<String, Object>> ResidentCheckInNumberChart() {
        List<Map<String, Object>> list =  houseDao.ResidentCheckInNumberChart();

        List<Integer> yearList = new ArrayList<Integer>();//存储年份

        //把key为0的map剔除掉
        Map<String, Object> zeroMap = new HashMap<String, Object>();
        int index = 0;
        for (Map<String, Object> map : list) {
            if(map.get("xData").toString().equals("0")){
//                System.out.println("==0==");
                zeroMap = map;
            }
            // Integer属于不可更改类型，而且Long和Integer没有任何继承关系，所以不能强转
            // 使用下面这种方法，注：java.lang.Number是Integer,Long的父类.
            Number number = (Number) map.get("xData");
            map.put("xData",number.toString()); // 转化为 string,后面排序用到

            yearList.add(number.intValue());
        }
        list.remove(zeroMap);
        yearList.remove(new Integer(0));


        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);

                    //  Collections.min(yearList) 获得list的最小值
        for(int i = Collections.min(yearList); i <= currentYear; i++){
            if(!yearList.contains(i)){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("xData",i);
                map.put("count",0);
                list.add(map);
            }
        }

        // list(map) 排序
        list.sort(new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                int map1value = Integer.parseInt(o1.get("xData")+"");
                int map2value = Integer.parseInt( o2.get("xData")+"");
                return map1value - map2value;
            }
        });


//        System.out.println(list);

        return list;
    }


}