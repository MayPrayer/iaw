package com.mayprayer.common.domain;



import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* 文章分类
* @TableName blog_article
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogArticle extends BaseDomain {

    /**
    * 标题
    */
    @NotBlank(message="[标题]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("标题")
    @Length(max= 255,message="编码长度不能超过255")
    private String titile;
    /**
    * 主图
    */
    @NotBlank(message="[主图]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("主图")
    @Length(max= 255,message="编码长度不能超过255")
    private String mainImg;
    /**
    * 简介，描述
    */
    @NotBlank(message="[简介，描述]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("简介，描述")
    @Length(max= 255,message="编码长度不能超过255")
    private String description;
    /**
    * 排序
    */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
    * 分类id
    */
    @NotNull(message="[分类id]不能为空")
    @ApiModelProperty("分类id")
    private Long categoryId;
    /**
    * 是否置顶
    */
    @NotNull(message="[是否置顶]不能为空")
    @ApiModelProperty("是否置顶")
    private Integer isTop;

    @NotNull(message="[阅读量]不能空")
    @ApiModelProperty("阅读量")
    private Long viewNum;
}
