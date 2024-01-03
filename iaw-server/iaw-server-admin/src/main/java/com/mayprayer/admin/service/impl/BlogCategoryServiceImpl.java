package com.mayprayer.admin.service.impl;

import com.mayprayer.admin.mapper.BlogCategoryMapper;
import com.mayprayer.admin.service.BlogCategoryService;
import com.mayprayer.common.domain.BlogCategory;
import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper categoryMapper;



    @Override
    public List<BlogCategory> getArticleCategoryList(BlogCategory blogCategory) {
       return categoryMapper.getArticleCategoryList(blogCategory);
    }

    @Override
    public R insertArticleCategory(BlogCategory blogCategory) {
        int i = categoryMapper.insertArticleCategory(blogCategory);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R delArticleCategory(List<Long> ids) {
        int i = categoryMapper.batchDelArticleCategory(ids);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R updateArticleCategory(BlogCategory blogCategory) {
        int i = categoryMapper.updateArticleCategory(blogCategory);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }
}
