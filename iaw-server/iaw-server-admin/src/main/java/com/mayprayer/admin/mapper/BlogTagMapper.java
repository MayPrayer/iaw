package com.mayprayer.admin.mapper;


import com.mayprayer.common.domain.BlogTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTagMapper {

    /**
     * 标签查询
     * @param blogTag
     * @return 文章标题等数据
     */
    List<BlogTag> getTagList(BlogTag blogTag);


    /**
     * 标签删除
     * @param idList
     * @return 删除数量
     */
    int batchDelTag(@Param("idList") List<Long> idList);

    /**
     * 标签修改
     * @param blogTag
     * @return  修改数量
     */
    int updateTag(BlogTag blogTag);



    /**
     *标签新增
     * @param blogTag
     * @return 新增数量
     */
    int insertTag(BlogTag blogTag);
}
