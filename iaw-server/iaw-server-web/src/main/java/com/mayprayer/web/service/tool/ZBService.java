package com.mayprayer.web.service.tool;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.mayprayer.web.service.ChromeService;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 18:28 2024/5/22
 * @Modified By:
 */
@Service
@Slf4j
public class ZBService {

    @Autowired
    private ChromeService chromeService;

    String ZB_FOLDER = "/usr/share/nginx/html/dist/";

    public  String  search(Integer year ,Integer month,Integer day,Integer hour,String sex ,String url) {
        Browser browser = chromeService.getInstance();
        Page page = browser.newPage();
        page.navigate(url);
        String fileName = System.currentTimeMillis()+".jpg";
        //输入
        page.selectOption("select[name='year']", year+"");
        page.selectOption("select[name='month']", month+"");
        page.selectOption("select[name='day']", day+"");
        page.selectOption("select[name='hour']", hour+"");
        page.check("input[value='"+sex+"']");
        //点击查询
        page.click(".button");
        FileOutputStream fos = null;
        try {
            page.waitForTimeout(2000);
            byte[] screenshot = page.querySelector(".content").screenshot();
            String imagePath = ZB_FOLDER+fileName;
            fos =  new FileOutputStream(imagePath);
            IoUtil.write(fos,true,screenshot);
        } catch (Exception e) {
            return null;
        }finally {
            if (null!=fos){
                try{
                    fos.close();
                }catch (Exception e2){
                    log.error("关闭流失败");
                }
            }
            page.close();

        }

        return  fileName;
    }

    public static void main(String[] args) {
        ZBService zbService = new ZBService();
        zbService.search(1998,9,8,9,"男","https://www.buyiju.com/bazi/");
    }


}
