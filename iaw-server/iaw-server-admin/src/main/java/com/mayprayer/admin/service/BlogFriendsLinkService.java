package com.mayprayer.admin.service;

import com.mayprayer.common.domain.BlogFriendsLink;
import com.mayprayer.common.utils.response.R;

import java.util.List;

public interface BlogFriendsLinkService {

    /**
     * 查询友情链接
     * @param blogFriendsLink
     * @return
     */
    List<BlogFriendsLink> getFriendsLinkList(BlogFriendsLink blogFriendsLink);


    /**
     * 插入友情链接
     * @param blogFriendsLink
     * @return
     */
    R insertFriendsLink(BlogFriendsLink blogFriendsLink);

    /**
     * 删除友情链接
     * @param ids
     * @return
     */
    R  delFriendsLink(List<Long> ids);


    /**
     * 修改友情链接
     * @param blogFriendsLink
     * @return
     */
    R updateFriendsLink(BlogFriendsLink blogFriendsLink);



}
