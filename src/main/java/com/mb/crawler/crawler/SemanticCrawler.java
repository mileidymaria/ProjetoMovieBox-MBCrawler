package com.mb.crawler.crawler;

import com.mb.crawler.model.dto.TVSerieDto;

import java.util.Collection;

public interface SemanticCrawler {
    Collection<TVSerieDto> crawl(String nationality);
}
