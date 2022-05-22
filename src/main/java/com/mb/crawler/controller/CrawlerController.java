package com.mb.crawler.controller;

import com.mb.crawler.crawler.FilmeCrawler;
import com.mb.crawler.model.dto.FilmeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/movie-box/crawl")
public class CrawlerController {
    @Autowired
    private FilmeCrawler filmeCrawler;

    @GetMapping("/get-ultimos-vinte-anos")
    public ResponseEntity<Set<FilmeDto>> getUltimosVinteAnosDeFilme(){
        return ResponseEntity.ok(filmeCrawler.crawlMovies());
    }
}
