package com.mayprayer.admin.mapper;

import com.mayprayer.admin.domain.BlogArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogArticleMapper {

    /**
     * 文章查询
     * @param blogArticle
     * @return 文章标题等数据
     */
    List<BlogArticle> getArticleList(BlogArticle blogArticle);


    /**
     * 文章删除
     * @param idList
     * @return 删除数量
     */
    int batchDelArticle(@Param("idList") List<Long> idList);

    /**
     * 文章修改
     * @param blogArticle
     * @return  修改数量
     */
    int updateArticle(BlogArticle blogArticle);

    /**
     *
     * @param blogArticle
     * @return 新增数量
     */
    int insertArticle(BlogArticle blogArticle);


}
