package com.hll.concert.sm.role;

import com.hll.concert.common.PageResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yuechao 2018/4/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmRoleQueryResp extends PageResp {

    private List<SmRoleVo> records;


}
