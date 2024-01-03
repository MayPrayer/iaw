package com.mayprayer.admin.service.impl;

import com.mayprayer.admin.mapper.BlogFriendsLinkMapper;
import com.mayprayer.admin.service.BlogFriendsLinkService;
import com.mayprayer.common.domain.BlogFriendsLink;
import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogFriendsLinkServiceImpl implements BlogFriendsLinkService {


    @Autowired
    private BlogFriendsLinkMapper friendsLinkMapper;


    @Override
    public List<BlogFriendsLink> getFriendsLinkList(BlogFriendsLink blogFriendsLink) {
        return friendsLinkMapper.getFriendsLinkList(blogFriendsLink);
    }

    @Override
    public R insertFriendsLink(BlogFriendsLink blogFriendsLink) {
        int i = friendsLinkMapper.insertFriendsLink(blogFriendsLink);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R delFriendsLink(List<Long> ids) {
        int i = friendsLinkMapper.batchDelFriendsLink(ids);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R updateFriendsLink(BlogFriendsLink blogFriendsLink) {
        int i = friendsLinkMapper.updateFriendsLink(blogFriendsLink);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }
}
