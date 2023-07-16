package com.mayprayer.web.api.chat;

/**
 * 聊天接口
 */
public abstract class ChatApi {

      static  final  String OWN_THINK_API = "https://api.ownthink.com/bot?appid=xiaosi&userid=user&spoken=%s";

      static  final  String QIN_YUN_KE_API = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=%s";


      abstract  String reply(String words);

}
