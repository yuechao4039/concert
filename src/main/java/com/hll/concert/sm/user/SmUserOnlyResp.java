package com.hll.concert.sm.user;

import com.hll.concert.common.Comment;
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
public class SmUserOnlyResp {

    @Comment("")
    private Integer id;

    @Comment("客户表id")
    private Integer organId;

    @Comment("")
    private String organCode;

    @Comment("")
    private String organName;

    @Comment("用户编号")
    private String code;

    @Comment("用户名")
    private String name;

    @Comment("用户密码")
    private String password;

    @Comment("密码加盐")
    private String salt;

    @Comment("用户手机号")
    private String phone;

    @Comment("用户邮箱")
    private String mail;

    @Comment("用户等级(等级小于等于客户表的等级,每个级别必须有)")
    private Integer level;

    @Comment("验证码通过redis实现")
    private String verifyCode;

    @Comment("密码错误次数通过redis实现")
    private Integer errorTime;

}
