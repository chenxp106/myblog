package cn.gdut.myblog.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    /**
     * 封装展示数据
     * @param page 得到的页数信息
     * @return map
     */
    public Map<String, Object> getData(IPage<?> page){
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return data;
    }
}
