package com.hll.concert.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User findByName(String name) {
        return this.userMapper.findByName(name);
    }
}
