package cn.gdut.myblog.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_login_log")
public class SysLoginLog {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String userNmae;

    @TableField("location")
    private String location;

    @TableField("create_time")
    private String createTime;

    @TableField("device")
    private String device;

}
