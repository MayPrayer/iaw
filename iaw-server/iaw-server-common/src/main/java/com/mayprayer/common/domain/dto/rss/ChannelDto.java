package com.mayprayer.common.domain.dto.rss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDto {

    /**
     * 频道标题
     */
    @JacksonXmlProperty(localName  = "title")
    private  String title;


    /**
     * 频道链接
     */
    @JacksonXmlProperty(localName  = "link")
    private  String link;


    /**
     * 频道描述
     */
    @JacksonXmlProperty(localName  = "description")
    @JacksonXmlCData
    private  String description;

    /**
     * 生成程序
     */
    @JacksonXmlProperty(localName  = "generator")
    private  String generator;

    /**
     * 语言
     */
    @JacksonXmlProperty(localName  = "language")
    private  String language;


    /**
     * 发布时间
     */
    @JacksonXmlProperty(localName  = "pubDate")
    private  String pubDate;


    /**
     * 文章
     */
    @JacksonXmlElementWrapper(useWrapping = false)   //外层不需要加一层包裹
    @JacksonXmlProperty(localName  = "item")
    private List<ItemDto> item;


}
