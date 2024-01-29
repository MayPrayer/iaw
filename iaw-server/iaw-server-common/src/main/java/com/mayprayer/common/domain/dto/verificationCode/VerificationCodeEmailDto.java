package com.mayprayer.common.domain.dto.verificationCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCodeEmailDto {

   /**
    * 模板中的标题
    */
   private String title;

   /**
    * 模板中的用户名
    */
   private String username;

   /**
    * 模板中的标题
    */
   private  String code;

}
