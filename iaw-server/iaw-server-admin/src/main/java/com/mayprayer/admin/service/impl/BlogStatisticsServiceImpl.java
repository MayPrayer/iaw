package com.mayprayer.admin.service.impl;

import com.mayprayer.admin.mapper.BlogStatisticsMapper;
import com.mayprayer.admin.service.BlogStatisticsService;
import com.mayprayer.admin.domain.BlogStatistics;
import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogStatisticsServiceImpl implements BlogStatisticsService {

    @Autowired
    private BlogStatisticsMapper blogStatisticsMapper;


    @Override
    public List<BlogStatistics> getStatisticsList(BlogStatistics blogStatistics) {
        return blogStatisticsMapper.getStatisticsList(blogStatistics);
    }

    @Override
    public R insertStatistics(BlogStatistics blogStatistics) {
        int i = blogStatisticsMapper.insertStatistics(blogStatistics);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R delStatistics(List<Long> ids) {
        int i = blogStatisticsMapper.batchDelStatistics(ids);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }

    @Override
    public R updateStatistics(BlogStatistics blogStatistics) {
        int i = blogStatisticsMapper.updateStatistics(blogStatistics);
        return R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,null);
    }
}
