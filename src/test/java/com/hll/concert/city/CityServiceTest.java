package com.hll.concert.city;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hll.concert.common.BaseResp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceTest {

    @Autowired
    private CityService cityService;

    @Test
    @Rollback(false)
    public void insertTest() {
        InsertCityReq insertCityReq = new InsertCityReq();
        insertCityReq.setName("Yanchen");
        insertCityReq.setCountry("China");
        insertCityReq.setState("CA");
        BaseResp resp = this.cityService.insert(insertCityReq);
        System.out.println(JSONObject.toJSONString(resp, SerializerFeature.WRITE_MAP_NULL_FEATURES));
    }
}
