package com.knewin.noticiasinfomoney.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerException {

    /**
     * Método que retorna a mensagem personalizada de exceção para URLs inválidas 
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(URLInvalidaException.class)
    public ResponseEntity<MensagemErroException> URLInvalidaException(URLInvalidaException exception,
            WebRequest request) {
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<MensagemErroException>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Método que retorna a mensagem personalizada de exceção quando a url recebida já
     * estiver cadastrada na base de dados
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(NoticiaExistenteException.class)
    public ResponseEntity<MensagemErroException> NoticiaExistenteException(NoticiaExistenteException exception,
    WebRequest request) {
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
        exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<MensagemErroException>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}