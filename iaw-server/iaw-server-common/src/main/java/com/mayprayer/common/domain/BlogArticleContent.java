package com.mayprayer.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* 文章内容表
* @TableName blog_article_content
*/
@Data
public class BlogArticleContent extends BaseDomain {

    /**
    * 文章内容
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("文章内容")
    @Length(max= -1,message="编码长度不能超过-1")
    private String content;
    /**
    * 文章id
    */
    @NotNull(message="[文章id]不能为空")
    @ApiModelProperty("文章id")
    private Long articleId;



}
