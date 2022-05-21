package com.mb.crawler.crawler;

import com.mb.crawler.annotation.Crawler;
import com.mb.crawler.model.dto.FilmeDto;
import com.mb.crawler.util.Nlp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Crawler
public class FilmeCrawler {
    private static final String url = "https://pt.wikipedia.org/wiki/Categoria:Filmes_dos_Estados_Unidos_de_2022";
    private static final WebDriver driver = new HtmlUnitDriver();

    public static void crawl(){
        driver.get(url);
        int duracao;
        Map<String, String> links = new ConcurrentHashMap<>();
        Set<FilmeDto> filmes = new HashSet<>();
        List<WebElement> elLinksFilmes = driver.findElements(By.cssSelector(".mw-category-group ul li a"));

        for(WebElement el : elLinksFilmes){
            links.put(el.getAttribute("title"), el.getAttribute("href"));
        }

        for(Map.Entry<String, String> link : links.entrySet()){
            driver.get(link.getValue());

            FilmeDto dto = new FilmeDto();
            dto.setTitulo(link.getKey());
            dto.setResumo(driver.findElement(By.cssSelector(".mw-parser-output p")).getText());
            dto.setWikipediaUrl(link.getValue());

            List<WebElement> trs = driver.findElement(By.id("mw-content-text"))
                    .findElement(By.cssSelector(".mw-parser-output"))
                    .findElement(By.cssSelector(".infobox_v2"))
                    .findElements(By.tagName("tr"));

            for(WebElement tr : trs){
                if(dto.getDuracaoEmMinutos() < 0 && getDuracao(tr) > 0){
                    dto.setDuracaoEmMinutos(getDuracao(tr));
                }
                if(dto.getGenero() == null &&!tr.getText().equals("") && Nlp.removerAcentos(tr.getText()).startsWith("Genero")){
                    List<String> generos = getGenero(tr.findElements(By.tagName("td")).get(1));
                    dto.setGenero(generos);
                }
            }
            filmes.add(dto);
        }

        driver.quit();
    }

    private static List<String> getGenero (WebElement td){
        List<String> generos = new ArrayList<>();
        List<WebElement> links = td.findElements(By.tagName("a"));
        for(WebElement a : links){
            generos.add(a.getText());
        }
        return generos;
    }

    private static int getDuracao (WebElement tr){
        String pattern = "[0-9] min";
        Pattern pCompile = Pattern.compile(pattern);
        List<WebElement> ths = tr.findElements(By.tagName("th"));

        if(ths == null){
            return -1;
        }

        for(WebElement th : ths){
            Matcher matcher = pCompile.matcher(th.getText());
            if(matcher.find()){
                int length = th.getText().length();
                return Integer.valueOf(th.getText().substring(length-8,length-5).trim());
            }
        }
        return -1;
    }

}
