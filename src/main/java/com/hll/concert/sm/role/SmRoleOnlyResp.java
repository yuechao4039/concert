package com.hll.concert.sm.role;

import com.hll.concert.common.BaseResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuechao 2018/4/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmRoleOnlyResp extends BaseResp {

    private Integer id;

    private String code;

    private String name;

    private Integer roleLevel;

    private Integer type;
}
