package com.mayprayer.admin.service;


import com.mayprayer.common.domain.BlogTag;
import com.mayprayer.common.utils.response.R;

import java.util.List;

public interface BlogTagService {

    /**
     * 查询标签
     * @param blogTag
     * @return
     */
    List<BlogTag> getTagList(BlogTag blogTag);


    /**
     * 插入标签
     * @param blogTag
     * @return
     */
    R insertTag(BlogTag blogTag);

    /**
     * 删除标签
     * @param ids
     * @return
     */
    R  delTag(List<Long> ids);


    /**
     * 修改标签
     * @param blogTag
     * @return
     */
    R updateTag(BlogTag blogTag);


}
