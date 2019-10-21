package cn.gdut.myblog.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("tb_article")
public class SysArticle implements Serializable {

    // 自增
    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;
    private String title;
    private String cover;
    private String author;
    private String content;

    private String contentMd;
    private String category;
    private String state;

    private Date publishTime;
    private Date editTime;
    private Date createTime;

    // 是否为数据库表字段
    @TableField(exist = false)
    private List<SysTag> tags;


}
