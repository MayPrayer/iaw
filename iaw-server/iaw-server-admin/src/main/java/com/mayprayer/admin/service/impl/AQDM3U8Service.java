package com.mayprayer.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 23:01 2023/6/6
 * @Modified By:
 */
public class AQDM3U8Service {

    private String url = "https://vip.aqdw14.com/";






    /**
     *
     * 下载的文件 需要解密
     * **/
    void TestAes() throws Exception {


        FileInputStream fis = new FileInputStream("D:/aa.ts");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1)
        {
            bos.write(b, 0, n);
        }
        fis.close();
        bos.close();
        byte[] buffer = bos.toByteArray();
        byte[] bytes =aesDecry(buffer, "b45ac06499cecec8",null);


        File file = new File("D:/aa1.ts");
        if (!file.getParentFile().exists()){
            //文件夹不存在 生成
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream aos = new BufferedOutputStream(fos);
        aos.write(bytes);
        aos.close();
        fos.close();

    }


    /**
     * 获取list
     */
    void getResource(){
        Document aqdDoc = Jsoup.parse(HttpRequest.get(url).execute().body());
        Elements aqdEls = aqdDoc.select(".widget-tags div ul li a");
        if (CollectionUtil.isNotEmpty(aqdEls)){
            aqdEls.stream().forEach(aqdEl->{
                System.out.println(aqdEl);
            });
        }
    }


    /**
     * 获取详情
     */
    void getResource2(){

        Document aqdDetailDoc = Jsoup.parse(HttpRequest.get("https://vip.aqdx35.com/videos/tag/exhibitionism/1").execute().body());
        Elements  aqds = aqdDetailDoc.select("div .thumbnail-cover a");
        if (CollectionUtil.isNotEmpty(aqds)){
            aqds.stream().forEach(aqdDetail->{
                System.out.println(aqdDetail);
            });
        }
    }

    void getResource3(){
        Document aqdDetailDoc = Jsoup.parse(HttpRequest.get("https://siwa2.xyz/index.php/vod/play/id/45863/sid/1/nid/1.html").execute().body());
        System.out.println(aqdDetailDoc);
    }


    Document aqdDetailDoc = Jsoup.parse(HttpRequest.get("https://siwa2.xyz/index.php/vod/play/id/45863/sid/1/nid/1.html").execute().body());






    /**
     * @Author: shiwx
     * * @param null
     * @Description: aes 解密  无iv状况默认iv为16位0
     * @Date 2022/7/13 4:14
     **/
    public static byte[] aesDecry(byte[] contentByte,String key,String IV_STRING ) throws Exception {
        if (null==IV_STRING){
            IV_STRING = "0000000000000000";
        }
        // byte[] contentByte = Base64.getDecoder().decode(content);
        byte[] keyByte = key.getBytes();
        //初始化一个密钥对象
        SecretKeySpec keySpec = new SecretKeySpec(keyByte ,"AES");
        //初始化一个初始向量,不传入的话，则默认用全0的初始向量
        byte[] initParam = IV_STRING.getBytes();
        IvParameterSpec ivSpec = new IvParameterSpec(initParam);
        // 指定加密的算法、工作模式和填充方式
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec,ivSpec);
        byte[] result  = cipher.doFinal(contentByte);
        return result;
    }



    void mergeFiles(){
        String[] files = new String[5];
        files[0]="D:/644bb8dc63.ts";
        files[1]="D:/644bb8dc64.ts";
        files[2]="D:/644bb8dc65.ts";
        files[3]="D:/644bb8dc66.ts";
        files[4]="D:/644bb8dc67.ts";
        mergeFiles(files,"D:/final.mp4");
    }


    public static boolean mergeFiles(String[] fpaths, String resultPath) {
        if (fpaths == null || fpaths.length < 1 ) {
            return false;
        }
        if (fpaths.length == 1) {
            return new File(fpaths[0]).renameTo(new File(resultPath));
        }

        File[] files = new File[fpaths.length];
        for (int i = 0; i < fpaths.length; i ++) {
            files[i] = new File(fpaths[i]);
            if (!files[i].exists() || !files[i].isFile()) {
                return false;
            }
        }

        File resultFile = new File(resultPath);

        try {
            int bufSize = 1024;
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(resultFile));
            byte[] buffer = new byte[bufSize];

            for (int i = 0; i < fpaths.length; i ++) {
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(files[i]));
                int readcount;
                while ((readcount = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, readcount);
                }
                inputStream.close();
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0; i < fpaths.length; i ++) {
            files[i].delete();
        }

        return true;
    }


    void getM3u8() throws  Exception{
        String s = HttpUtil.get("https://cdn.aqd-tv.com/video/9887ce63/playlist/9887ce63.m3u8");
        //一行读取
        String[] lines = s.split("\n");
        AtomicInteger count = new AtomicInteger();
        Arrays.stream(lines).forEach(line->{
            if (line.contains(".ts")){
                url = "https://cdn.aqd-tv.com/video/9887ce63/playlist/"+line;
                HttpUtil.downloadFile(url,"D:/"+count+".ts");
                count.getAndIncrement();
            }
        });
        String [] filepath = new String[count.get()];
        for (int i = 0;i<count.get();i++){
            filepath[i] = "D:/"+i+".ts";
        }
        mergeFiles(filepath,"D:/aa.mp4");

    }



}
