package com.mayprayer.web.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.springframework.stereotype.Service;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 18:30 2024/5/22
 * @Modified By:
 */
@Service("chromeService")
public class ChromeService {

    private Browser browser ;

   public void init(){
        if (null==browser){
            browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        }
    }

    public Browser getInstance(){
       init();
       return  browser;
    }

}
