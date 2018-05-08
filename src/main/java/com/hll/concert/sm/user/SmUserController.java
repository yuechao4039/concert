package com.hll.concert.sm.user;

import com.hll.concert.common.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuechao 2018/5/8
 */
@RestController
@RequestMapping(value = {"/api/user"})
public class SmUserController {

    @Autowired
    private SmUserService smUserService;

    @ResponseBody
    @RequestMapping(value = {"/add"})
    public ResponseEntity<BaseResp> addUser(@RequestBody SmUserAddRequest req) {
        BaseResp baseResp = smUserService.addSmUser(req);
        return new ResponseEntity<>(baseResp, HttpStatus.OK);
    }
}
