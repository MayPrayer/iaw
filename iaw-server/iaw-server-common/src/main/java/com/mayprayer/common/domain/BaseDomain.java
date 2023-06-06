package com.mayprayer.common.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class BaseDomain {

    /**
     * 主键
     */

    @ApiModelProperty("主键")
    private Long id;
    /**
     * 创建时间
     */
    @NotNull(message="[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 创建人
     */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("创建人")
    @Length(max= 50,message="编码长度不能超过50")
    private String createBy;
    /**
     * 修改时间
     */
    @NotNull(message="[修改时间]不能为空")
    @ApiModelProperty("修改时间")
    private Date updateTime;
    /**
     * 修改人
     */
    @NotBlank(message="[修改人]不能为空")
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("修改人")
    @Length(max= 50,message="编码长度不能超过50")
    private String updateBy;
    /**
     * 是否删除，默认0未删除
     */
    @NotNull(message="[是否删除，默认0未删除]不能为空")
    @ApiModelProperty("是否删除，默认0未删除")
    private Integer isDelete;




}
