package cn.gdut.myblog.common.exception;

import lombok.Getter;
import lombok.Setter;

public class GlobalException extends RuntimeException {

    @Setter
    @Getter
    private String msg;

    public GlobalException(String message) {
        super(message);
        this.msg = message;
    }
}
