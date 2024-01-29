package com.mayprayer.admin.service;


import com.mayprayer.admin.domain.BlogNavigation;
import com.mayprayer.common.utils.response.R;

import java.util.List;

public interface BlogNavigationService {

    /**
     * 查询导航栏
     * @param blogNavigation
     * @return
     */
    List<BlogNavigation> getNavigationList(BlogNavigation blogNavigation);


    /**
     * 插入导航栏
     * @param blogNavigation
     * @return
     */
    R insertNavigation(BlogNavigation blogNavigation);

    /**
     * 删除导航栏
     * @param ids
     * @return
     */
    R  delNavigation(List<Long> ids);


    /**
     * 修改导航栏
     * @param blogNavigation
     * @return
     */
    R updateNavigation(BlogNavigation blogNavigation);


}
