package com.mb.crawler.controller;

import com.mb.crawler.crawler.NonSemanticCrawler;
import com.mb.crawler.crawler.SemanticCrawler;
import com.mb.crawler.model.dto.MovieDto;
import com.mb.crawler.model.dto.TVSerieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
        logger.info("Recebendo requisição para crawlear os últimos 30 anos de filmes de vários países na wikipedia...");
        return ResponseEntity.ok(nonSemanticCrawler.crawl());
    }

    @GetMapping("/get-tv-series-by-nationality")
    public ResponseEntity<Collection<TVSerieDto>> getUltimosVinteAnosDeSerie(@RequestParam @NotBlank @Valid String nationality){
        logger.info("Recebendo requisição para crawlear series de nacionalidade " + nationality + "...");
        return ResponseEntity.ok(semanticCrawler.crawl(nationality));
    }
}
