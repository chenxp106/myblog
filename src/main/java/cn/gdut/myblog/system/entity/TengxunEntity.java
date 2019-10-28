package cn.gdut.myblog.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TengxunEntity implements Serializable {

    private String key;
    private String name;
    private String type;
    private long size;
    private String url;


}
