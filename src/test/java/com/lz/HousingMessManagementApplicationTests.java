package com.lz;

import com.lz.dao.HouseDao;
import com.lz.dao.ResidentDao;
import com.lz.dao.UnitDao;
import com.lz.entity.Building;
import com.lz.entity.House;
import com.lz.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Random;

@SpringBootTest
class HousingMessManagementApplicationTests {
    @Resource
    AdministratorService administratorService;

    @Resource
    BuildingService buildingService;

    @Resource
    UnitService unitService;


    @Resource
    HouseService houseService;

    @Resource
    ResidentService residentService;

    @Resource
    HouseDao houseDao;
    @Resource
    ResidentDao residentDao;
    @Resource
    UnitDao unitDao;

    @Resource
    PartyService partyService;

    @Test
    void contextLoads() {
        for (Building b : buildingService.queryAllByLimit(0, 10)) {
            System.out.println(b);
        }
    }

    @Test
    void test1() {
//        200108080102
        System.out.println(unitService.queryByNumber(2, 1));
    }

    @Test
    void test2() {
        System.out.println(houseService.queryAllByLimit("", "", 0, 50));

    }

    @Test
    void test3() {
        House house = houseDao.queryById(residentDao.queryById(92).getHouse().getId());
        System.out.println(house.getHouseNumber());
    }

    @Test
    void test4() {
            //创建随机对象
            Random random=new Random();

            //循环输出号码
            System.out.println("随机号码");
            for(int i=0;i<70;i++) {
                int num;
                System.out.print("1"+(random.nextInt(7)+3));
                do {
                    num=random.nextInt();
                    if(num>100000000&&num<1000000000) {
                        System.out.println(num);
                    }
                }while(!(num>100000000&&num<1000000000));
            }

    }

    @Test
    void test5(){
        System.out.println(residentService.statisticalCensusRegister(null,null));
    }


}
