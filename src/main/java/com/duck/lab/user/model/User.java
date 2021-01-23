package com.duck.lab.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Repository
//@Getter
//@Setter
//@ToString
@Data //@Data를 하거나 @ToString을 적용하면 toString을 별도 구현하지 않아도 출력할 수 있음
public class User {
    private String name;
    private int no;

    //apply Lombok
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getNo() {
//        return no;
//    }
//
//    public void setNo(int no) {
//        this.no = no;
//    }
}
