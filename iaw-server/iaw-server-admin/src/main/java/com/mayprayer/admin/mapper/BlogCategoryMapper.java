package com.mayprayer.admin.mapper;


import com.mayprayer.admin.domain.BlogCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogCategoryMapper {


    /**
     * 文章分类查询
     * @param blogCategory
     * @return 文章标题等数据
     */
    List<BlogCategory> getArticleCategoryList( BlogCategory blogCategory);


    /**
     * 文章分类删除
     * @param idList
     * @return 删除数量
     */
    int batchDelArticleCategory(@Param("idList") List<Long> idList);

    /**
     * 文章分类修改
     * @param blogCategory
     * @return  修改数量
     */
    int updateArticleCategory( BlogCategory blogCategory);



    /**
     *文章分类新增
     * @param blogCategory
     * @return 新增数量
     */
    int insertArticleCategory( BlogCategory blogCategory);

}
