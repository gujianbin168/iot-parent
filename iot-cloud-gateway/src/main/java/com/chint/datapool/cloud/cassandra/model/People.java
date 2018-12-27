package com.chint.datapool.cloud.cassandra.model;

import java.util.UUID;
//import org.springframework.data.cassandra.core.mapping.PrimaryKey;
//import org.springframework.data.cassandra.core.mapping.Table;

 
//@Table
public class People {

//    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    private String name;

    private Integer age;

    public People(String name) {
        this.name = name;
        this.age = 18;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}