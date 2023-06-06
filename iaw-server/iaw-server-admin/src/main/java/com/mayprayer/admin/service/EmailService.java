package com.mayprayer.admin.service;

import java.util.Map;

public interface EmailService {

      /**
       *  发送邮件
       * @param sentTo  收件人 邮箱
       * @param sentFrom  发送人 邮箱
       * @param title     标题
       * @param content   内容
       * @throws Exception
       */
      void sendEmail(String sentTo, String sentFrom, String title, Map<String,Object> content)  throws Exception;

}
