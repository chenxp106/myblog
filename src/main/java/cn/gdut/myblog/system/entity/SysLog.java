package cn.gdut.myblog.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "tb_log")
@Data
public class SysLog implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
    private String operation;
    // 操作时间
    private Long time;
    private String method;
    private String params;
    private String ip;
    @TableField(value = "create_time")
    private Date createTime;
    private String location;

}
