package com.mayprayer.admin.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mayprayer.admin.service.BlogTagService;
import com.mayprayer.admin.domain.BlogTag;
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
 *
 *文章标签相关接口
 */
@RestController
@Slf4j
@RequestMapping("/tag")
public class BlogTagController {

    @Autowired
    private BlogTagService tagService;


    /**
     * 标签查询
     * @param blogTag
     * @return
     */
    @GetMapping()
    public R listNavigation(@RequestBody(required = false) BlogTag blogTag){
        PageHelper.startPage(1,10);
        List<BlogTag> tagList = tagService.getTagList(blogTag);
        PageResult result = PageResult.buildPage((Page)tagList);
        return R.success(result);
    }


    /**
     * 标签 创建
     * @param blogTag
     * @return
     */
    @PostMapping()
    public R addNavigation(@RequestBody(required = false) BlogTag blogTag){
        return tagService.insertTag(blogTag);
    }


    /**
     * 标签删除
     * @param ids
     * @return
     */
    @DeleteMapping()
    public R delNavigation(@RequestParam String ids){
        List<Long> idList= Arrays.asList(ids).stream().map(e->Long.parseLong(e)).collect(Collectors.toList());
        return tagService.delTag(idList);
    }

    /**
     * 标签修改
     * @param blogTag
     * @return
     */
    @PutMapping()
    public R updateNavigation(@RequestBody(required = false) BlogTag blogTag){
        return tagService.updateTag(blogTag);
    }




}
