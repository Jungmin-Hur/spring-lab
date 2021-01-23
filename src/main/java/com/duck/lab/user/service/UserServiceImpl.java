package com.duck.lab.user.service;

import com.duck.lab.user.model.User;
import org.springframework.stereotype.Service;

@Service("com.duck.springlab.user.service.UserService")
public class UserServiceImpl implements UserService{

    @Override
    public void createUser(String name, int no) {
        User user = new User();
        user.setName(name);
        user.setNo(no);
        System.out.println(user.toString());

        //skip next code...
    }
}
