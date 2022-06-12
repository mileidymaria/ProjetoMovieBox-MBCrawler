package com.mb.crawler.controller;

import com.mb.crawler.crawler.NonSemanticCrawler;
import com.mb.crawler.crawler.SemanticCrawler;
import com.mb.crawler.model.dto.MovieDto;
import com.mb.crawler.model.dto.TVSerieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/movie-box/crawl")
public class CrawlerController {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerController.class);
    @Autowired
    private NonSemanticCrawler nonSemanticCrawler;

    @Autowired
    private SemanticCrawler semanticCrawler;

    @GetMapping("/get-filmes-ultimos-vinte-anos")
    public ResponseEntity<Set<MovieDto>> getUltimosVinteAnosDeFilme(){
        logger.info("Recebendo requisição para crawlear os últimos 20 anos de filmes...");
        return ResponseEntity.ok(nonSemanticCrawler.crawl());
    }

    @GetMapping("/get-series-tv-ultimos-vinte-anos")
    public ResponseEntity<Collection<TVSerieDto>> getUltimosVinteAnosDeSerie(){
        logger.info("Recebendo requisição para crawlear os últimos 20 anos de filmes...");
        return ResponseEntity.ok(semanticCrawler.crawl());
    }
}
