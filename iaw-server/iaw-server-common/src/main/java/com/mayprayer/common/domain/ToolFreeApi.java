package com.mayprayer.common.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName tool_free_api
*/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ToolFreeApi extends BaseDomain {


    /**
    * 域名
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("域名")
    @Length(max= 50,message="编码长度不能超过50")
    private String domain;
    /**
    * api详情页
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("api详情页")
    @Length(max= 255,message="编码长度不能超过255")
    private String detailUrl;
    /**
    * api名称
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("api名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String apiName;
    /**
    * api调用地址
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("api调用地址")
    @Length(max= 255,message="编码长度不能超过255")
    private String apiUrl;
    /**
    * 响应方法
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("响应方法")
    @Length(max= 255,message="编码长度不能超过255")
    private String respMethod;
    /**
    * 请求方法
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("请求方法")
    @Length(max= 255,message="编码长度不能超过255")
    private String requestMethod;
    /**
    * 请求案例
    */
    @Size(max= 3000,message="编码长度不能超过3000")
    @ApiModelProperty("请求案例")
    @Length(max= 3000,message="编码长度不能超过3,000")
    private String requestExample;
    /**
    * 响应案例
    */
    @Size(max= 3000,message="编码长度不能超过3000")
    @ApiModelProperty("响应案例")
    @Length(max= 3000,message="编码长度不能超过3,000")
    private String respExample;



}
