package cn.gdut.myblog.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_comment")
public class SysComment {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long pId;
    private Long cId;
    private String articleTitle;
    private Long articleId;
    private String name;
    private String cName;
    private Date time;
    private String content;
    private String email;
    private String url;
    private Long sort;
    private String ip;
    private String device;
    private String address;


}
