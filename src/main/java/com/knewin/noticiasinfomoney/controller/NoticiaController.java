package com.knewin.noticiasinfomoney.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.knewin.noticiasinfomoney.DTO.NoticiaDTO;
import com.knewin.noticiasinfomoney.service.NoticiaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class NoticiaController {

    private NoticiaService noticiaService;

    public NoticiaController(NoticiaService noticiaService){
        this.noticiaService = noticiaService;
    }

    @PostMapping("/nova-noticia")
    public ResponseEntity<NoticiaDTO> cadastrarNoticia(@RequestBody String url) {
        NoticiaDTO noticia = new NoticiaDTO(noticiaService.cadastrarNoticia(url));
        return ResponseEntity.status(HttpStatus.CREATED).body(noticia);  
    }

    @GetMapping("/noticias")
    public ResponseEntity<List<NoticiaDTO>> buscarTodasNoticias() {
        List<NoticiaDTO> listaNoticiasDTOs = this.noticiaService.buscarTodasNoticias().stream().map(NoticiaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaNoticiasDTOs);
    }
    
}
