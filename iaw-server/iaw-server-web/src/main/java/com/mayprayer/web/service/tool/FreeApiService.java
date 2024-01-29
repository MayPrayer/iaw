package com.mayprayer.web.service.tool;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpUtil;
import com.mayprayer.web.domain.ToolFreeApi;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 夏柔api 爬取
 */
public class FreeApiService {

    private final static String FreeApi = "api.aa1.cn";


    public void getListHtml(){
        Document freeApiDoc = Jsoup.parse(HttpUtil.get(FreeApi));
        Elements itemElements = freeApiDoc.select(".cf-friends-link");
        //api链接
        if (CollectionUtil.isNotEmpty(itemElements)){
            itemElements.stream().forEach(
                    element -> {
                        String href = element.attr("href");
                        String domain = FreeApi;
                        String detailUrl = FreeApi+href;
                        String apiName ="";
                        String apiUrl ="";
                        String respMethod ="";
                        String requestMethod ="";
                        String requestExample="";
                        String respExample="";

                        //标题
                        Document apiDetail = Jsoup.parse(HttpUtil.get(FreeApi + href));
                        Elements titles  = apiDetail.select(".post-title");
                        if (CollectionUtil.isNotEmpty(titles)){
                            apiName = titles.get(0).text();
                        }

                        Elements  apiElements= apiDetail.select("article p code");
                        if (CollectionUtil.isNotEmpty(apiElements)){
                             apiUrl = apiElements.get(0).text();
                             respMethod = apiElements.get(1).text();
                            requestMethod = apiElements.get(2).text();
                            requestExample = apiElements.get(3).text();
                        }
                        apiDetail.select(".code");


                        //创建freeapi 数据库对象
                        ToolFreeApi.builder().build();
                    }
            );
        }
    }


    public static void main(String[] args) {
        new FreeApiService().getListHtml();
    }

}
