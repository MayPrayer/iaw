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
import java.util.Date;

/**
* 后台用户表
* @TableName sys_user
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUser extends  BaseDomain {


    /**
    * 用户名
    */
    @NotBlank(message="[用户名]不能为空")
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("用户名")
    @Length(max= 50,message="编码长度不能超过50")
    private String userName;
    /**
    * 密码
    */
    @NotBlank(message="[密码]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("密码")
    @Length(max= 20,message="编码长度不能超过20")
    private String password;
    /**
    * 登录地址
    */
    @NotBlank(message="[登录地址]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("登录地址")
    @Length(max= 100,message="编码长度不能超过100")
    private String loginAddress;
    /**
    * 登录方式
    */
    @NotNull(message="[登录方式]不能为空")
    @ApiModelProperty("登录方式")
    private Integer loginType;
    /**
    * 上次登录时间
    */
    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;
    /**
    * ip地址
    */
    @NotBlank(message="[ip地址]不能为空")
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("ip地址")
    @Length(max= 16,message="编码长度不能超过16")
    private String ip;
    /**
    * 用户设备
    */
    @NotBlank(message="[用户设备]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("用户设备")
    @Length(max= 100,message="编码长度不能超过100")
    private String useAgent;


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

}
