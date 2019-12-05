package com.hutu.common.entity;

import com.hutu.common.enums.ResultCode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 统一前端返回对象
 *
 * @author hutu
 * @date 2018/7/19 17:00
 */
public class R implements Serializable {

    private static final long serialVersionUID = 1L;
    public int code;
    public String msg;
    public HashMap<String, Object> data;

    public R() {
        this.code = ResultCode.OK.code;
        this.msg = ResultCode.OK.desc;
        this.data = new HashMap<>();
    }

    public static R error() {
        return error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    public static R error(ResultCode resultCode) {
        R r = new R();
        r.code = resultCode.code;
        r.msg = resultCode.desc;
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.code = ResultCode.INTERNAL_SERVER_ERROR.code;
        r.msg = msg;
        return r;
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.code = code;
        r.msg = msg;
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R ok(ResultCode resultCode) {
        R r = new R();
        r.code = resultCode.code;
        r.msg = resultCode.desc;
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.msg = msg;
        return r;
    }

    public static R ok(int code, String msg) {
        R r = new R();
        r.code = code;
        r.msg = msg;
        return r;
    }

    public R put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
