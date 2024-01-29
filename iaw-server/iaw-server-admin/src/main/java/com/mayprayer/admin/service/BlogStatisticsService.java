package com.mayprayer.admin.service;

import com.mayprayer.admin.domain.BlogStatistics;
import com.mayprayer.common.utils.response.R;

import java.util.List;

public interface BlogStatisticsService {

    /**
     * 查询统计数据
     * @param blogStatistics
     * @return
     */
    List<BlogStatistics> getStatisticsList(BlogStatistics blogStatistics);


    /**
     * 插入统计数据
     * @param blogStatistics
     * @return
     */
    R insertStatistics(BlogStatistics blogStatistics);

    /**
     * 删除统计数据
     * @param ids
     * @return
     */
    R  delStatistics(List<Long> ids);


    /**
     * 修改统计数据
     * @param blogStatistics
     * @return
     */
    R updateStatistics(BlogStatistics blogStatistics);



}
