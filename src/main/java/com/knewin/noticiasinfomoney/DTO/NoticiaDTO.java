package com.knewin.noticiasinfomoney.DTO;

import java.util.List;

import com.knewin.noticiasinfomoney.model.Noticia;

public class NoticiaDTO {

    private String url;
    private String titulo;
    private String subtitulo;
    private String autor;
    private String data; 
    private List<String> conteudo;  

    public NoticiaDTO() {
         
    }

    public NoticiaDTO (Noticia noticia) {
        this.toNoticia(noticia);
    }
    
    /**
     * Método que converte o objeto Notícia DTO para Notícia
     */
    public void toNoticia(Noticia noticia){
       this.setUrl(noticia.getUrl());
       this.setTitulo(noticia.getTitulo());
       this.setSubtitulo(noticia.getSubtitulo());
       this.setAutor(noticia.getAutor());
       this.setData(noticia.getData());
       this.setConteudo(noticia.getConteudo());
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
