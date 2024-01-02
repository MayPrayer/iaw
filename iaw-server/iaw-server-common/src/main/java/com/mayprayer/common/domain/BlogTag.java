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
* 文章标签
* @TableName blog_tag
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogTag extends  BaseDomain {


    /**
    * 标签名称
    */
    @NotBlank(message="[标签名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("标签名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String tagName;
    /**
    * 排序
    */
    @NotNull(message="[排序]不能为空")
    @ApiModelProperty("排序")
    private Integer sort;



}
