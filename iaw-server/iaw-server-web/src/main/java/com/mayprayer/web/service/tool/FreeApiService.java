package com.mayprayer.web.service.tool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.mayprayer.common.utils.response.R;
import com.mayprayer.web.domain.ToolFreeApi;
import com.mayprayer.web.domain.tool.*;
import com.mayprayer.web.domain.video.EpisodeInfo;
import com.mayprayer.web.domain.video.RespIqiyiDto;
import com.mayprayer.web.domain.video.RespIqiyiVideoInfo;
import com.mayprayer.web.domain.video.RespSearchIqiyiDto;
import com.mayprayer.web.domain.video.RespTXDto;
import com.mayprayer.web.domain.video.RespTXVideoInfo;
import com.mayprayer.web.domain.video.VideoInfo;
import com.mayprayer.web.mapper.ToolFreeApiMapper;
import com.mayprayer.web.mapper.ToolGameMapper;
import com.mayprayer.web.service.novel.BQGService;
import com.mayprayer.web.service.platforms.Iqiyi;
import com.mayprayer.web.service.platforms.TXVideo;
import io.swagger.util.Json;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 夏柔api 爬取
 */

@Service("freeApiService")
public class FreeApiService {

    private  static String FreeApi = "api.aa1.cn";

    private  static String MM_VIDEO_URL = null;

    private  static String MY_DATE_URL =null;


    private  static String KFC_URL =null;

    private  static  String  SIMPLE_NEWS =null;

    private  static String  PRIVATE_INFO =null;


    private  static  String WHEATHER_URL=null;

    private  static String TG_URL =null;

    private   static String COSER_URL=null;

    private  static String  GAME_URL = null;

    private  static String   NOVEL_URL= null;


    @Autowired
    private ToolFreeApiMapper toolFreeApiMapper;

    @Autowired
    private MRService mrService;

    @Autowired
    private BQGService bqgService;

    @Autowired
    private ToolGameMapper toolGameMapper;


    @Autowired
    private Iqiyi iqiyiService;

    @Autowired
    private TXVideo txVideoService;






    @PostConstruct
    public void init(){
        List<ToolFreeApi> apiUrlList = toolFreeApiMapper.getApiUrlList();
        Map<String, String> apiMap = apiUrlList.stream().collect(Collectors.toMap(ToolFreeApi::getKeyword, ToolFreeApi::getUrl, (key1, key2) -> key2));
        MM_VIDEO_URL= apiMap.get("MM_VIDEO_URL");
        MY_DATE_URL= apiMap.get("MY_DATE_URL");
        KFC_URL= apiMap.get("KFC_URL");
        SIMPLE_NEWS= apiMap.get("SIMPLE_NEWS");
        PRIVATE_INFO= apiMap.get("PRIVATE_INFO");
        WHEATHER_URL= apiMap.get("WHEATHER_URL");
        TG_URL= apiMap.get("TG_URL");
        COSER_URL= apiMap.get("COSER_URL");
        GAME_URL = apiMap.get("GAME_URL");
        NOVEL_URL = apiMap.get("NOVEL_URL");
    }





    /**
     * 美女视频
     *
     * @return
     */
    public String getMMVideo() {
        String s = HttpUtil.get(MM_VIDEO_URL);
        R<String> result = JSONUtil.toBean(s, R.class);
        return result.getData();
    }


    /**
     * 摸鱼日历
     */
    public String getMYDate() {
        String s = HttpUtil.get(MY_DATE_URL);
        R result = JSONUtil.toBean(s, R.class);
        Map<String, String> map = (Map<String, String>) result.getData();
        return map.get("moyu_url");
    }

    /**
     * 疯狂星期四
     */
    public String getKFC() {
        String s = HttpUtil.get(KFC_URL);
        Map map = JSONUtil.toBean(s, Map.class);
        return (String) map.get("msg");
    }


    /**
     * 新闻简报
     *
     */
    public String getSimpleNews(){
        String s = HttpUtil.get(SIMPLE_NEWS);
        Map map = JSONUtil.toBean(s, Map.class);
        return (String) map.get("data");
    }

    /**
     * 需要破解 5s 盾
     * @param info
     * @return
     */
    public String getPrivateInfo(String info){
        String s = HttpUtil.get(String.format(PRIVATE_INFO, info));
        R bean = JSONUtil.toBean(s, R.class);
        return null;
    }

    /**舔狗日记
     * @param info
     * @return
     */
    public String getWheather(String info){
        String s = HttpUtil.get(String.format(WHEATHER_URL, info));
        Weather weather = JSONUtil.toBean(s, Weather.class);
        return weather.toString();
    }

    /**
     * 舔狗
     * @return
     */
    public String getTG() {
        String s = HttpUtil.get(String.format(TG_URL));
        return  s.replace("<p>","").replace("</p>","");
    }

    /**
     * 随机coser
     * @return
     */
    public List<String> getCOSER() {
        String s = HttpUtil.get(String.format(COSER_URL));
        CoserInfoR coserInfoR = JSONUtil.toBean(s, CoserInfoR.class);
        return coserInfoR.getData().getData();
    }


    /**
     * 骂人宝典
     * @param level
     * @return
     */
    public String getMR(String level){
        return mrService.getRandomMR(level);
    }


    /**
     * 小说下载
     * @param param
     * @param type 1 搜索  2 下载
     * @return
     */
    public String getNovel(String param ,Integer type){
        if (type==1){
          return   bqgService.search(param,NOVEL_URL);
        }else{
            return   bqgService.download(param,NOVEL_URL);
        }
    }


    /**
     * 游戏搜索
     * @param s
     * @return
     */
    public String getGame(String s) {
        PageHelper.startPage(1,20);
        ToolGame toolGame = new ToolGame();
        toolGame.setName(s);
        List<ToolGame> gameList = toolGameMapper.getGameList(toolGame);
        if (CollectionUtil.isNotEmpty(gameList)){
            StringBuilder stringBuilder = new StringBuilder();
            for (ToolGame game:gameList) {
                stringBuilder.append(game.toString());
            }
            return  stringBuilder.toString();
        }
        return  "暂无该游戏信息";
    }


    /**
     *
     * @param name 关键词
     * @return
     */
   public List<VideoInfo> searchVideo(String name){
       List<VideoInfo>  videoInfos = new ArrayList<>();
       List<RespSearchIqiyiDto> iqySearchResult = iqiyiService.search(name);
       List<RespTXDto> txsearchResult = txVideoService.search(name);
       if (CollectionUtil.isNotEmpty(iqySearchResult)){
           iqySearchResult.stream().forEach(
                   e->{
                       VideoInfo videoInfo =VideoInfo.builder().id(e.getId()).name(e.getG_title()).build();
                       List<EpisodeInfo>  episodeInfoList =new ArrayList<>();
                       List<RespIqiyiVideoInfo> videoinfos = e.getVideoinfos();
                       if (CollectionUtil.isNotEmpty(videoinfos)){
                           videoinfos.stream().forEach(b->{
                               EpisodeInfo episodeInfo = EpisodeInfo.builder().name(b.getName()).sort(b.getOrder() + "").url(b.getUrl()).build();
                               episodeInfoList.add(episodeInfo);
                           });
                       }else{
                           EpisodeInfo episodeInfo = EpisodeInfo.builder().name(e.getG_title()).sort(1+"").url(e.getG_main_link()).build();
                           episodeInfoList.add(episodeInfo);
                       }
                       videoInfo.setEpisodes(episodeInfoList);
                       videoInfos.add(videoInfo);
                   }
           );
       }
       if (CollectionUtil.isNotEmpty(txsearchResult)){
           txsearchResult.stream().forEach(e->{
               VideoInfo videoInfo =VideoInfo.builder().id(e.getId()).name(e.getName()).build();
               List<EpisodeInfo>  episodeInfoList =new ArrayList<>();
               List<RespTXVideoInfo> videoinfos = e.getEpisodeInfoList();
               videoinfos.stream().forEach(b->{
                   EpisodeInfo episodeInfo = EpisodeInfo.builder().name(b.getTitle()).sort(b.getTitle()).build();
                   if (StrUtil.isNotBlank(b.getVideoUrl())){
                       episodeInfo.setUrl(b.getVideoUrl());
                   }
                   if (StrUtil.isNotBlank(b.getUrl())){
                       episodeInfo.setUrl(b.getUrl());
                   }
                   episodeInfoList.add(episodeInfo);
               });
               videoInfo.setEpisodes(episodeInfoList);
               videoInfos.add(videoInfo);
           });
       }
      return  videoInfos;
   }

   public String parse(String name,String id ,Integer sort){
       StringBuilder result = new StringBuilder();
       String url =null;
       List<VideoInfo> videoInfos= searchVideo(name);
       VideoInfo videoInfo = null;
       if (CollectionUtil.isNotEmpty(videoInfos)){
          if (StrUtil.isBlank(id)){
              videoInfo = videoInfos.stream().filter(e->e.getName().equals(name)).findFirst().orElse(null);
          }else{
              videoInfo =  videoInfos.stream().filter(e->e.getId().equals(id)).findFirst().orElse(null);
          }
       }

        if (videoInfo!=null){
            if (sort<0||sort>videoInfo.getEpisodes().size()){
                return "视频不存在或者参数不正确";
            }
          url=videoInfo.getEpisodes().get(sort-1).getUrl();
        }

       if (null!=url){
           result.append("视频搜索数据如下：\n\n");
           result.append("名称 : "+name+"\n");
           result.append("集数 : "+sort+"\n\n");
           result.append("综合 : "+"https://jx.jsonplayer.com/player/?url="+url+"\n\n");
           result.append("虾米 : "+"https://jx.xmflv.com/?url="+url+"\n\n");
           result.append("爱豆 : "+"https://jx.aidouer.net/?url="+url+"\n\n");
           result.append("夜幕 : "+"https://www.yemu.xyz/?url="+url+"\n\n");
           result.append("m1907(最新) : "+"https://im1907.top/?jx="+url+"\n\n");
           result.append("冰豆(最新): "+"https://bd.jx.cn/?url="+url+"\n\n");
           return  result.toString();
       }
       return "视频不存在或者参数不正确";
   }



}
