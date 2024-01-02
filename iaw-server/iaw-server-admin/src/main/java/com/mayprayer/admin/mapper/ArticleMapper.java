package com.mayprayer.admin.mapper;

import com.mayprayer.common.domain.BlogArticle;

import java.util.List;

public interface ArticleMapper {


    List<BlogArticle> getArticleList(BlogArticle blogArticle);

}
