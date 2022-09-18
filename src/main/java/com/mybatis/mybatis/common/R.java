package com.mybatis.mybatis.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
public class R<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> com.mybatis.mybatis.common.R<T> success(T object) {
        com.mybatis.mybatis.common.R<T> r = new com.mybatis.mybatis.common.R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> com.mybatis.mybatis.common.R<T> error(String msg) {
        com.mybatis.mybatis.common.R r = new com.mybatis.mybatis.common.R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public com.mybatis.mybatis.common.R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
