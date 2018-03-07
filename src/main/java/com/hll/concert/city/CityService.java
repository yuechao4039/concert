package com.hll.concert.city;


import com.hll.concert.common.BaseResp;
import com.hll.concert.common.RowsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;


    public BaseResp insert(InsertCityReq insertCityReq) {
        City city = new City();
        city.setName(insertCityReq.getName());
        city.setState(insertCityReq.getState());
        city.setCountry(insertCityReq.getCountry());
        int i = this.cityMapper.insert(city);

        return new BaseResp();
    }

    public BaseResp update(UpdateCityReq updateCityReq) {
        City city = new City();
        city.setName(updateCityReq.getName());
        city.setState(updateCityReq.getState());
        city.setCountry(updateCityReq.getCountry());
        int i = this.cityMapper.update(city);

        return new BaseResp();
    }


    public BaseResp delete(String id) {
        int i = this.cityMapper.delete(Long.valueOf(id));
        return new BaseResp();
    }

    public BaseResp findAll() {
        RowsResp<QueryCityResp> rowsResp = new RowsResp<QueryCityResp>();

        List<City> list = this.cityMapper.findAll();
        for (City city : list) {
            QueryCityResp irep = new QueryCityResp();
            irep.setId(city.getId());
            irep.setName(city.getName());
            irep.setCountry(city.getCountry());
            irep.setState(city.getState());
            rowsResp.add(irep);
        }

        return rowsResp;

    }

}
