package com.mayprayer.admin.mapper;

import com.mayprayer.admin.domain.BlogStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogStatisticsMapper {

    /**
     * 统计查询
     * @param blogStatistics
     * @return 文章标题等数据
     */
    List<BlogStatistics> getStatisticsList(BlogStatistics blogStatistics);


    /**
     * 统计删除
     * @param idList
     * @return 删除数量
     */
    int batchDelStatistics(@Param("idList") List<Long> idList);

    /**
     * 统计修改
     * @param blogStatistics
     * @return  修改数量
     */
    int updateStatistics(BlogStatistics blogStatistics);



    /**
     *导航栏新增
     * @param blogStatistics
     * @return 新增数量
     */
    int insertStatistics(BlogStatistics blogStatistics);

}
