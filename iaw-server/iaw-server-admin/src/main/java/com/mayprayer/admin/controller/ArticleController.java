package com.mayprayer.admin.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mayprayer.admin.service.ArticleService;
import com.mayprayer.common.domain.BlogArticle;
import com.mayprayer.common.utils.page.PageResult;
import com.mayprayer.common.utils.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章相关接口
 */
@RestController
@Slf4j
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;


    @GetMapping("/list")
    public R list(@RequestBody(required = false) BlogArticle article){
        PageHelper.startPage(1,10);
        List<BlogArticle> articleList = articleService.getArticleList(article);
        PageResult  result = PageResult.buildPage((Page)articleList);
        return R.success(result);
    }


}
