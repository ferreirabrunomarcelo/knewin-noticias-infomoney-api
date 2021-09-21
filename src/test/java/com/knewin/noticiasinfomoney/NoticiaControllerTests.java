package com.knewin.noticiasinfomoney;

import com.knewin.noticiasinfomoney.controller.NoticiaController;
import com.knewin.noticiasinfomoney.repository.NoticiaRepository;
import com.knewin.noticiasinfomoney.service.NoticiaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NoticiaController.class)
public class NoticiaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticiaService noticiaService;

    @MockBean
    private NoticiaRepository noticiaRepository;

    @MockBean
    private NoticiaController noticiaController;

    @Test
    public void buscarTodasNoticiasEndPointTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/noticias"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void cadastrarNovaNoticiaEndPointTest() throws Exception {

        String url = "https://www.infomoney.com.br/onde-investir/captacoes-de-recursos-com-metas-esg-geram-valor-ao-acionista-no-longo-prazo/";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/nova-noticia").contentType(MediaType.APPLICATION_JSON)
                .content(url)).andExpect(MockMvcResultMatchers.status().isOk());
    }


}
