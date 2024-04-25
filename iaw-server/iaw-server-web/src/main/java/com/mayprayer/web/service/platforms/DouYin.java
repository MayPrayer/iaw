package com.mayprayer.web.service.platforms;

import cn.hutool.http.HttpUtil;
import com.mayprayer.common.utils.constant.Constant;
import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;


@Service
public class DouYin {



    /**
     * 抖音解析
     * @param url
     * @return
     */
    public   String parse(String url){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // 启用无头模式
        options.addArguments("--disable-gpu"); // 禁用GPU加速，可提高稳定性
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
//        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        options.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 16_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.6 Mobile/15E148 Safari/604.1");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        String html = driver.getPageSource();
        String attr = Jsoup.parse(html).select("video").attr("src");
        attr = attr.replace("720p","1080p");
        String driverUrl = driver.getCurrentUrl();
        driver.quit();
        return driverUrl.split("/")[0]+"//"+driverUrl.split("/")[2]+attr;
    }


}
