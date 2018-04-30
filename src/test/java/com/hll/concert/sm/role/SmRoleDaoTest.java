package com.hll.concert.sm.role;


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
public class SmRoleDaoTest {

    @Autowired
    private SmRoleDao smRoleDao;

    @Test
    @Rollback()
    public void testAddSmRole(){

        SmRoleEntity entity = new SmRoleEntity.SmRoleEntityBuilder()
                .code("1001").name("develop").roleLevel(1).type(1)
                .build();
        int result = smRoleDao.addSmRole(entity);
        Assert.assertEquals(1, result);
        Assert.assertNotNull(entity.getId());
    }

    @Test
    @Rollback()
    public void testUpdateSmRole() {
        SmRoleEntity entity = new SmRoleEntity.SmRoleEntityBuilder()
                .code("1001").name("develop").roleLevel(1).type(1)
                .build();
        smRoleDao.addSmRole(entity);

        SmRoleEntity updateEn = new SmRoleEntity.SmRoleEntityBuilder()
                .id(entity.getId())
                .code("1001").name("develop").roleLevel(1).type(1)
                .build();
        int result = smRoleDao.updateSmRole(updateEn);
        Assert.assertEquals(1, result);
    }

    @Test
    @Rollback()
    public void testDeleteSmRoleById() {

        SmRoleEntity entity = new SmRoleEntity.SmRoleEntityBuilder()
                .code("1001").name("develop").roleLevel(1).type(1)
                .build();
        smRoleDao.addSmRole(entity);

        SmRoleEntity deEn = new SmRoleEntity.SmRoleEntityBuilder().id(entity.getId()).build();
        int result = smRoleDao.deleteSmRoleById(deEn);
        Assert.assertEquals(1, result);

    }


    @Test
    @Rollback
    public void testFindSmRoleById() {

        SmRoleEntity entity = new SmRoleEntity.SmRoleEntityBuilder()
                .code("1001").name("develop").roleLevel(1).type(1)
                .build();
        smRoleDao.addSmRole(entity);

        SmRoleEntity qEn = new SmRoleEntity.SmRoleEntityBuilder().id(entity.getId()).build();
        SmRoleEntity oEn = smRoleDao.findSmRoleById(qEn);
        Assert.assertNotNull(oEn);
    }

    @Test
    @Rollback
    public void testFindAll() {
        SmRoleEntity entity = new SmRoleEntity.SmRoleEntityBuilder()
                .code("1001").name("develop").roleLevel(1).type(1)
                .build();
        smRoleDao.addSmRole(entity);
        SmRoleEntity oEn = new SmRoleEntity.SmRoleEntityBuilder()
                .code("1001").name("develop").roleLevel(1).type(1)
                .build();
        smRoleDao.addSmRole(entity);
        SmRoleEntity inEn = new SmRoleEntity.SmRoleEntityBuilder().code("1001")
                .build();

        List<SmRoleEntity> list = this.smRoleDao.findAll(inEn);
        Assert.assertEquals(2, list.size());
    }




}
