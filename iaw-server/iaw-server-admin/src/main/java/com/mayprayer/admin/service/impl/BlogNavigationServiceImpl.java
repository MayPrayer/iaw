package com.mayprayer.admin.service.impl;

import com.mayprayer.admin.mapper.BlogNavigationMapper;
import com.mayprayer.admin.service.BlogNavigationService;
import com.mayprayer.common.domain.BlogNavigation;
import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogNavigationServiceImpl implements BlogNavigationService {


    @Autowired
    private BlogNavigationMapper navigationMapper;


    @Override
    public List<BlogNavigation> getNavigationList(BlogNavigation blogNavigation) {
        return navigationMapper.getNavigationList(blogNavigation);
    }

    @Override
    public R insertNavigation(BlogNavigation blogNavigation) {
        int i = navigationMapper.insertNavigation(blogNavigation);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R delNavigation(List<Long> ids) {
        int i = navigationMapper.batchDelNavigation(ids);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R updateNavigation(BlogNavigation blogNavigation) {
        int i = navigationMapper.updateNavigation(blogNavigation);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }
}
