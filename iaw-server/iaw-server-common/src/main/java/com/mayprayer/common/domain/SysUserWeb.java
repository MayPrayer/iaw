package com.mayprayer.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* 网站用户
* @TableName sys_user_web
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserWeb extends  BaseDomain {

    /**
    * 手机号
    */
    @Size(max= 11,message="编码长度不能超过11")
    @ApiModelProperty("手机号")
    @Length(max= 11,message="编码长度不能超过11")
    private String phone;
    /**
    * 邮箱
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("邮箱")
    @Length(max= 50,message="编码长度不能超过50")
    private String email;
    /**
    * qq
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("qq")
    @Length(max= 50,message="编码长度不能超过50")
    private String qq;
    /**
    * 微信
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("微信")
    @Length(max= 50,message="编码长度不能超过50")
    private String wechat;
    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Long userId;

}
