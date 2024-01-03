package com.mayprayer.admin.service;

import com.mayprayer.common.domain.BlogArticleContent;
import com.mayprayer.common.utils.response.R;

import java.util.List;

public interface BlogArticleContentService {

    /**
     * 查询文章内容
     * @param blogArticleContent
     * @return
     */
    List<BlogArticleContent> getArticleContentList(BlogArticleContent blogArticleContent);


    /**
     * 插入文章内容
     * @param blogArticleContent
     * @return
     */
    R insertArticleContent(BlogArticleContent blogArticleContent);

    /**
     * 删除文章内容
     * @param ids
     * @return
     */
    R  delArticleContent(List<Long> ids);


    /**
     * 修改文章内容
     * @param blogArticleContent
     * @return
     */
    R updateArticleContent(BlogArticleContent blogArticleContent);


}
