package com.mayprayer.system.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 通知公告表
* @TableName sys_notice
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysNotice extends  BaseDomain {

    private Long roleId;


    private String  noticeMsg;

}
