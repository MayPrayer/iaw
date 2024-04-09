package com.mayprayer.web.service.novel;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mayprayer.web.domain.tool.NovelInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BQGService {




    private static final String NOVEL_FOLDER = "/usr/share/nginx/html/dist/"; // 存储小说章节的文件夹
    private static  String MERGED_FILE =null; // 合并后的文件名
    private static final int THREAD_COUNT = 20; // 同时下载的线程数量


    public  String  search(String name ,String url){


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 启用无头模式
        options.addArguments("--disable-gpu"); // 禁用GPU加速，可提高稳定性
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // 指定 Chrome 驱动程序路径
//        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        // 初始化 Chrome WebDriver，并设置选项
        WebDriver driver = new ChromeDriver(options);

        // 打开网页
        driver.get(url);

        //输入
        WebElement searchBox  = driver.findElement(By.className("text"));
        searchBox.sendKeys(name);

        //点击查询
        WebElement searchButton = driver.findElement(By.className("btn"));
        searchButton.click();
        try{
            Thread.sleep(5000);
        }catch (Exception e){
        }

        String s = driver.getPageSource();
        Document searchTable = Jsoup.parse(s);
        Elements bookboxs = searchTable.select(".bookbox");
        StringBuilder builder = new StringBuilder();
        if (CollectionUtil.isEmpty(bookboxs)){
            return "暂无该小说信息";
        }
        for (Element item : bookboxs){
            String href = item.select(".bookinfo").select("a").first().attr("href");
            String bookName = item.select(".bookinfo").select("a").first().text();
            String author = item.select(".bookinfo").select(".author").first().text();
            String info = item.select(".bookinfo").select(".uptime").first().text();
            builder.append("id："+href.split("/")[2]+"\n");
            builder.append("书名："+bookName+"\n");
            builder.append(author+"\n\n");
        }
        // 关闭浏览器
        driver.quit();
        return  "搜索信息如下:\n\n"+builder.toString();
    }






    public  String download (String id,String url){

        List<String> chapterUrls = new ArrayList<>();
        Document catoroy = Jsoup.parse(HttpUtil.get(url+"/book/"+id+"/"));
        Elements charcters = catoroy.select(".listmain dl dd").removeClass(".more pc_none");
        if (CollectionUtil.isEmpty(charcters)){
            return  null;
        }
        String fileName = catoroy.select(".info h1").first().text()+"_"+System.currentTimeMillis();
        MERGED_FILE =NOVEL_FOLDER+fileName+".txt";
        log.info("开始下载");
        if (CollectionUtil.isNotEmpty(charcters)) {
            for (Element item : charcters) {
                String charcterUrl = item.select("a").first().attr("href");
                chapterUrls.add(url + charcterUrl);
            }
        }
        downloadNovel(chapterUrls,fileName);
        mergeNovel(fileName);
        log.info("下载完毕");
        return fileName+".txt";
    }



    // 下载整本小说的方法
    private  void downloadNovel(List<String> chapterUrls,String fileName) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        for (String chapterUrl : chapterUrls) {
            executor.submit(() -> downloadChapter(chapterUrl,fileName));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // 下载单个章节的方法
    private  void downloadChapter(String chapterUrl,String fileName ) {
        Document charcterTxt=Jsoup.parse(HttpUtil.get(chapterUrl));
        Element text = charcterTxt.select("#chaptercontent").first();
        String content = text.html().split("<p")[0].replace("<br>","");
        String[] split = chapterUrl.split("/");
        File file = new File(NOVEL_FOLDER+fileName+"/"+split[split.length-1].replace(".html",".txt"));
        try (Writer writer = FileUtil.getWriter(file,"UTF-8",true)) {
            // 写入文本到文件
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 合并小说章节为一个文件
    private  void mergeNovel(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(MERGED_FILE)))) {
            File folder = new File(NOVEL_FOLDER+fileName);
            File[] files = folder.listFiles();

            Arrays.sort(files, (o1, o2) -> {
                int num1 = Integer.parseInt(o1.getName().replaceAll(".txt", ""));
                int num2 = Integer.parseInt(o2.getName().replaceAll(".txt", ""));
                return Integer.compare(num1, num2);
            });

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
            FileUtils.forceDelete(folder);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }











}
