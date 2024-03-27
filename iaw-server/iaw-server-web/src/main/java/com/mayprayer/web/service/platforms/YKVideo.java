package com.mayprayer.web.service.platforms;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 19:51 2024/3/26
 * @Modified By:
 */
public class YKVideo {





      static   void  search(String name){
                String body = HttpUtil.createGet(String.format("https://so.youku.com/search_video/q_%s", name))
                        .header("User-Agent", " Baiduspider+")
                        .execute().body();
                System.out.println(body);


        }


        public static void main(String[] args) {
                search("百炼成神");
        }


}
