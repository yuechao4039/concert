package com.hll.concert.sm.role;


import com.hll.concert.common.BaseResp;
import com.hll.concert.user.User;
import com.hll.concert.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmRoleServiceTest {

    @Autowired
    SmRoleService smRoleService;

    @Test
    @Rollback
    public void testAddSmRole() {
        SmRoleAddRequest request = new SmRoleAddRequest();
        request.setCode("1002");
        request.setName("admin");
        request.setRoleLevel(1);
        request.setType(2);
        BaseResp baseResp = this.smRoleService.addSmRole(request);
        Assert.assertEquals(0, baseResp.getICode());
    }

    @Test
    @Rollback
    public void testUpdateSmRole() {
        SmRoleUpdateRequest request = new SmRoleUpdateRequest();
        request.setId(1002);
        request.setCode("xx");
    }
}
