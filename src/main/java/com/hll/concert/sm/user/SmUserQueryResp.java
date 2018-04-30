package com.hll.concert.sm.user;;

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
public class SmUserQueryResp extends PageResp {

    private List<SmUserVo> records;


}
