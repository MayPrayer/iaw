package com.mayprayer.web.service.tool;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class MoYuService {

    private  final String NOVEL_FOLDER = "/usr/share/nginx/html/dist/";

    String search(String url){
        Document wxDoc = Jsoup.parse(HttpUtil.get(url));
        String moyuContent= wxDoc.select(".album__list-item").first().attr("data-link");
        moyuContent = moyuContent.replace("http","https");
        String s = HttpUtil.get(moyuContent);
        Elements imgElements = Jsoup.parse(s).select("p img");
        String href = "";
        if (CollectionUtil.isNotEmpty(imgElements)){
            Element element = imgElements.stream().filter(e -> "540".equals(e.attr("data-w"))).findFirst().orElse(null);
            if (null!=element){
                href =element.attr("data-src");
            }
        }
        String fileName = System.currentTimeMillis()+".jpg";
        String fileUrl = NOVEL_FOLDER+fileName;
        HttpUtil.downloadFile(href,fileUrl);
        try{
            Thumbnails.of(fileUrl).size(540,804).outputQuality(0.8f).toFile(fileUrl);
        }catch (Exception e){
            log.error("图片压缩失败"+e);
        }
        return fileName;
    }


    public static void main(String[] args) {
        MoYuService muu  = new MoYuService();
        muu.search("https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxOTYyMzczNA==&action=getalbum&album_id=2190548434338807809");
    }






}
