package com.mayprayer.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录参数
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginDto {

    private String userName;

    private String password;

}
