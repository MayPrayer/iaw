package com.mayprayer.admin.controller;

import com.mayprayer.admin.service.BlogArticleContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章内容相关接口
 */
@RestController
@Slf4j
@RequestMapping("/content")
public class BlogArticleContentController {

    @Autowired
    private BlogArticleContentService articleContentService;








}
