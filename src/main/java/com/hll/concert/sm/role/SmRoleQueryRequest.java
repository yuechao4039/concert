package com.hll.concert.sm.role;

import com.hll.concert.common.Comment;
import com.hll.concert.common.PageReq;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yuechao 2018/4/29
 */
@Data
public class SmRoleQueryRequest extends PageReq {

    @Comment("角色编号")
    private String code;

    @Comment("角色名称")
    private String name;

    @Comment("角色等级")
    private Integer roleLevel;

    @Comment("角色类型")
    private Integer type;
}
