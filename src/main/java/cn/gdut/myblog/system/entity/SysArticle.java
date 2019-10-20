package cn.gdut.myblog.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_article")
public class SysArticle {

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



}
