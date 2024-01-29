package com.mayprayer.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mayprayer.admin.service.BlogArticleCommentsService;
import com.mayprayer.admin.domain.BlogArticleComments;
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
 * 评论相关接口
 */
@RestController
@Slf4j
@RequestMapping("/comments")
public class BlogArticleCommentsController {

    @Autowired
    private BlogArticleCommentsService articleCommentsService;


    /**
     * 评论查询
     * @param blogArticleComments
     * @return
     */
    @GetMapping()
    public R listArticleComments(@RequestBody(required = false)BlogArticleComments blogArticleComments){
        PageHelper.startPage(1,10);
        List<BlogArticleComments> articleCommentsList = articleCommentsService.getArticleCommentsList(blogArticleComments);
        PageResult result = PageResult.buildPage((Page)articleCommentsList);
        return R.success(result);
    }


    /**
     * 评论创建
     * @param blogArticleComments
     * @return
     */
    @PostMapping()
    public R addArticleComment(@RequestBody(required = false) BlogArticleComments blogArticleComments){
        return articleCommentsService.insertArticleComment(blogArticleComments);
    }


    /**
     * 评论删除
     * @param ids
     * @return
     */
    @DeleteMapping()
    public R delArticleComments(@RequestParam String ids){
        List<Long> idList= Arrays.asList(ids).stream().map(e->Long.parseLong(e)).collect(Collectors.toList());
        return articleCommentsService.delArticleComments(idList);
    }

    /**
     * 评论修改
     * @param blogArticleComments
     * @return
     */
    @PutMapping()
    public R updateArticleComment(@RequestBody(required = false) BlogArticleComments blogArticleComments){
        return articleCommentsService.updateArticleComments(blogArticleComments);
    }



}
