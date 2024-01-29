package com.mayprayer.admin.service;

import com.mayprayer.admin.domain.BlogArticleComments;
import com.mayprayer.common.utils.response.R;

import java.util.List;

public interface BlogArticleCommentsService {

    /**
     * 查询评论
     * @param blogArticleComments
     * @return
     */
    List<BlogArticleComments> getArticleCommentsList(BlogArticleComments blogArticleComments);


    /**
     * 插入评论
     * @param blogArticleComments
     * @return
     */
    R insertArticleComment(BlogArticleComments blogArticleComments);

    /**
     * 删除评论
     * @param ids
     * @return
     */
    R  delArticleComments(List<Long> ids);


    /**
     * 修改评论
     * @param blogArticleComments
     * @return
     */
    R updateArticleComments(BlogArticleComments blogArticleComments);

}
