package com.mayprayer.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto implements Serializable {

    /**
     * 标题
     */
    private String title;


    /**
     * 用户名
     */
    private  String toUser;


    /**
     * 内容
     */
    private String content;

}
