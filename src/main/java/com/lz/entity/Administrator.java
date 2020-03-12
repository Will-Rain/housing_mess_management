package com.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Administrator)实体类
 *
 * @author makejava
 * @since 2020-02-24 18:10:33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator implements Serializable {
    private static final long serialVersionUID = -71715841720317667L;
    /**
    * 用户名
    */
    private String name;
    /**
    * 密码
    */
    private String password;
    /**
    * 角色（超级管理员0、普通管理员1）
    */
    private Integer role;
}