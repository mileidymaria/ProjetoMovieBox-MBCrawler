package com.mb.crawler.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SeleniumConfiguration {

    @PostConstruct
    void postConstruct(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Horus-User\\Documents\\chromedriver.exe");
    }

//    @Bean
//    public ChromeDriver chromeDriver(){
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//        return new ChromeDriver(options);
//    }

    @Bean
    public HtmlUnitDriver htmlUnitDriver(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return new HtmlUnitDriver();
    }
}
