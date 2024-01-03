package com.mayprayer.admin.mapper;


import com.mayprayer.common.domain.BlogNavigation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogNavigationMapper {

    /**
     * 导航栏查询
     * @param blogNavigation
     * @return 文章标题等数据
     */
    List<BlogNavigation> getNavigationList(BlogNavigation blogNavigation);


    /**
     * 导航栏删除
     * @param idList
     * @return 删除数量
     */
    int batchDelNavigation(@Param("idList") List<Long> idList);

    /**
     * 导航栏修改
     * @param blogNavigation
     * @return  修改数量
     */
    int updateNavigation(BlogNavigation blogNavigation);



    /**
     *导航栏新增
     * @param blogNavigation
     * @return 新增数量
     */
    int insertNavigation(BlogNavigation blogNavigation);

}
