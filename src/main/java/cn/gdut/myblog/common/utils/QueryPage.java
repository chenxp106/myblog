package cn.gdut.myblog.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
