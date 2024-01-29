package com.mayprayer.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mayprayer.admin.mapper.BlogArticleMapper;
import com.mayprayer.admin.service.BlogArticleService;
import com.mayprayer.admin.domain.BlogArticle;
import com.mayprayer.admin.domain.dto.BlogArticleDto;
import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogBlogArticleServiceImpl implements BlogArticleService {

    @Autowired
    private BlogArticleMapper articleMapper;



    @Override
    public List<BlogArticleDto> getArticleList(BlogArticleDto blogArticleDto) {
        BlogArticle blogArticle = blogArticleDto.getBlogArticle();
        List<BlogArticle> articleList = articleMapper.getArticleList(blogArticle);
        return BeanUtil.copyToList(articleList, BlogArticleDto.class);
    }

    @Override
    public R insertArticle(BlogArticleDto blogArticleDto) {
        BlogArticle blogArticle = blogArticleDto.getBlogArticle();
        int i = articleMapper.insertArticle(blogArticle);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R delArticle(List<Long> ids) {
        int i = articleMapper.batchDelArticle(ids);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R updateArticle(BlogArticleDto blogArticleDto) {
        BlogArticle blogArticle = blogArticleDto.getBlogArticle();
        int i = articleMapper.updateArticle(blogArticle);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }


    /**
     * 校验分类以及标签是否存在 并且构造城完整对象
     * @return
     */
    public R check(BlogArticleDto blogArticleDto){
        return  null;
    }

}
