package com.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * (Unit)实体类
 *
 * @author makejava
 * @since 2020-03-01 14:51:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unit implements Serializable {
    private static final long serialVersionUID = 862775362261156762L;
    /**
    * 主码（楼宇号+单元号）
    */
    private String id;
    /**
    * 单元号
    */
    private Integer unitNumber;
    /**
    * 层数
    */
    private Integer layerCount;
    /**
    * 房屋数量
    */
    private Integer houseCount;
    /**
    * 电梯数量
    */
    private Integer elevatorCount;
    /**
    * 所属楼宇
    */
    @Resource
    private Building building;
}