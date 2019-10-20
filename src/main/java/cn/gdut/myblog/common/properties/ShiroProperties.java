package cn.gdut.myblog.common.properties;

import lombok.Data;

@Data
public class ShiroProperties {

    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private long sessionTimeout;
    private int cookieTimeout;

}
