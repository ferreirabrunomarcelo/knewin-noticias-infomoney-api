package com.knewin.noticiasinfomoney.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "noticia")
public class Noticia {
   
    @Id
    private String id;
    @Indexed(unique = true)
    private String url;
    private String titulo;
    private String subtitulo;
    private String autor;
    private String data; 
    private List<String> conteudo;   

    public Noticia() { 

    }

    public Noticia(String url, String titulo, String subtitulo, String autor, String data, List<String> conteudo) {
        this.url = url;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.autor = autor; 
        this.data = data;
        this.conteudo = conteudo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getConteudo() {
        return conteudo;
    }

    public void setConteudo(List<String> conteudo) {
        this.conteudo = conteudo;
    }

}
