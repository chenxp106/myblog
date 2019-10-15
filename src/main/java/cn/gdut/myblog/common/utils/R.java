package cn.gdut.myblog.common.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class R<T> implements Serializable {

    @Getter
    @Setter
    private int code = 200;

    @Getter
    @Setter
    private Object msg = "success";

    @Setter
    @Getter
    private T data;

    public R(T data) {
        this.data = data;
    }

    public R(Object msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public R() {
    }
}
