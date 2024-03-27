package com.mayprayer.web.service.chat;

/**
 * 聊天接口
 */
public abstract class ChatApi {

      static  final  String OWN_THINK_API = "https://api.ownthink.com/bot?appid=xiaosi&userid=user&spoken=%s";

      static  final  String QIN_YUN_KE_API = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=%s";

      static  final  String BAIDU_BOT_API = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=%s";


    /**
     *
     * @param words  words 对话语句
     * @param userId userId 确认对象 用于后续存储上下文对象
     * @return
     */
     public  abstract  String reply(String words,String userId);

}
