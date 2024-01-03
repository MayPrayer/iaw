package com.mayprayer.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mayprayer.admin.service.BlogArticleService;
import com.mayprayer.common.domain.dto.blog.article.BlogArticleDto;
import com.mayprayer.common.domain.vo.blog.article.ArticleVo;
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
 * 文章相关接口
 */
@RestController
@Slf4j
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private BlogArticleService blogArticleService;


    /**
     * 文章列表查询
     * @param articleVo
     * @return
     */
    @GetMapping()
    public R listArticle(@RequestBody(required = false) ArticleVo articleVo){
        PageHelper.startPage(1,10);
        BlogArticleDto blogArticleDto =BlogArticleDto.builder().build();
        BeanUtil.copyProperties(articleVo,blogArticleDto);
        List<BlogArticleDto> articleList = blogArticleService.getArticleList(blogArticleDto);
        PageResult  result = PageResult.buildPage((Page)articleList);
        return R.success(result);
    }


    /**
     * 文章创建
     * @param articleVo
     * @return
     */
    @PostMapping()
    public R addArticle(@RequestBody(required = false) ArticleVo articleVo){
        BlogArticleDto blogArticleDto =BlogArticleDto.builder().build();
        BeanUtil.copyProperties(articleVo,blogArticleDto);
        return blogArticleService.insertArticle(blogArticleDto);
    }


    /**
     * 文章删除
     * @param ids
     * @return
     */
    @DeleteMapping("")
    public R delArticle(@RequestParam String ids){
        List<Long> idList= Arrays.asList(ids).stream().map(e->Long.parseLong(e)).collect(Collectors.toList());
        return blogArticleService.delArticle(idList);
    }

    /**
     * 文章修改
     * @param articleVo
     * @return
     */
    @PutMapping("")
    public R updateArticle(@RequestBody(required = false) ArticleVo articleVo){
        BlogArticleDto blogArticleDto =BlogArticleDto.builder().build();
        BeanUtil.copyProperties(articleVo,blogArticleDto);
        return blogArticleService.updateArticle(blogArticleDto);
    }





}
