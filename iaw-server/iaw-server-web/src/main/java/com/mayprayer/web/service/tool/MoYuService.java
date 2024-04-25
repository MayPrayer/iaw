package com.mayprayer.web.service.tool;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoYuService {

    private  final String NOVEL_FOLDER = "/usr/share/nginx/html/dist/";

    String search(String url){
        Document wxDoc = Jsoup.parse(HttpUtil.get(url));
        String moyuContent= wxDoc.select(".album__list-item").first().attr("data-link");
        moyuContent = moyuContent.replace("http","https");
        String s = HttpUtil.get(moyuContent);
        Elements pElements = Jsoup.parse(s).select("p");
        String href = "";
        if (CollectionUtil.isNotEmpty(pElements)){
            for (Element item: pElements) {
                if (item.attr("style").equals("text-align: center;")){
                    Element img = item.select("img").first();
                    if (null!=img){
                        href =img.attr("data-src");
                    }
                }
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
        MoYuService moYuService = new MoYuService();
        moYuService.search("https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxOTYyMzczNA==&action=getalbum&album_id=2190548434338807809");
    }



}
