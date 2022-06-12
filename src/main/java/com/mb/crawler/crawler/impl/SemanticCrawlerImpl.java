package com.mb.crawler.crawler.impl;

import com.mb.crawler.annotation.Crawler;
import com.mb.crawler.controller.CrawlerController;
import com.mb.crawler.crawler.SemanticCrawler;
import com.mb.crawler.model.dto.TVSerieDto;
import com.mb.crawler.model.vocabulary.DBO;
import com.mb.crawler.model.vocabulary.DPB;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Crawler
public class SemanticCrawlerImpl implements SemanticCrawler {
    private static final Logger logger = LoggerFactory.getLogger(SemanticCrawlerImpl.class);
    CharsetEncoder enc = Charset.forName("UTF-8").newEncoder();
    public static final String BASE_DBPEDIA_URL_STRING = "https://dbpedia.org/resource/List_of_Brazilian_television_series";

    @Override
    public Collection<TVSerieDto> crawl(){

        Collection<TVSerieDto> series = new ArrayList<>();
        Model graph = ModelFactory.createDefaultModel();
        graph.read(BASE_DBPEDIA_URL_STRING);
        List<Statement> triples = graph.listStatements((Resource) null, DBO.WIKI_PAGE_WIKI_LINK, (RDFNode)null).toList();

        for(Statement triple : triples){
            if(triple.getResource().toString().equals(BASE_DBPEDIA_URL_STRING)){
                continue;
            }

            if(!triple.getObject().isResource()){
                continue;
            }

            Triple actualTriple = triple.asTriple();
            try{
                graph.read(actualTriple.getObject().toString());
            }
            catch (Exception e){
                logger.error("Error when read resources from URI " + actualTriple.getObject().toString());
            }

            TVSerieDto tvSerieDto = crawSingleTVSerie(graph);
            if(tvSerieDto == null){
                continue;
            }
            series.add(tvSerieDto);
        }

        System.out.println("dont know what to do");

        return series;
    }

    public TVSerieDto crawSingleTVSerie (Model graph){
        TVSerieDto tvSerieDto = new TVSerieDto();
        if(graph.listObjectsOfProperty(DBO.ABSTRACT).hasNext() && graph.listObjectsOfProperty(DPB.NAME).hasNext() && graph.listObjectsOfProperty(DPB.NAME).hasNext()){
            tvSerieDto.setResumo(graph.listObjectsOfProperty(DBO.ABSTRACT).toList().get(0).toString());
            tvSerieDto.setTitulo(graph.listObjectsOfProperty(DPB.NAME).toList().get(0).toString());
            tvSerieDto.setUrl(graph.listObjectsOfProperty(DPB.NAME).toList().get(0).toString());
        }
        else{
            return null;
        }

        if(!graph.listObjectsOfProperty(DBO.LANGUAGE).hasNext()){
            return tvSerieDto;
        }
        tvSerieDto.setLanguage(graph.listObjectsOfProperty(DBO.LANGUAGE).toList().get(0).toString());
        if(!graph.listObjectsOfProperty(DBO.GENRE).hasNext()){
            return tvSerieDto;
        }
        tvSerieDto.setGenres(graph.listObjectsOfProperty(DBO.GENRE).toList().stream().map(genreObj -> genreObj.toString()).collect(Collectors.toList()));
        return tvSerieDto;
    }
}
