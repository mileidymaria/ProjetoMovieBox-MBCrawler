package com.mb.crawler.model.dto;

import java.util.List;
import java.util.Objects;

public class MovieDto {
    private int hashLink;
    private String titulo;
    private String url;
    private int duracaoEmMinutos;
    private String resumo;
    private List<String> genero;
    private List<ActorDto> elenco;

    public MovieDto() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<ActorDto> getElenco() {
        return elenco;
    }

    public void setElenco(List<ActorDto> elenco) {
        this.elenco = elenco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDto)) return false;
        MovieDto movieDto = (MovieDto) o;
        return getUrl().equals(movieDto.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }
}
