package com.mayprayer.admin.service;


import com.mayprayer.admin.domain.dto.BlogArticleDto;
import com.mayprayer.common.utils.response.R;

import java.util.List;


public interface BlogArticleService {


    List<BlogArticleDto> getArticleList(BlogArticleDto blogArticleDto);

    R insertArticle(BlogArticleDto blogArticleDto);

    R  delArticle(List<Long> ids);

    R updateArticle(BlogArticleDto blogArticleDto);

}
