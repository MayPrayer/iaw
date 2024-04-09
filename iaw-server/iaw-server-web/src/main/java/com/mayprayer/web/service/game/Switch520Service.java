package com.mayprayer.web.service.game;

import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Switch520Service {


    public static void main(String[] args) {
        for (int i= 1;i<=489;i++) {

        }
        Document gamelist = Jsoup.parse(HttpUtil.get("https://www.gamer520.com/pcgame/page/"+2));
        Element element = gamelist.select(".content-area").stream().findFirst().orElse(null);
        Elements title = element.select(".entry-title  a");
        for (Element item: title) {
            String name = item.attr("title");
            String href = item.attr("href");

            //方式1 没有跳转 直接显示再下载内容


            //方式2 需要下载
            Document scriptH = Jsoup.parse(HttpUtil.get("https://www.gamer520.com/go/?post_id="+href.split("/")[3].replace(".html","")));
            String skipurl="https"+scriptH.html().split("https")[1].split("';")[0];

            Document codeUrl =  Jsoup.parse(HttpUtil.get(skipurl));
             codeUrl.select(".entry-title").text().split("");
            //获取请求链接  https://www.freer.blog/29546.html     获取验证码

            //获取cookie  https://www.freer.blog/wp-login.php?action=postpass  提交验证码   post_password: 52016  Submit: 提交



            System.out.println(name+": "+href);
        }
    }




}
