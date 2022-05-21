package com.mb.crawler.util;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class Nlp {

    private static final Map<String, String> patterns = new HashMap<>(100){{

    }};

    public static String removerAcentos(String str){
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String cleaner(String str){
        return null;
    }
}
