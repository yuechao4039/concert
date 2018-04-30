package com.hll.concert.sm.role;

import com.hll.concert.common.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuechao 2018/4/29
 */
@Data
@lombok.Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmRoleEntity {

    @Comment("角色ID")
    private Integer id;

    @Comment("角色编码")
    private String code;

    @Comment("角色名称")
    private String name;

    @Comment("角色级别")
    private Integer roleLevel;

    @Comment("角色类型")
    private Integer type;
}
