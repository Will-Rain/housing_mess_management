package com.lz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Resident)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:31:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resident implements Serializable {
    private static final long serialVersionUID = -75233010393199187L;
    /**
    * 主码
    */
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别
    */
    private String gender;
    /**
    * 出生年月日
    */
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8") //用在传递json的时候
    private Date birthday;
    /**
    * 手机号码
    */
    private String telephoneNumber;
    /**
    * 身份证号
    */
    private String identityCard;
    /**
    * 户籍所在地
    */
    private String censusRegister;
    /**
    * 教育程度
    */
    private String educationalLevel;
    /**
    * 所属党派
    */
    private Party party;
    /**
    * 居民照片
    */
    private String photo;
    /**
    * 是否是户主
    */
    private Integer isHeadOfHousehold;
    /**
    * 所属房间
    */
    private House house;

}