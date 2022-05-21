package com.mb.crawler.model.dto;

public class FilmeDto {
    private String titulo;
    private String wikipediaUrl;
    private int duracaoEmMinutos;
    private String resumo;
    private String genero;

    public FilmeDto(String titulo, String wikipediaUrl, int duracaoEmMinutos, String resumo, String genero) {
        this.titulo = titulo;
        this.wikipediaUrl = wikipediaUrl;
        this.duracaoEmMinutos = duracaoEmMinutos;
        this.resumo = resumo;
        this.genero = genero;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
