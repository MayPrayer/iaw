package com.mayprayer.admin.domain;


import com.mayprayer.system.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BlogTagArticle  extends BaseDomain {

    /**
     * 标签id
     */
    private Long  tagId;

    /**
     * 文章id
     */
    private Long  articleId;

}
