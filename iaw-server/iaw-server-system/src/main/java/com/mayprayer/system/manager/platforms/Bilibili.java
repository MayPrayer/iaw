package com.mayprayer.system.manager.platforms;

import cn.hutool.http.HttpUtil;

/**
 * b站
 */
public class Bilibili{

    private String url = "https://api.bilibili.com/x/web-interface/wbi/search/square?limit=50";




    public void search() {
        String s = HttpUtil.get(url);
    }



}
