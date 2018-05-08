package com.hll.concert.sm.role;


import com.hll.concert.sm.user.SmUserDao;
import com.hll.concert.sm.user.SmUserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SmUserDaoTest {

    @Autowired
    private SmUserDao smUserDao;

    @Test
    @Rollback()
    public void testAddSmRole(){
        SmUserEntity entity = new SmUserEntity();
        entity.setCode("xxx");

        int result = smUserDao.addSmUser(entity);
        Assert.assertEquals(1, result);
        Assert.assertNotNull(entity.getId());
    }






}
