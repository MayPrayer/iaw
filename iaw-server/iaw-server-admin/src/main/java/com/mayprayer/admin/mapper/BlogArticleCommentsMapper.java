package com.mayprayer.admin.mapper;

import com.mayprayer.common.domain.BlogArticleComments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogArticleCommentsMapper {


    /**
     * 文章评论查询
     * @param blogArticleComments
     * @return 文章标题等数据
     */
    List<BlogArticleComments> getArticleCommentsList(BlogArticleComments blogArticleComments);


    /**
     * 文章评论删除
     * @param idList
     * @return 删除数量
     */
    int batchDelArticleComments(@Param("idList") List<Long> idList);

    /**
     * 文章评论修改
     * @param blogArticleComments
     * @return  修改数量
     */
    int updateArticleComment(BlogArticleComments blogArticleComments);



    /**
     *文章评论新增
     * @param blogArticleComments
     * @return 新增数量
     */
    int insertArticleComment(BlogArticleComments blogArticleComments);


}
