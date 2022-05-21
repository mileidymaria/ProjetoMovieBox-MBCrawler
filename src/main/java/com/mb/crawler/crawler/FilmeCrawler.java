package com.mb.crawler.crawler;

import com.mb.crawler.annotation.Crawler;
import com.mb.crawler.model.dto.FilmeDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Crawler
public class FilmeCrawler {
    private static final String url = "https://pt.wikipedia.org/wiki/Categoria:Filmes_dos_Estados_Unidos_de_2022";
    private static final WebDriver driver = new HtmlUnitDriver();

    public static void crawl(){

        driver.get(url);
        Map<String, String> links = new ConcurrentHashMap<>();
        Set<FilmeDto> filmes = new HashSet<>();
        List<WebElement> elLinksFilmes = driver.findElements(By.cssSelector(".mw-category-group ul li a"));

        for(WebElement el : elLinksFilmes){
            links.put(el.getAttribute("title"), el.getAttribute("href"));
        }

        for(Map.Entry<String, String> link : links.entrySet()){
            driver.get(link.getValue());
            filmes.add(new FilmeDto(link.getKey(), link.getValue(), 0, driver.findElement(By.cssSelector(".mw-parser-output p")).getText(), null));
        }

        driver.quit();
    }

}
