package com.mayprayer.web.service.platforms;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 19:52 2024/3/26
 * @Modified By:
 */
public class TXVideo {

    private final  static  String TX_SEARCH_API = "https://v.qq.com/x/search/?q=%s";
   static void search(String name){
        String html = HttpUtil.get(String.format(TX_SEARCH_API, name));
        Document searchDoc = Jsoup.parse(html);
        Elements resultElemements = searchDoc.select("._playlist .item");
        if (CollectionUtil.isNotEmpty(resultElemements)){
            for (Element item: resultElemements) {
               String href =  item.select("a").get(0).attr("href");
               String chacter =  item.text();
               String vip ="";
                Elements vipElement = item.select(".mark_v");
               if (CollectionUtil.isNotEmpty(vipElement)){
                   vip="vip";
               }
                System.out.println(chacter+"  "+vip+"  "+href);
            }
        }
    }

    public static void main(String[] args) {

    }


}
