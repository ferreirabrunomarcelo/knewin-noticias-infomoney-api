package com.knewin.noticiasinfomoney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.knewin.noticiasinfomoney.exception.URLInvalidaException;
import com.knewin.noticiasinfomoney.model.Noticia;
import com.knewin.noticiasinfomoney.repository.NoticiaRepository;
import com.knewin.noticiasinfomoney.service.NoticiaService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NoticiaServiceTests {

    @InjectMocks
    NoticiaService noticiaService;

    @Mock
    NoticiaRepository noticiaRepository;

    @Test
    public void formatarDataSemAtualizadoTest() throws IOException {

        String url = "https://www.infomoney.com.br/colunistas/economia-e-politica-direto-ao-ponto/o-erro-de-macri-foi-nao-ter-sido-bolsonaro/?__twitter_impression=true";

        Document document;
        document = Jsoup.connect(url).get();

        Element data = document.getElementsByClass("posted-on").first();

        String dataFormatada = noticiaService.formatarData(data);
        assertEquals("13 ago 2019 13h35", dataFormatada);
    }

    @Test
    public void formatarDataComAtualizadoTest() throws IOException {

        String url = "https://www.infomoney.com.br/mercados/itausa-lucra-123-mais-no-1o-tri-a-r-24-bi-prejuizo-da-marisa-cai-50-e-mais-balancos-petrobras-petrorio-e-outros-destaques/";
        Document document;
        document = Jsoup.connect(url).get();

        Element data = document.getElementsByClass("posted-on").first();

        String dataFormatada = noticiaService.formatarData(data);
        assertEquals("11 maio 2021 08h24", dataFormatada);
    }

    @Test
    public void buscarTodasNoticiasTest() {

        List<Noticia> listaNoticias = new ArrayList<Noticia>();

        Noticia noticia = new Noticia();

        for (int i = 0; i < 10; i++) {
            listaNoticias.add(noticia);
        }

        when(noticiaRepository.findAll()).thenReturn(listaNoticias);

        List<Noticia> listaNoticasRetornadas = noticiaRepository.findAll();

        assertEquals(listaNoticasRetornadas.size(), 10);
        assertNotNull(listaNoticasRetornadas);
        verify(noticiaRepository).findAll();
    }

    @Test
    public void converterUrlInvalidaOutroSiteParaNoticiaTest() {
      
        String url = "https://www.google.com/";
        Assertions.assertThrows(URLInvalidaException.class, () -> noticiaService.cadastrarNoticia(url));
    }

    @Test
    public void converterUrlInvalidaInfomoneyParaNoticiaTest() {
       
        String url = "https://www.infomoney.com.br/ferramentas/fliper/";
        Assertions.assertThrows(URLInvalidaException.class, () -> noticiaService.cadastrarNoticia(url));
    }

    @Test
    public void verificarTituloConverterUrlValidaTest() {
        
        String url = "https://www.infomoney.com.br/mercados/com-tempestade-perfeita-do-minerio-vale-perde-espaco-e-analistas-apontam-mais-oportunidades-em-acoes-de-siderurgicas/";
        Noticia noticia = noticiaService.converterUrlParaNoticia(url);
        assertEquals(noticia.getTitulo(),
                "Com “tempestade perfeita” do minério, Vale perde espaço e analistas apontam mais oportunidades em ações de siderúrgicas");
    }

    @Test
    public void verificarSubtituloConverterUrlValidaTest() {
       
        String url = "https://www.infomoney.com.br/mercados/com-tempestade-perfeita-do-minerio-vale-perde-espaco-e-analistas-apontam-mais-oportunidades-em-acoes-de-siderurgicas/";
        Noticia noticia = noticiaService.converterUrlParaNoticia(url);
        assertEquals(noticia.getSubtitulo(),
                "Bradesco BBI ressalta que Usiminas é a sua preferida no setor, enquanto analista da Encore destaca que Gerdau está menos exposta a cenário para minério");
    }

    @Test
    public void verificarAutorConverterUrlValidaTest() {
        
        String url = "https://www.infomoney.com.br/mercados/com-tempestade-perfeita-do-minerio-vale-perde-espaco-e-analistas-apontam-mais-oportunidades-em-acoes-de-siderurgicas/";
        Noticia noticia = noticiaService.converterUrlParaNoticia(url);
        assertEquals(noticia.getAutor(), "Por Lara Rizério");
    }

    @Test
    public void verificarDataConverterUrlValidaTest() {
        
        String url = "https://www.infomoney.com.br/mercados/com-tempestade-perfeita-do-minerio-vale-perde-espaco-e-analistas-apontam-mais-oportunidades-em-acoes-de-siderurgicas/";
        Noticia noticia = noticiaService.converterUrlParaNoticia(url);
        assertEquals(noticia.getData(), "21 set 2021 14h59");
    }

    @Test
    public void verificarConteudoConverterUrlValidaTest() {
        
        String url = "https://www.infomoney.com.br/mercados/com-tempestade-perfeita-do-minerio-vale-perde-espaco-e-analistas-apontam-mais-oportunidades-em-acoes-de-siderurgicas/";
        Noticia noticia = noticiaService.converterUrlParaNoticia(url);
        assertNotNull(noticia.getConteudo());
    }

}
