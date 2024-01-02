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
* 日志表
* @TableName sys_log
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysLog extends  BaseDomain {


    /**
    * 操作类型 ：新增 修改 查询  删除
    */
    @NotNull(message="[操作类型 ：新增 修改 查询  删除]不能为空")
    @ApiModelProperty("操作类型 ：新增 修改 查询  删除")
    private Integer operateType;
    /**
    * 操作时间
    */
    @NotNull(message="[操作时间]不能为空")
    @ApiModelProperty("操作时间")
    private Date operateTime;
    /**
    * 方法名称
    */
    @NotBlank(message="[方法名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("方法名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String methodName;
    /**
    * 请求参数
    */
    @NotBlank(message="[请求参数]不能为空")
    @Size(max= 3000,message="编码长度不能超过3000")
    @ApiModelProperty("请求参数")
    @Length(max= 3000,message="编码长度不能超过3,000")
    private String requestParams;
    /**
    * 响应参数
    */
    @NotBlank(message="[响应参数]不能为空")
    @Size(max= 3000,message="编码长度不能超过3000")
    @ApiModelProperty("响应参数")
    @Length(max= 3000,message="编码长度不能超过3,000")
    private String responseParams;
    /**
    * 日志类型：正常     异常
    */
    @NotNull(message="[日志类型：正常     异常]不能为空")
    @ApiModelProperty("日志类型：正常     异常")
    private Integer logType;
    /**
    * 用户id
    */
    @ApiModelProperty("用户id")
    private Long userId;
    /**
    * 用户名称
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("用户名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String userName;
    /**
    * ip 地址
    */
    @NotBlank(message="[ip 地址]不能为空")
    @Size(max= 15,message="编码长度不能超过15")
    @ApiModelProperty("ip 地址")
    @Length(max= 15,message="编码长度不能超过15")
    private String ip;



}
