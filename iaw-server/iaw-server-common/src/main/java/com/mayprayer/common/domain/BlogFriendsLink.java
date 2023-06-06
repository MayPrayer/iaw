package com.mayprayer.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* 友情链接
* @TableName blog_friends_link
*/
@Data
public class BlogFriendsLink extends  BaseDomain{

    /**
    * 链接名称
    */
    @NotBlank(message="[链接名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("链接名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String linkName;
    /**
    * 链接url
    */
    @NotBlank(message="[链接url]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("链接url")
    @Length(max= 255,message="编码长度不能超过255")
    private String linkUrl;
    /**
    * 是否打开链接
    */
    @NotNull(message="[是否打开链接]不能为空")
    @ApiModelProperty("是否打开链接")
    private Integer isOpen;
    /**
    * 打开方式
    */
    @NotNull(message="[打开方式]不能为空")
    @ApiModelProperty("打开方式")
    private Integer openType;
    /**
    * 排序
    */
    @NotNull(message="[排序]不能为空")
    @ApiModelProperty("排序")
    private Integer sort;


}
