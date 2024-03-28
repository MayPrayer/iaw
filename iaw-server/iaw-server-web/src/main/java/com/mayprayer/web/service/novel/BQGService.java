package com.mayprayer.web.service.novel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BQGService {


    private static final String NOVEL_FOLDER = "D:/novel"; // 存储小说章节的文件夹
    private static  String MERGED_FILE =null; // 合并后的文件名
    private static final int THREAD_COUNT = 10; // 同时下载的线程数量


    public static void main(String[] args) {
        List<String> chapterUrls = new ArrayList<>();
        Document catoroy = Jsoup.parse(HttpUtil.get("https://www.bqgbb.cc/book/5530/"));
        Elements charcters = catoroy.select(".listmain dl dd").removeClass(".more pc_none");
        MERGED_FILE ="5531.txt";
        Long startTime = System.currentTimeMillis();
        log.info("开始下载");
        if (CollectionUtil.isNotEmpty(charcters)) {
            for (Element item : charcters) {
                String charcterUrl = item.select("a").first().attr("href");
                chapterUrls.add("https://www.bqgbb.cc" + charcterUrl);
            }
        }
        downloadNovel(chapterUrls);
        mergeNovel();
        log.info("合并下载");
    }

    // 下载单个章节的方法
    private static void downloadChapter(String chapterUrl) {
        Document charcterTxt=Jsoup.parse(HttpUtil.get(chapterUrl));
        Element text = charcterTxt.select("#chaptercontent").first();
        String content = text.html().split("<p")[0].replace("<br>","");
        File file = new File(NOVEL_FOLDER);
        try (Writer writer = FileUtil.getWriter(file,"UTF-8",true)) {
            // 写入文本到文件
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 下载整本小说的方法
    private static void downloadNovel(List<String> chapterUrls) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        for (String chapterUrl : chapterUrls) {
            executor.submit(() -> downloadChapter(chapterUrl));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 合并小说章节为一个文件
    private static void mergeNovel() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(MERGED_FILE)))) {
            File folder = new File(NOVEL_FOLDER);
            File[] files = folder.listFiles();

            //排序

            if (files != null) {
                for (File file : files) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line);
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}
