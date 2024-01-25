package com.mayprayer.system.util;


import com.mayprayer.common.utils.constant.Constant;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {


    public static  void writeJson(String result, HttpServletResponse response, HttpStatus status) throws IOException {
        response.setCharacterEncoding("utf-8");    //设置 HttpServletResponse使用utf-8编码
        response.setHeader("Content-Type", "application/json");  //设置响应头的编码
        response.setStatus(status.value());
        response.getWriter().write(result);
    }

    public static void writeToken(String result, HttpServletResponse response,String uuid) throws IOException{
        response.setCharacterEncoding("utf-8");    //设置 HttpServletResponse使用utf-8编码
        response.setHeader("Content-Type", "application/json");  //设置响应头的编码
        response.setHeader("Access-Control-Expose-Headers", "token");
        response.setHeader(Constant.USER_TOKEN,uuid);
        response.getWriter().write(result);
    }



}
