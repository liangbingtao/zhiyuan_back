package com.sanelee.zhiyuan.Util;

import com.sanelee.zhiyuan.Enums.ResultEnum;
import com.sanelee.zhiyuan.Model.Result;

/**
 * @Description Author neo
 * Date 2020/9/15 14:47
 */
public class ResultUtil {

    /*成功*/
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    /*成功*/
    public static Result success() {
        return success(null);
    }

    /*失败*/
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /*失败*/
    public static Result error(ResultEnum resultEnum){
        Result result=new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}
