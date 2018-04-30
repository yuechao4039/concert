package com.hll.concert.sm.role;

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
public class SmRoleService {

    @Autowired
    private SmRoleDao smRoleDao;


    public BaseResp addSmRole(SmRoleAddRequest request) {
        SmRoleEntity entity = new SmRoleEntity();
        BeanUtils.copyProperties(request, entity);
        int result = this.smRoleDao.addSmRole(entity);
        if (result < 0) {
            throw new MyException("新增失败");
        }
        return new BaseResp();
    }

    public BaseResp updateSmRole(SmRoleUpdateRequest request) {
        SmRoleEntity entity = new SmRoleEntity();
        BeanUtils.copyProperties(request, entity);
        int result = this.smRoleDao.updateSmRole(entity);
        if (result < 0) {
            throw new MyException("更新失败");
        }
        return new BaseResp();
    }

    public BaseResp deleteSmRoleById(SmRoleDeleteRequest request) {
        SmRoleEntity entity = new SmRoleEntity();
        BeanUtils.copyProperties(request, entity);
        int result = this.smRoleDao.deleteSmRoleById(entity);
        if (result < 0) {
            throw new MyException("删除失败");
        }
        return new BaseResp();
    }


    public SmRoleOnlyResp findSmRoleById(SmRoleFindByIdRequest request) {
        SmRoleEntity entity = new SmRoleEntity();
        BeanUtils.copyProperties(request, entity);
        SmRoleEntity oEn = this.smRoleDao.findSmRoleById(entity);
        if (oEn == null) {
            throw new MyException("不存在");
        }
        SmRoleOnlyResp vo = new SmRoleOnlyResp();
        BeanUtils.copyProperties(oEn, vo);
        return vo;
    }

    public SmRoleQueryResp findAll(SmRoleQueryRequest request) {
        SmRoleEntity entity = new SmRoleEntity();
        BeanUtils.copyProperties(request, entity);

        Page page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<SmRoleEntity> list = this.smRoleDao.findAll(entity);
        List<SmRoleVo> records = list.stream().map(x -> {
            SmRoleVo vo = new SmRoleVo();
            BeanUtils.copyProperties(x, vo);
            return vo;
        }).collect(Collectors.toList());

        SmRoleQueryResp resp = new SmRoleQueryResp.SmRoleQueryRespBuilder().records(records).build();
        resp.setTotalCount(page.getTotal());
        return resp;
    }





}
