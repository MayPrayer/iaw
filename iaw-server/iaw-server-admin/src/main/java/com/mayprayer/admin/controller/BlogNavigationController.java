package com.mayprayer.admin.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mayprayer.admin.service.BlogNavigationService;
import com.mayprayer.admin.domain.BlogNavigation;
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
 *导航栏相关接口
 */
@RestController
@Slf4j
@RequestMapping("/navigation")
public class BlogNavigationController {


    @Autowired
    private BlogNavigationService navigationService;


    /**
     * 导航栏查询
     * @param blogNavigation
     * @return
     */
    @GetMapping()
    public R listNavigation(@RequestBody(required = false) BlogNavigation blogNavigation){
        PageHelper.startPage(1,10);
        List<BlogNavigation> navigationList = navigationService.getNavigationList(blogNavigation);
        PageResult result = PageResult.buildPage((Page)navigationList);
        return R.success(result);
    }








    /**
     * 导航栏 创建
     * @param blogNavigation
     * @return
     */
    @PostMapping()
    public R addNavigation(@RequestBody(required = false) BlogNavigation blogNavigation){
        return navigationService.insertNavigation(blogNavigation);
    }


    /**
     * 导航栏删除
     * @param ids
     * @return
     */
    @DeleteMapping()
    public R delNavigation(@RequestParam String ids){
        List<Long> idList= Arrays.asList(ids).stream().map(e->Long.parseLong(e)).collect(Collectors.toList());
        return navigationService.delNavigation(idList);
    }

    /**
     * 导航栏修改
     * @param blogNavigation
     * @return
     */
    @PutMapping()
    public R updateNavigation(@RequestBody(required = false) BlogNavigation blogNavigation){
        return navigationService.updateNavigation(blogNavigation);
    }




}
