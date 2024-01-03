package com.mayprayer.admin.service;


import com.mayprayer.common.domain.BlogCategory;
import com.mayprayer.common.utils.response.R;
import java.util.List;

public interface BlogCategoryService {

    /**
     * 查询文章分类
     * @param blogCategory
     * @return
     */
    List<BlogCategory> getArticleCategoryList(BlogCategory blogCategory);


    /**
     * 插入文章分类
     * @param blogCategory
     * @return
     */
    R insertArticleCategory(BlogCategory blogCategory);

    /**
     * 删除文章分类
     * @param ids
     * @return
     */
    R  delArticleCategory(List<Long> ids);


    /**
     * 修改文章分类
     * @param blogCategory
     * @return
     */
    R updateArticleCategory(BlogCategory blogCategory);




}
