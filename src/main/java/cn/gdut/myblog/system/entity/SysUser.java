package cn.gdut.myblog.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "tb_user")
public class SysUser implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String salt;
    private String avatar;
    private String introduce;
    private String remark;

}
