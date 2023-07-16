package com.mayprayer.web.api.chat;

/**
 * 聊天接口
 */
public abstract class ChatApi {

      static  final  String OWN_THINK_API = "https://api.ownthink.com/bot?appid=xiaosi&userid=user&spoken=%s";

      static  final  String QIN_YUN_KE_API = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=%s";

      static  final  String BAIDU_BOT_API = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/eb-instant?access_token=%s";


     public  abstract  String reply(String words);

}
