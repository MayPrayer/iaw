package com.mayprayer.admin.service.impl;

import com.mayprayer.admin.mapper.ArticleMapper;
import com.mayprayer.admin.service.ArticleService;
import com.mayprayer.common.domain.BlogArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<BlogArticle> getArticleList(BlogArticle blogArticle) {
        return articleMapper.getArticleList(blogArticle);
    }

    @Override
    public int insertArticle(BlogArticle blogArticle) {
        return 0;
    }

    @Override
    public int delArticle(List<Long> ids) {
        return 0;
    }

    @Override
    public int updateArticle(BlogArticle blogArticle) {
        return 0;
    }
}
