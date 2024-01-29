package com.mayprayer.admin.service.impl;

import com.mayprayer.admin.mapper.BlogTagMapper;
import com.mayprayer.admin.service.BlogTagService;
import com.mayprayer.admin.domain.BlogTag;
import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogTagServiceImpl implements BlogTagService {


    @Autowired
    private BlogTagMapper blogTagMapper ;



    @Override
    public List<BlogTag> getTagList(BlogTag blogTag) {
        return blogTagMapper.getTagList(blogTag);
    }

    @Override
    public R insertTag(BlogTag blogTag) {
        int i = blogTagMapper.insertTag(blogTag);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R delTag(List<Long> ids) {
        int i = blogTagMapper.batchDelTag(ids);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R updateTag(BlogTag blogTag) {
        int i = blogTagMapper.updateTag(blogTag);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }
}
