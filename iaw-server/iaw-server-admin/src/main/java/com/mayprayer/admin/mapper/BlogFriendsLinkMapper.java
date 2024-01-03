package com.mayprayer.admin.mapper;


import com.mayprayer.common.domain.BlogFriendsLink;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogFriendsLinkMapper {

    /**
     * 友情链接查询
     * @param blogFriendsLink
     * @return 文章标题等数据
     */
    List<BlogFriendsLink> getFriendsLinkList(BlogFriendsLink blogFriendsLink);


    /**
     * 友情链接删除
     * @param idList
     * @return 删除数量
     */
    int batchDelFriendsLink(@Param("idList") List<Long> idList);

    /**
     * 友情链接修改
     * @param blogFriendsLink
     * @return  修改数量
     */
    int updateFriendsLink(BlogFriendsLink blogFriendsLink);



    /**
     *友情链接新增
     * @param blogFriendsLink
     * @return 新增数量
     */
    int insertFriendsLink(BlogFriendsLink blogFriendsLink);

}
