package com.mb.crawler.model.dto;


public class ActorDto {
    private String nome;
    private int hashNome;

    public ActorDto(String nome, int hashNome) {
        this.nome = nome;
        this.hashNome = hashNome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHashNome() {
        return hashNome;
    }

    public void setHashNome(int hashNome) {
        this.hashNome = hashNome;
    }
}
