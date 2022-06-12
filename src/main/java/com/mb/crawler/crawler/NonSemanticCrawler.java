package com.mb.crawler.crawler;

import com.mb.crawler.model.dto.MovieDto;

import java.util.Set;

public interface NonSemanticCrawler {
    Set<MovieDto> crawl();
}
