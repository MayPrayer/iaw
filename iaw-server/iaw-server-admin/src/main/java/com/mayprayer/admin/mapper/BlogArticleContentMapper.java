package com.mayprayer.admin.mapper;


import com.mayprayer.admin.domain.BlogArticleContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogArticleContentMapper {


    /**
     * 文章内容查询
     * @param blogArticleContent
     * @return 文章标题等数据
     */
    List<BlogArticleContent> getArticleContentList(BlogArticleContent blogArticleContent);


    /**
     * 文章内容删除
     * @param idList
     * @return 删除数量
     */
    int batchDelArticleContent(@Param("idList") List<Long> idList);

    /**
     * 文章内容修改
     * @param blogArticleContent
     * @return  修改数量
     */
    int updateArticleContent(BlogArticleContent blogArticleContent);



    /**
     *文章内容新增
     * @param blogArticleContent
     * @return 新增数量
     */
    int insertArticleContent(BlogArticleContent blogArticleContent);


}
