package com.mayprayer.web.service.platforms;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mayprayer.common.utils.response.R;
import com.mayprayer.web.domain.video.RespTXDto;
import com.mayprayer.web.domain.video.RespTXVideoInfo;
import com.mayprayer.web.domain.video.RespTXVideoTab;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 19:52 2024/3/26
 * @Modified By:
 */
@Service
public class TXVideo {

    private final  static  String TX_SEARCH_API = "https://v.qq.com/x/search/?q=%s";

    private final  static String  firstSearchApi = "https://pbaccess.video.qq.com/trpc.videosearch.search_cgi.http/load_playsource_list_info?id=%s&dataType=2&scene=2&platform=2&site=qq&appid=1";

    private final  static String  sencondSearchApi = "https://pbaccess.video.qq.com/trpc.videosearch.search_cgi.http/load_playsource_list_info?id=%s&dataType=2&scene=2&platform=2&site=qq&appid=1&pageContext=%s";





    public List<RespTXDto> search(String name){
       List<RespTXDto> result= null;
        String html = HttpUtil.get(String.format(TX_SEARCH_API, name));
        Document searchDoc = Jsoup.parse(html);
        Elements resultElemements = searchDoc.select(".result_item_v");
        if (CollectionUtil.isNotEmpty(resultElemements)){
            result = new ArrayList<>();
            for (Element item: resultElemements) {
                String dataId = item.attr("data-id");
                String title = item.select(".result_title em").text();
                RespTXDto resultDto = new RespTXDto();
                resultDto.setName(title);
                resultDto.setId(dataId);

                String s = HttpUtil.get(String.format(firstSearchApi, dataId));
                RespTXDto respTXDto = getRespTXDto(s);
                if (null!=respTXDto){
                    if (CollectionUtil.isNotEmpty(respTXDto.getTabs())){
                        for(RespTXVideoTab respTXVideoTab :respTXDto.getTabs()){
                            String indexStr= HttpUtil.get(String.format(sencondSearchApi, dataId, URLEncoder.encode(respTXVideoTab.getAsnycParams())));
                            RespTXDto indexRespTXDto = getRespTXDto(indexStr);
                            if (null==resultDto.getEpisodeInfoList()){
                                resultDto.setEpisodeInfoList(new ArrayList<>());
                            }
                            List<RespTXVideoInfo> finalList = new ArrayList<>();
                            //过滤出限免的
                            List<RespTXVideoInfo> XMList =  indexRespTXDto.getEpisodeInfoList().stream().filter(e->e.getRawTags().contains("限免")).collect(Collectors.toList());
                            //过滤出不是预告的
                            List<RespTXVideoInfo> noYGList =  indexRespTXDto.getEpisodeInfoList().stream().filter(e->
                                    (StrUtil.isNotBlank(e.getRawTags())&&!e.getRawTags().contains("预"))||StrUtil.isBlank(e.getRawTags())
                            ).collect(Collectors.toList());
                            if (CollectionUtil.isNotEmpty(XMList)){
                                finalList.addAll(XMList);
                            }
                            if (CollectionUtil.isNotEmpty(noYGList)){
                                finalList.addAll(noYGList);
                            }
                            resultDto.getEpisodeInfoList().addAll(finalList);
                        }
                    }else{
                        List<RespTXVideoInfo> oneVideo =  new ArrayList<>();
                        oneVideo.addAll(respTXDto.getEpisodeInfoList());
                        resultDto.setEpisodeInfoList(oneVideo);
                    }
                    result.add(resultDto);
                }else{
                 continue;
                }
            }
        }
        return  result;
    }


    public   String parse(String name,String id ,Integer sort){

        List<RespTXDto> search = search(name);
        if (CollectionUtil.isNotEmpty(search)){
            RespTXDto videos= search.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
            if (null!=videos){
                RespTXVideoInfo respTXVideoInfo = videos.getEpisodeInfoList().get(sort - 1);
                return StrUtil.isNotBlank(respTXVideoInfo.getVideoUrl())?respTXVideoInfo.getVideoUrl():respTXVideoInfo.getUrl();
            }
        }
        return  null;
    }






    RespTXDto  getRespTXDto(String s){
        Map result = (Map) JSONUtil.toBean(s, R.class).getData();
        Map itemListMap = BeanUtil.toBean(result.get("normalList"), Map.class);
        List videoInfoList = (List) itemListMap.get("itemList");
        Map videoInfoMap = (Map) videoInfoList.get(0);
        Map infoMap = (Map) videoInfoMap.get("videoInfo");
        List firstBlockList = (List) infoMap.get("firstBlockSites");
        RespTXDto respTXDto =null;
        if (CollectionUtil.isNotEmpty(firstBlockList)){
            respTXDto=BeanUtil.toBean(firstBlockList.get(0), RespTXDto.class, CopyOptions.create().ignoreError());
        }
        return  respTXDto;
    }


    public static void main(String[] args) {
        TXVideo txVideo = new TXVideo();
        List<RespTXDto> 封神榜 = txVideo.search("仙逆");

        System.out.println(封神榜);
    }




}
