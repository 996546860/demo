package com.example.demo.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.function.Supplier;

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class User{


    private Integer id;

    private String name;

    public  Integer getId() {
        return this.id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

}