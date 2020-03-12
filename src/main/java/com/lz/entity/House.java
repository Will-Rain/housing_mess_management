package com.lz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (House)实体类
 *
 * @author makejava
 * @since 2020-03-02 10:57:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class House implements Serializable {
    private static final long serialVersionUID = -14902955441753612L;
    /**
    * 主码（单元id+房间号）
    */
    private String id;
    /**
    * 房屋号码
    */
    private Integer houseNumber;
    /**
    * 户型
    */
    private String houseType;
    /**
    * 房屋面积
    */
    private Integer houseArea;
    /**
    * 住户人数
    */
    private Integer housePeopleCount;
    /**
    * 房屋危险性鉴定等级（A/B/C/D）
    */
    private String houseRiskLevel;
    /**
    * 房屋使用现状（自用、租借、闲置）
    */
    private String houseUseStatus;
    /**
    * 房间数量
    */
    private Integer roomCount;
    /**
    * 是否出售(0或1)
    */
    private Integer saleInfo;
    /**
    * 入住时间
    */
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8") //用在传递json的时候
    private Date checkInTime;
    /**
    * 所属单元
    */
    private Unit unit;
}