package com.mayprayer.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mayprayer.admin.service.BlogCategoryService;
import com.mayprayer.admin.domain.BlogCategory;
import com.mayprayer.common.utils.page.PageResult;
import com.mayprayer.common.utils.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章分类相关接口
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class BlogCategoryController {


    @Autowired
    private BlogCategoryService blogCategoryService;


    /**
     * 文章分类查询
     * @param blogCategory
     * @return
     */
    @GetMapping()
    public R listArticleCategory(@RequestBody(required = false) BlogCategory blogCategory){
        PageHelper.startPage(1,10);
        List<BlogCategory> articleCategoryList = blogCategoryService.getArticleCategoryList(blogCategory);
        PageResult result = PageResult.buildPage((Page)articleCategoryList);
        return R.success(result);
    }







    /**
     * 文章分类 创建
     * @param blogCategory
     * @return
     */
    @PostMapping()
    public R addArticleCategory(@RequestBody(required = false)  BlogCategory blogCategory){
        return blogCategoryService.insertArticleCategory(blogCategory);
    }


    /**
     * 文章分类删除
     * @param ids
     * @return
     */
    @DeleteMapping()
    public R delArticleCategory(@RequestParam String ids){
        List<Long> idList= Arrays.asList(ids).stream().map(e->Long.parseLong(e)).collect(Collectors.toList());
        return blogCategoryService.delArticleCategory(idList);
    }

    /**
     * 文章分类修改
     * @param blogCategory
     * @return
     */
    @PutMapping()
    public R updateArticleCategory(@RequestBody(required = false) BlogCategory blogCategory){
        return blogCategoryService.updateArticleCategory(blogCategory);
    }





}
