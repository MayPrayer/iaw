package com.mayprayer.common.domain.dto.rss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JacksonXmlRootElement(localName = "rss" )
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RssDto {

    @JacksonXmlProperty(localName = "channel")
    private ChannelDto channelDto;

    @JacksonXmlProperty(isAttribute = true ,localName = "xmlns:dc")   // isAttribute 是否是标签属性 否则就是子标签    localName 显示名称
    private final String dc="http://purl.org/dc/elements/1.1/"  ;

    @JacksonXmlProperty(isAttribute = true  , localName = "xmlns:trackback")
    private  final String  trackback="http://madskills.com/public/xml/rss/module/trackback/";

    @JacksonXmlProperty(isAttribute = true ,localName = "xmlns:wfw")
    private  final String  wfw="http://wellformedweb.org/CommentAPI/" ;

    @JacksonXmlProperty(isAttribute = true , localName = "xmlns:slash")
    private final String  slash ="http://purl.org/rss/1.0/modules/slash/" ;

    @JacksonXmlProperty(isAttribute = true ,localName = "xmlns:atom")
    private final String  atom ="http://www.w3.org/2005/Atom" ;

    @JacksonXmlProperty(isAttribute = true ,localName = "xmlns:content")
    private final String  content="http://purl.org/rss/1.0/modules/content/" ;

    @JacksonXmlProperty(isAttribute = true )
    private final  String version="2.0";





}
