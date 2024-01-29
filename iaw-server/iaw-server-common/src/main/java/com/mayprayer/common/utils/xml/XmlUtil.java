package com.mayprayer.common.utils.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class XmlUtil {


    public static Map<String, String> parseXml(InputStream inputStream) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 读取输入流
        try{
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            for (Element e : elementList)   map.put(e.getName(), e.getText());
        }catch (Exception e){
            log.error("读取xml文件失败");
        }finally {
            if (null!=inputStream){
                try {
                    inputStream.close();
                }catch (Exception e){
                    log.error("关闭流失败");
                }

            }
        }
        return map;
    }






}
