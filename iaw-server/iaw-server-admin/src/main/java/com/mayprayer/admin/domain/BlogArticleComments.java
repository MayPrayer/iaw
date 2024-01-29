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
* 文章评论
* @TableName blog_article_comments
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogArticleComments  extends BaseDomain {

    /**
    * 文章id
    */
    @NotNull(message="[文章id]不能为空")
    @ApiModelProperty("文章id")
    private Long articleId;
    /**
    * 上级评论
    */
    @NotNull(message="[上级评论]不能为空")
    @ApiModelProperty("上级评论")
    private Long parentId;
    /**
    * 是否显示
    */
    @NotNull(message="[是否显示]不能为空")
    @ApiModelProperty("是否显示")
    private Integer isShow;
    /**
    * 评论内容
    */
    @NotBlank(message="[评论内容]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("评论内容")
    @Length(max= 255,message="编码长度不能超过255")
    private String content;
    /**
    * 设备信息
    */
    @NotBlank(message="[设备信息]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("设备信息")
    @Length(max= 255,message="编码长度不能超过255")
    private String deviceInfo;
    /**
    * ip属地
    */
    @NotBlank(message="[ip属地]不能为空")
    @Size(max= 15,message="编码长度不能超过15")
    @ApiModelProperty("ip属地")
    @Length(max= 15,message="编码长度不能超过15")
    private String ipAddress;
    /**
    * 是否匿名
    */
    @NotNull(message="[是否匿名]不能为空")
    @ApiModelProperty("是否匿名")
    private Integer isAnonymous;
    /**
    * 昵称
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("昵称")
    @Length(max= 255,message="编码长度不能超过255")
    private String nickName;
    /**
    * 头像
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("头像")
    @Length(max= 255,message="编码长度不能超过255")
    private String avatar;



}
