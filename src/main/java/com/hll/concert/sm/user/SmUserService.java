package com.hll.concert.sm.user;;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hll.concert.common.BaseResp;
import com.hll.concert.common.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author yuechao 2018/4/29
*/
@Service
@Transactional
public class SmUserService {

    @Autowired
    private SmUserDao smUserDao;


    public BaseResp addSmUser(SmUserAddRequest request) {
        SmUserEntity entity = new SmUserEntity();
        BeanUtils.copyProperties(request, entity);
        int result = this.smUserDao.addSmUser(entity);
        if (result < 0) {
        throw new MyException("新增失败");
        }
        return new BaseResp();
    }

    public BaseResp updateSmUser(SmUserUpdateRequest request) {
        SmUserEntity entity = new SmUserEntity();
        BeanUtils.copyProperties(request, entity);
        int result = this.smUserDao.updateSmUser(entity);
        if (result < 0) {
        throw new MyException("更新失败");
        }
        return new BaseResp();
    }

    public BaseResp deleteSmUserById(SmUserDeleteRequest request) {
        SmUserEntity entity = new SmUserEntity();
        BeanUtils.copyProperties(request, entity);
        int result = this.smUserDao.deleteSmUserById(entity);
        if (result < 0) {
        throw new MyException("删除失败");
        }
        return new BaseResp();
    }


    public SmUserOnlyResp findSmUserById(SmUserFindByIdRequest request) {
        SmUserEntity entity = new SmUserEntity();
        BeanUtils.copyProperties(request, entity);
        SmUserEntity oEn = this.smUserDao.findSmUserById(entity);
        if (oEn == null) {
        throw new MyException("不存在");
        }
        SmUserOnlyResp vo = new SmUserOnlyResp();
        BeanUtils.copyProperties(oEn, vo);
        return vo;
    }

    public SmUserQueryResp findAll(SmUserQueryRequest request) {
        SmUserEntity entity = new SmUserEntity();
        BeanUtils.copyProperties(request, entity);

        Page page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<SmUserEntity> list = this.smUserDao.findAll(entity);
        List<SmUserVo> records = list.stream().map(x -> {
            SmUserVo vo = new SmUserVo();
            BeanUtils.copyProperties(x, vo);
            return vo;
            }).collect(Collectors.toList());

        SmUserQueryResp resp = new SmUserQueryResp.SmUserQueryRespBuilder().records(records).build();
        resp.setTotalCount(page.getTotal());
        return resp;
    }

}
