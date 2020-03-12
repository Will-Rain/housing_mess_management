package com.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Building)实体类
 *
 * @author makejava
 * @since 2020-02-29 13:33:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building implements Serializable {
    private static final long serialVersionUID = -54619335313687178L;
    /**
    * 主码（建成时间+楼号）
    */
    private String id;
    /**
    * 楼宇号
    */
    private Integer buildingNumber;
    /**
    * 建筑时间
    */

    //    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8") //目前没发现有啥用
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date constructionTime;
    /**
    * 物业管理公司
    */
    private String propertyManagementCompany;
    /**
    * 负责人
    */
    private String buildingPrincipal;
    /**
    * 单元总数
    */
    private Integer unitCount;
}