package com.mayprayer.web.service.platforms;


import org.springframework.stereotype.Service;


@Service
public class DouYin {



    /**
     * 抖音解析
     * @param url
     * @return
     */
//    public   String parse(String url){

//        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
//        options.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 16_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.6 Mobile/15E148 Safari/604.1");
//        WebDriver driver = new ChromeDriver(options);
//        driver.get(url);
//        String html = driver.getPageSource();
//        String attr = Jsoup.parse(html).select("video").attr("src");
//        attr = attr.replace("720p","1080p");
//        String driverUrl = driver.getCurrentUrl();
//        driver.quit();
//        return driverUrl.split("/")[0]+"//"+driverUrl.split("/")[2]+attr;
//    }


}
