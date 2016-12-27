package com.daocers.domain;


import com.daocers.generator.annotation.Column;
import com.daocers.generator.annotation.Id;
import com.daocers.generator.annotation.Index;
import com.daocers.generator.annotation.Table;

import java.lang.reflect.Field;

/**
 * Created by daocers on 2016/9/27.
 */
@Table("child")
public class Child {
    @Id
    @Index
    private Integer id;
    @Column
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static void main(String[] args){
        Child child = new Child();
        child.setId(1);
        child.setName("allen");
        Field[] fields = child.getClass().getDeclaredFields();
        for(Field field: fields){
            field.getName();
        }
    }
}
