package com.daocers.domain;


import com.daocers.generator.annotation.Column;
import com.daocers.generator.annotation.Id;
import com.daocers.generator.annotation.Index;
import com.daocers.generator.annotation.Table;

/**
 * Created by daocers on 2016/9/27.
 */
@Table(value = "partner")
public class Partner {
    @Id
    @Index
    private Integer id;

    @Column
    private String name;

    @Column
    private String no;
}
