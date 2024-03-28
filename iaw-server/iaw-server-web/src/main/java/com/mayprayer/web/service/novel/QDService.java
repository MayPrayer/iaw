package com.mayprayer.web.service.novel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.Writer;

@Slf4j
public class QDService {


    public static void main(String[] args) {
        Document catoroy = Jsoup.parse(HttpUtil.get("https://www.bqgbb.cc/book/5530/"));
        Elements charcters = catoroy.select(".listmain dl dd").removeClass(".more pc_none");

        Long startTime = System.currentTimeMillis();
        log.info("开始下载");
        if (CollectionUtil.isNotEmpty(charcters)){
            for (Element item:charcters) {
                Elements more = item.select(".more");
                //过滤 展开标签
                if (CollectionUtil.isEmpty(more)){
                    String charcterUrl = item.select("a").first().attr("href");
                    Document charcterTxt=Jsoup.parse(HttpUtil.get("https://www.bqgbb.cc"+charcterUrl));
                    Element text = charcterTxt.select("#chaptercontent").first();
                    String content = text.html().split("<p")[0].replace("<br>","");
                    File file = new File("D:/50030.txt");
                    try (Writer writer = FileUtil.getWriter(file,"UTF-8",true)) {
                        // 写入文本到文件
                        writer.write(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        Long endTime = System.currentTimeMillis();
        Long userTime = endTime-startTime;
        log.info("下载完毕，耗时"+userTime+"");
    }


}
