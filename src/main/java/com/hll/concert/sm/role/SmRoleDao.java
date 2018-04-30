package com.hll.concert.sm.role;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yuechao 2018/4/29
 */

@Component
@Mapper
public interface SmRoleDao {


    int addSmRole(SmRoleEntity entity);

    int updateSmRole(SmRoleEntity entity);

    int deleteSmRoleById(SmRoleEntity entity);

    SmRoleEntity findSmRoleById(SmRoleEntity entity);

    List<SmRoleEntity> findAll(SmRoleEntity entity);
}
