package com.knewin.noticiasinfomoney.repository;

import com.knewin.noticiasinfomoney.model.Noticia;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticiaRepository extends MongoRepository<Noticia, String>{
    
}
