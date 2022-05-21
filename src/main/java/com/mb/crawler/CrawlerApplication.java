package com.mb.crawler;

import com.mb.crawler.crawler.FilmeCrawler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlerApplication {

	@Autowired
	static FilmeCrawler filmeCrawler;
	public static void main(String[] args) {
		SpringApplication.run(CrawlerApplication.class, args);
		filmeCrawler.crawl();
	}

}
