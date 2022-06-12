package com.mb.crawler.model.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public final class DBO {
    private static final Model M_MODEL = ModelFactory.createDefaultModel();
    public static final String NS = "http://dbpedia.org/ontology/";
    public static final Resource NAMESPACE;
    public static final Property WIKI_PAGE_WIKI_LINK;
    public static final Property ABSTRACT;
    public static final Property CHANNEL;
    public static final Property CREATOR;
    public static final Property COUNTRY;
    public static final Property GENRE;
    public static final Property LANGUAGE;
    public static final Property STARRING;

    static {
        NAMESPACE = M_MODEL.createResource("http://dbpedia.org/ontology/");
        WIKI_PAGE_WIKI_LINK = M_MODEL.createProperty("http://dbpedia.org/ontology/wikiPageWikiLink");
        ABSTRACT = M_MODEL.createProperty("http://dbpedia.org/ontology/abstract");
        CHANNEL = M_MODEL.createProperty("http://dbpedia.org/ontology/creator");
        CREATOR = M_MODEL.createProperty("http://dbpedia.org/ontology/channel");
        COUNTRY = M_MODEL.createProperty("http://dbpedia.org/ontology/country");
        GENRE = M_MODEL.createProperty("http://dbpedia.org/ontology/genre");
        LANGUAGE = M_MODEL.createProperty("http://dbpedia.org/ontology/language");
        STARRING = M_MODEL.createProperty("https://dbpedia.org/ontology/starring");
    }

}
