package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.SysArticle;
import cn.gdut.myblog.system.mapper.ArticleMapper;
import cn.gdut.myblog.system.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, SysArticle> implements ArticleService {
}
