package com.duck.lab.user.model;

import org.springframework.stereotype.Repository;

@Repository
public class User {
    private String name;
    private int no;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
