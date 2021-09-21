package com.knewin.noticiasinfomoney.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.knewin.noticiasinfomoney.exception.NoticiaExistenteException;
import com.knewin.noticiasinfomoney.exception.URLInvalidaException;
import com.knewin.noticiasinfomoney.model.Noticia;
import com.knewin.noticiasinfomoney.repository.NoticiaRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class NoticiaService {

    private NoticiaRepository noticiaRepository;

    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    /**
     * Método que cadastra novas notícias na base de dados
     * 
     * @param url
     * @return
     */
    public Noticia cadastrarNoticia(String url) {

        Noticia noticia = null;

        try {
            noticia = converterUrlParaNoticia(url);
            noticia = noticiaRepository.save(noticia);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            throw new NoticiaExistenteException("Essa notícia já foi cadastrada.");
        }
        return noticia;
    }

    /**
     * Método que converte uma url para o objeto notícia usando técnicas de
     * webscraping
     * 
     * @param url
     * @return
     */
    public Noticia converterUrlParaNoticia(String url) {

        Noticia noticia = null;
        Element titulo = null;
        Element subtitulo = null;
        Element autor = null;
        Element data = null;
        List<Element> conteudo = new ArrayList<Element>();
        List<String> paragrafos = new ArrayList<String>();

        try {
            
            //webscraping com o jsoup
            Document document;
            document = Jsoup.connect(url).get();
            titulo = document.getElementsByClass("page-title-1").first();
            subtitulo = document.getElementsByClass("article-lead").first();
            autor = document.getElementsByClass("author-name").first();
            data = document.getElementsByClass("posted-on").first();
            conteudo = document.select("div.article-content > p");
            
            // Adiciona os parágrafos do elemento conteúdo em uma lista de strings
            for (Element c : conteudo) {
                paragrafos.add(c.text());
            }

            noticia = new Noticia(url, titulo.text(), subtitulo.text(), autor.text(), formatarData(data), paragrafos);

        } catch (IOException | NullPointerException e) {
            throw new URLInvalidaException("A URL informada não é corresponde a uma notícia do Infomoney.");
        }
        return noticia;
    }

    /**
     * Método que formata a data de publicação em (dia/mês/ano hora:minuto)
     * 
     * @param data
     * @return
     */
    public String formatarData(Element data) {

        String[] dataFormatada = data.text().split(" -");
        return dataFormatada[0];
    }

    /**
     * Método que busca todas as notícias cadastradas na base de dados
     * 
     * @return
     */
    public List<Noticia> buscarTodasNoticias() {
        return this.noticiaRepository.findAll();
    }
}
