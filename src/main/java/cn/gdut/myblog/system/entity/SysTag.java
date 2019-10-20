package cn.gdut.myblog.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_tag")
public class SysTag implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
}
