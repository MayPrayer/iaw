package com.mayprayer.common.utils.constant;

import lombok.Data;

@Data
public class Constant {

   public static final Boolean BOOLEAN_YES = true ;

   public static final Boolean BOOLEAN_NO = false ;

   public static final Integer INT_YES = 1 ;


   public static final Integer INT_NO = 0 ;

   /**
    * 用户token key 名称
    */
   public  static final String  USER_TOKEN = "token";

   /**
    * redis 存放用户登录信息 文件夹名称
    */
   public static  final  String  LOGIN_USER_FOLDER = "LoginUser:";



}
