package com.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Party)实体类
 *
 * @author makejava
 * @since 2020-04-03 18:47:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Party implements Serializable {
    private static final long serialVersionUID = 985381237398860046L;
    
    private Integer id;
    
    private String party;

}