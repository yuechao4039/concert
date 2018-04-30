package com.hll.concert.sm.role;

import com.hll.concert.common.Comment;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yuechao 2018/4/29
 */
@Data
public class SmRoleAddRequest {

    @Comment("角色编号")
    @NotEmpty(message = "不能为空")
    private String code;

    @Comment("角色名称")
    @NotEmpty(message = "不能为空")
    private String name;

    @Comment("角色等级")
    @NotNull(message = "不能为空")
    private Integer roleLevel;

    @Comment("角色类型")
    @NotNull(message = "不能为空")
    private Integer type;
}
