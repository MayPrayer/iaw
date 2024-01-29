package com.mayprayer.admin.domain;

import com.mayprayer.system.domain.BaseDomain;
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
* 导航栏
* @TableName blog_navigation
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogNavigation extends BaseDomain {

    /**
    * 导航栏名称
    */
    @NotBlank(message="[导航栏名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("导航栏名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String navigationName;
    /**
    * 父级导航栏id
    */
    @NotNull(message="[父级导航栏id]不能为空")
    @ApiModelProperty("父级导航栏id")
    private Long parentId;
    /**
    * 图标
    */
    @NotBlank(message="[图标]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("图标")
    @Length(max= 255,message="编码长度不能超过255")
    private String icoImg;
    /**
    * 点击跳转方式
    */
    @NotNull(message="[点击跳转方式]不能为空")
    @ApiModelProperty("点击跳转方式")
    private Integer openType;



}
