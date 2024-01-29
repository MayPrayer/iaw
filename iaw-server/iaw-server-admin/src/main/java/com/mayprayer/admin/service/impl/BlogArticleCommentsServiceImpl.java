package com.mayprayer.admin.service.impl;

import com.mayprayer.admin.mapper.BlogArticleCommentsMapper;
import com.mayprayer.admin.service.BlogArticleCommentsService;
import com.mayprayer.admin.domain.BlogArticleComments;
import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogArticleCommentsServiceImpl  implements BlogArticleCommentsService {

    @Autowired
    private BlogArticleCommentsMapper articleCommentsMapper;


    @Override
    public List<BlogArticleComments> getArticleCommentsList(BlogArticleComments blogArticleComments) {
        return articleCommentsMapper.getArticleCommentsList(blogArticleComments);
    }

    @Override
    public R insertArticleComment(BlogArticleComments blogArticleComments) {
        int i = articleCommentsMapper.insertArticleComment(blogArticleComments);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R delArticleComments(List<Long> ids) {
        int i = articleCommentsMapper.batchDelArticleComments(ids);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R updateArticleComments(BlogArticleComments blogArticleComments) {
        int i = articleCommentsMapper.updateArticleComment(blogArticleComments);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }
}
