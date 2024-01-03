package com.mayprayer.admin.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mayprayer.admin.service.BlogFriendsLinkService;
import com.mayprayer.common.domain.BlogFriendsLink;
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
 * 友情链接相关接口
 */
@RestController
@Slf4j
@RequestMapping("/friendslink")
public class BlogFriendsLinkController {




    @Autowired
    private BlogFriendsLinkService friendsLinkService;


    /**
     * 友情链接查询
     * @param blogFriendsLink
     * @return
     */
    @GetMapping()
    public R listFriendsLink(@RequestBody(required = false) BlogFriendsLink blogFriendsLink){
        PageHelper.startPage(1,10);
        List<BlogFriendsLink> friendsLinkList = friendsLinkService.getFriendsLinkList(blogFriendsLink);
        PageResult result = PageResult.buildPage((Page)friendsLinkList);
        return R.success(result);
    }


    /**
     * 友情链接 创建
     * @param blogFriendsLink
     * @return
     */
    @PostMapping()
    public R addFriendsLink(@RequestBody(required = false)  BlogFriendsLink blogFriendsLink){
        return friendsLinkService.insertFriendsLink(blogFriendsLink);
    }


    /**
     * 友情链接删除
     * @param ids
     * @return
     */
    @DeleteMapping()
    public R delFriendsLink(@RequestParam String ids){
        List<Long> idList= Arrays.asList(ids).stream().map(e->Long.parseLong(e)).collect(Collectors.toList());
        return friendsLinkService.delFriendsLink(idList);
    }

    /**
     * 友情链接修改
     * @param blogFriendsLink
     * @return
     */
    @PutMapping()
    public R updateFriendsLink(@RequestBody(required = false) BlogFriendsLink blogFriendsLink){
        return friendsLinkService.updateFriendsLink(blogFriendsLink);
    }






}
