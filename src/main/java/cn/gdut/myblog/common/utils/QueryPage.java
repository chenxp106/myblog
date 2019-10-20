package cn.gdut.myblog.common.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryPage implements Serializable {

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页的个数
     */
    private int limit;
}
