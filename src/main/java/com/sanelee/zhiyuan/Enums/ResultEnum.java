package com.sanelee.zhiyuan.Enums;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @Description Author neo
 * Date 2020/9/15 15:57
 */
public enum ResultEnum {
    SYS_ERROR(100,"服务器异常"),
    MESSAGE_ERROR(101,"验证码获取异常"),
    USER_NOTFOUND(102,"未找到用户"),
    SCHOOL_NOTFOUND(103,"未找到该学校"),
    SCORE_NOTBLANK(104,"分数不能为空"),
    PROVINCE_NOTBLANK(105,"省份分不能为空"),
    SORT_NOTBLANK(106,"文理科不能为空"),
    USERNAME_EXISTED(107,"该用户名已存在！！！"),
    PHONE_REGISTED(108,"该手机号已注册！！！"),
    PASSWORD_NOTSAME(109,"密码不一致！！！"),

        ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
