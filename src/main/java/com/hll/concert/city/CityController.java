package com.hll.concert.city;


import com.hll.concert.common.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/add", produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<BaseResp> insert(@Valid @RequestBody InsertCityReq ireq) {

        BaseResp baseResp = this.cityService.insert(ireq);

        return new ResponseEntity<BaseResp>(baseResp, HttpStatus.OK);
    }

}
