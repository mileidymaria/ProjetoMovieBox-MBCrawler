package com.mb.crawler.model.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public final class DPB {
    private static final Model M_MODEL = ModelFactory.createDefaultModel();
    public static final String NS = "http://dbpedia.org/property/";
    public static final Resource NAMESPACE;
    public static final Property NAME;

    static {
        NAMESPACE = M_MODEL.createResource("http://dbpedia.org/property/");
        NAME = M_MODEL.createProperty("http://dbpedia.org/property/name");
    }
}
