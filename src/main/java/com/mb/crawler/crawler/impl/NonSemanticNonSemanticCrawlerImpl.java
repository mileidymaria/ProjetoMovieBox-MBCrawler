package com.mb.crawler.crawler.impl;

import com.mb.crawler.annotation.Crawler;
import com.mb.crawler.crawler.NonSemanticCrawler;
import com.mb.crawler.model.dto.ActorDto;
import com.mb.crawler.model.dto.MovieDto;
import com.mb.crawler.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mb.crawler.util.Nlp.removerAcentos;
import static com.mb.crawler.util.Util.generateStringHash;

@Crawler
public class NonSemanticNonSemanticCrawlerImpl implements NonSemanticCrawler {
    private static final Logger logger = LoggerFactory.getLogger(NonSemanticNonSemanticCrawlerImpl.class);
    private static final String URL_USA = "https://pt.wikipedia.org/wiki/Categoria:Filmes_dos_Estados_Unidos_de_";
    private static final String URL_BRA = "https://pt.wikipedia.org/wiki/Categoria:Filmes_do_Brasil_de_";
    private static final String URL_CA = "https://pt.wikipedia.org/wiki/Categoria:Filmes_do_Canadá_de_";
    private static final String URL_ES = "https://pt.wikipedia.org/wiki/Categoria:Filmes_da_Espanha_de_2021";
    private static final String URL_ORIGINAIS_NETFLIX = "https://pt.wikipedia.org/wiki/Categoria:Filmes_originais_da_Netflix";
    private static final String URL_DISTRIBUIDOS_NETFLIX = "https://pt.wikipedia.org/wiki/Categoria:Filmes_distribu%C3%ADdos_pela_Netflix";
    private static final String URL_FILMES_SUPER_HEROI = "https://pt.wikipedia.org/wiki/Categoria:Filmes_de_super-her%C3%B3is";

    @Override
    public Set<MovieDto> crawl(){
        Set<MovieDto> filmes = new HashSet<>();
        int anoAtual = LocalDate.now().getYear();
        logger.info("Início da crawleagem "+ LocalDateTime.now().toString());

        while (anoAtual >= 1990){
            filmes.addAll(crawl(URL_USA +anoAtual));
            filmes.addAll(crawl(URL_BRA +anoAtual));
            filmes.addAll(crawl(URL_CA +anoAtual));
            filmes.addAll(crawl(URL_ES +anoAtual));
            filmes.addAll(crawl(URL_ORIGINAIS_NETFLIX));
            filmes.addAll(crawl(URL_DISTRIBUIDOS_NETFLIX));
            filmes.addAll(crawl(URL_FILMES_SUPER_HEROI));
            logger.info("Ano: "+ anoAtual + " com " + filmes.size() + " filmes crawleados (acumulando os anos anteiores)");
            anoAtual--;
        }

        logger.info("Fim "+ LocalDateTime.now().toString() +", com um total de " + filmes.size() + " filmes crawleados.");
        return filmes;
    }

    private Set<MovieDto> crawl(String url){
        WebDriver driver = new HtmlUnitDriver();
        driver.get(url);

        Map<String, String> links = new ConcurrentHashMap<>();
        Set<MovieDto> filmes = new HashSet<>();
        List<WebElement> elLinksFilmes = driver.findElements(By.cssSelector(".mw-category-group ul li a"));

        for(WebElement el : elLinksFilmes){
            links.put(el.getAttribute("title"), el.getAttribute("href"));
        }

        for(Map.Entry<String, String> link : links.entrySet()){
            driver.get(link.getValue());

            try{
                driver.findElement(By.id("mw-content-text")).findElement(By.cssSelector(".mw-parser-output")).findElement(By.cssSelector(".infobox"));
            }
            catch (Exception e){
                logger.error("Não encontrou a infobox, não extrairá informação desse filme.");
                continue;
            }

            MovieDto dto = new MovieDto();
            dto.setHashLink(Util.generateStringHash(link.getValue()));
            dto.setTitulo(link.getKey());
            dto.setResumo(driver.findElement(By.cssSelector(".mw-parser-output p")).getText());
            dto.setUrl(link.getValue());

            List<WebElement> trs = driver.findElement(By.id("mw-content-text")).findElement(By.cssSelector(".mw-parser-output")).findElement(By.cssSelector(".infobox")).findElements(By.tagName("tr"));

            for(WebElement tr : trs){
                if(dto.getDuracaoEmMinutos() < 0 && getDuracao(tr) > 0){
                    dto.setDuracaoEmMinutos(getDuracao(tr));
                }
                if(dto.getGenero() == null && tr.findElements(By.tagName("td")).size() >= 2  &&!tr.getText().equals("") && removerAcentos(tr.getText()).startsWith("Genero")){
                    List<String> generos = getGenero(tr.findElements(By.tagName("td")).get(1));
                    dto.setGenero(generos);
                }
                if(dto.getGenero() == null && tr.findElements(By.tagName("td")).size() >= 2 &&!tr.getText().equals("") && removerAcentos(tr.getText()).startsWith("Elenco")){
                    List<ActorDto> elenco = getElenco(tr.findElements(By.tagName("td")).get(1));
                    dto.setElenco(elenco);
                }
            }
            filmes.add(dto);
        }

        driver.quit();
        return filmes;
    }

    private List<ActorDto> getElenco (WebElement td){
        List<ActorDto> atores = new ArrayList<>();
        List<WebElement> elenco = td.findElements(By.tagName("a"));
        for(WebElement ator : elenco){
            atores.add(new ActorDto(ator.getText(),generateStringHash(removerAcentos(ator.getText()))));
        }
        return atores;
    }

    private List<String> getGenero (WebElement td){
        List<String> generos = new ArrayList<>();
        List<WebElement> links = td.findElements(By.tagName("a"));
        for(WebElement a : links){
            generos.add(a.getText());
        }
        return generos;
    }

    private int getDuracao (WebElement tr){
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
                try {
                    Integer.valueOf(th.getText().substring(length - 8, length - 5).trim());
                }
                catch (Exception e){
                    logger.error("Erro ao extrair duração do filme.");
                    return -1;
                }
                return Integer.valueOf(th.getText().substring(length-8,length-5).trim());
            }
        }
        return -1;
    }

}
