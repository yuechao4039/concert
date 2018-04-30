package com.hll.concert.sm.user;

import com.hll.concert.common.Comment;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.NotNull;

/**
 * @author yuechao 2018/4/29
 */
@Data
public class SmUserDeleteRequest {

    @Comment("")
    @NotEmpty(message = "不能为空")
    private Integer id;

}
