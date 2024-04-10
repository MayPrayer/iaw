package com.mayprayer.web.service.game;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.mayprayer.web.domain.tool.ToolGame;
import com.mayprayer.web.mapper.ToolGameMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class Switch520Service {

    private  final int THREAD_COUNT =2; // 同时下载的线程数量

    @Autowired
    private ToolGameMapper toolGameMapper;






    // 下载整本小说的方法
    public   void downloadGame() {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Integer> integerList = new ArrayList<>();
        for (int i =1;i<=489;i++){
            integerList.add(i);
        }
        for (int i:integerList) {
            executor.submit(() ->run(i));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



   public   void  run(Integer page) {
       Document gamelist = null;
       gamelist=Jsoup.parse(HttpUtil.createGet("https://www.gamer520.com/pcgame/page/"+page).setHttpProxy("127.0.0.1",7897).execute().body());
        if (page==1){
            gamelist= Jsoup.parse(HttpUtil.createGet("https://www.gamer520.com/pcgame").setHttpProxy("127.0.0.1",7897).execute().body());
        }
       Element element = gamelist.select(".content-area").stream().findFirst().orElse(null);
       Elements title = element.select(".entry-title  a");
       for (Element item : title) {
           String name = item.attr("title");
           name =name.replace("|解压即撸|","");
           String href = item.attr("href");

           String pwdCode = null;
           String tiquCode = null;
           String downloadUrl = null;
           //方式1 没有跳转 直接显示再下载内容
           Document showDoc = Jsoup.parse(HttpUtil.createGet(href).setHttpProxy("127.0.0.1",7897).execute().body());

           Elements pwdElements = showDoc.select(".pwd");
           if (CollectionUtil.isEmpty(pwdElements)) {
               Elements contentEle = showDoc.select(".entry-content p");
               for (Element itemEle : contentEle) {
                   String text = itemEle.text();
                   if (text.contains("提取码")) {
                       tiquCode = text.split("提取码：")[1];
                       String lessContent = text.split("提取码：")[0];
                       if (lessContent.contains("天翼高速") || lessContent.contains("百度网盘")) {
                           downloadUrl = "天翼高速" + lessContent.split("天翼高速")[1];

                           pwdCode = lessContent.split("天翼高速")[0];
                       }
                   }
               }
           } else {
               //方式2 需要下载
               Document scriptH = Jsoup.parse(HttpUtil.createGet("https://www.gamer520.com/go/?post_id=" + href.split("/")[3].replace(".html", "")).setHttpProxy("127.0.0.1",7897).execute().body());
               String skipurl = "https" + scriptH.html().split("https")[1].split("';")[0];

               Document codeUrl = Jsoup.parse(HttpUtil.createGet(skipurl).setHttpProxy("127.0.0.1",7897).execute().body());
               //查询下需要需不需要提交验证码
               Elements formElements = codeUrl.select(".entry-content form");
               HttpRequest request = HttpUtil.createGet(skipurl).setHttpProxy("127.0.0.1",7897);
               if (CollectionUtil.isNotEmpty(formElements)) {
                   String code = codeUrl.select(".entry-title").text().split("密码保护：")[1];
                   Map map = new HashMap();
                   map.put("post_password", code);
                   map.put("Submit", "提交");
                   HttpResponse response = HttpUtil.createPost("https://www.freer.blog/wp-login.php?action=postpass").setHttpProxy("127.0.0.1",7897).form(map).execute();
                   HttpCookie sumbitCookie = null;
                   List<HttpCookie> cookies = response.getCookies();
                   if (CollectionUtil.isNotEmpty(cookies)) {
                       for (HttpCookie cookie : cookies) {
                           if (cookie.getName().contains("postpass")) {
                               sumbitCookie = cookie;
                           }
                       }
                   }
                   request.cookie(sumbitCookie);
               }

               //携带cookie 请求
               String showDownHtml = request.execute().body();
               Element contentElement = Jsoup.parse(showDownHtml).select(".entry-content").first();
               downloadUrl = contentElement.select("a").first().attr("href");


               Elements contentElements = contentElement.select("p");
               if (CollectionUtil.isNotEmpty(contentElements)) {
                   for (Element itemEle : contentElements) {
                       String text = itemEle.text();
                       if (text.contains("提取码")) {
                           tiquCode = text.replace("提取码: ", "");
                       } else if (text.contains("解压密码")) {
                           pwdCode = text.replace("解压密码:", "");
                       }
                   }
               }
           }

           log.info("名称：" + name + "\n" +
                   "链接：" + downloadUrl + "\n" +
                   "提取码：" + tiquCode + "\n" +
                   "密码：" + pwdCode + "\n"
           );

           ToolGame toolGame = ToolGame.builder().name(name).downloadUrl(downloadUrl).tqCode(tiquCode).zipCode(pwdCode).build();
           toolGameMapper.insert(toolGame);
       }
   }




}
