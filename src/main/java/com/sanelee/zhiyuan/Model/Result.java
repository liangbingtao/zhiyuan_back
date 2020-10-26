package com.sanelee.zhiyuan.Model;

import lombok.Data;

/**
 * @Description Author neo
 * Date 2020/9/15 14:45
 */
@Data
public class Result<T> {

    /*返回提示码*/
    private Integer code;

    /* 提示信息*/
    private String msg;

    /*  具体数据*/
    private T data;
}
