package com.mb.crawler.model.dto;

import java.util.List;
import java.util.Objects;

public class FilmeDto {
    private int hashLink;
    private String titulo;
    private String wikipediaUrl;
    private int duracaoEmMinutos;
    private String resumo;
    private List<String> genero;
    private List<AtorDto> elenco;

    public FilmeDto() {
        this.duracaoEmMinutos = -1;
    }

    public int getHashLink() {
        return hashLink;
    }

    public void setHashLink(int hashLink) {
        this.hashLink = hashLink;
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

    public List<String> getGenero() {
        return genero;
    }

    public void setGenero(List<String> genero) {
        this.genero = genero;
    }

    public List<AtorDto> getElenco() {
        return elenco;
    }

    public void setElenco(List<AtorDto> elenco) {
        this.elenco = elenco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmeDto)) return false;
        FilmeDto filmeDto = (FilmeDto) o;
        return getWikipediaUrl().equals(filmeDto.getWikipediaUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWikipediaUrl());
    }
}
