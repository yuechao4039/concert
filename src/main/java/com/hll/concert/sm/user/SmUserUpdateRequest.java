package com.hll.concert.sm.user;

import com.hll.concert.common.Comment;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yuechao 2018/4/29
 */
@Data
public class SmUserUpdateRequest {

    @Comment("")
    @NotEmpty(message = "不能为空")
    private Integer id;

    @Comment("客户表id")
    @NotEmpty(message = "不能为空")
    private Integer organId;

    @Comment("")
    @NotNull(message = "不能为空")
    private String organCode;

    @Comment("")
    @NotNull(message = "不能为空")
    private String organName;

    @Comment("用户编号")
    @NotNull(message = "不能为空")
    private String code;

    @Comment("用户名")
    @NotNull(message = "不能为空")
    private String name;

    @Comment("用户密码")
    @NotNull(message = "不能为空")
    private String password;

    @Comment("密码加盐")
    @NotNull(message = "不能为空")
    private String salt;

    @Comment("用户手机号")
    @NotNull(message = "不能为空")
    private String phone;

    @Comment("用户邮箱")
    @NotNull(message = "不能为空")
    private String mail;

    @Comment("用户等级(等级小于等于客户表的等级,每个级别必须有)")
    @NotEmpty(message = "不能为空")
    private Integer level;

    @Comment("验证码通过redis实现")
    @NotNull(message = "不能为空")
    private String verifyCode;

    @Comment("密码错误次数通过redis实现")
    @NotEmpty(message = "不能为空")
    private Integer errorTime;

}