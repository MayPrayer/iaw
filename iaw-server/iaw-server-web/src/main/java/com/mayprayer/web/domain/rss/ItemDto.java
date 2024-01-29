package com.mayprayer.web.domain.rss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    /**
     * 标题
     */
    @JacksonXmlProperty(localName  = "title")
    private String title;

    /**
     * 作者
     */
    @JacksonXmlProperty(localName  = "author")
    private String author;

    /**
     * 链接
     */
    @JacksonXmlProperty(localName  = "link")
    private String link;

    /**
     * 发布时间
     */
    @JacksonXmlProperty(localName  = "pubDate")
    private String pubDate;

    /**
     * 标记唯一文章id
     */
    @JacksonXmlProperty(localName  = "guid")
    private String guid;

    /**
     * 具体内容
     */
    @JacksonXmlProperty(localName  = "description")
    @JacksonXmlCData
    private String description;

}
