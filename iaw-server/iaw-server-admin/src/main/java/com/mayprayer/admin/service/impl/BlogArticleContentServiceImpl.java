package com.mayprayer.admin.service.impl;

import com.mayprayer.admin.mapper.BlogArticleContentMapper;
import com.mayprayer.admin.service.BlogArticleContentService;
import com.mayprayer.admin.domain.BlogArticleContent;
import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogArticleContentServiceImpl implements BlogArticleContentService {

    @Autowired
    private BlogArticleContentMapper articleContentMapper;

    @Override
    public List<BlogArticleContent> getArticleContentList(BlogArticleContent blogArticleContent) {
        return  articleContentMapper.getArticleContentList(blogArticleContent);
    }

    @Override
    public R insertArticleContent(BlogArticleContent blogArticleContent) {
        int i = articleContentMapper.insertArticleContent(blogArticleContent);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R delArticleContent(List<Long> ids) {
        int i = articleContentMapper.batchDelArticleContent(ids);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R updateArticleContent(BlogArticleContent blogArticleContent) {
        int i = articleContentMapper.updateArticleContent(blogArticleContent);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }


}
