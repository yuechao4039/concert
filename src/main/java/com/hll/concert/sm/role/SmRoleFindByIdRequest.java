package com.hll.concert.sm.role;

import com.hll.concert.common.Comment;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuechao 2018/4/29
 */
@Data
public class SmRoleFindByIdRequest {

    @Comment("")
    @NotNull(message = "不能为空")
    private Integer id;


}
