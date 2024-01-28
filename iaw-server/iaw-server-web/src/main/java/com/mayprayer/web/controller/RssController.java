package com.mayprayer.web.controller;


import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mayprayer.common.domain.dto.rss.ChannelDto;
import com.mayprayer.common.domain.dto.rss.ItemDto;
import com.mayprayer.common.domain.dto.rss.RssDto;
import com.mayprayer.common.utils.xml.XmlUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rss")
public class RssController {



//    什么值得买
//
//    公众号
//
//    特殊网站
//
//    小黑盒    steam 自动领取
//
//    抢票软件 或者啥
//
//    米友社(元神，型穷铁道) 活动咨询
//
//    贴吧
//
//    小红书
//
//
//    /**
//     *
//     */
//    @PostMapping("/weibo/hot")
//    public void weiboHot(){
//
//
//
//
//    }
//
//
//    /**
//     * 微博用户id
//     * @param userId
//     */
//    @PostMapping("/weibo/user/{userId}")
//    public void weiboUser(@PathVariable String userId){
//
//
//
//
//    }
//
//
//    /**
//     * 知乎热搜
//     */
//    @PostMapping("/zhihu/hot")
//    public void zhihuHot(){
//
//
//
//
//    }
//
//    /**
//     * 知乎用户动态
//     */
//    @PostMapping("/zhihu/{userId}")
//    public void zhihuHot(@PathVariable String userId){
//
//
//
//
//    }
//
//    /**
//     * b站用户状态
//     */
//    @PostMapping("/bilibili/{userId}")
//    public void bilibiliUser(@PathVariable String userId){
//
//
//
//
//    }
//
//    /**
//     * b站用户状态
//     */
//    @PostMapping("/bilibili/hot")
//    public void bilibiliHot(){
//
//
//
//
//    }
//
//
//    /**
//     * b站用户番剧
//     */
//    @PostMapping("/bilibili/video/{userId}")
//    public void bilibiliVideo(@PathVariable String userId){
//
//
//
//
//    }
//
//
//
//
//
//
//


    public static void main(String[] args) throws Exception {
        ItemDto itemDto = ItemDto.builder()
                .guid(IdUtil.simpleUUID()).title("随机文章标题")
                .author("shiwx").description("描述哈哈哈啊哈")
                .link("www.mayprayer.top").pubDate(new Date().toString()).build();
        List<ItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        itemDtos.add(itemDto);
        ChannelDto channelDto = ChannelDto.builder().item(itemDtos).language("zh-cn")
                .title("频道标题")
                .description("频道描述").link("www.mayprayer.top").generator("mayprayer's toolhub").pubDate(new Date().toString()).build();
        RssDto rssDto = RssDto.builder().channelDto(channelDto).build();

        //转成xml 字符串
        XmlMapper xmlMapper = new XmlMapper();
        String s = xmlMapper.writeValueAsString(rssDto);
        System.out.println(s);

    }




}
