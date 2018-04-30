package com.hll.concert.sm.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* @author yuechao 2018/4/29
*/
@Component
@Mapper
public interface SmUserDao {

    int addSmUser(SmUserEntity entity);

    int updateSmUser(SmUserEntity entity);

    int deleteSmUserById(SmUserEntity entity);

    SmUserEntity findSmUserById(SmUserEntity entity);

    List<SmUserEntity> findAll(SmUserEntity entity);

}
