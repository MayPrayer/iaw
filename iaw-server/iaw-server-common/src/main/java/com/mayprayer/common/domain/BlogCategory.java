package com.mayprayer.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
* 文章分类
* @TableName blog_category
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogCategory extends BaseDomain{

    /**
    * 分类名称
    */
    @NotBlank(message="[分类名称]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("分类名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String categoryName;
    /**
    * 分类排序
    */
    @ApiModelProperty("分类排序")
    private Integer sort;



}
