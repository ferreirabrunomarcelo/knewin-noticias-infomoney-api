package com.knewin.noticiasinfomoney.exception;

import java.util.Date;

public class MensagemErroException {

    private int codigo;
    private Date horario;
    private String mensagem;
    private String descricao;

    public MensagemErroException(int codigo, Date horario, String mensagem, String descricao) {
        this.codigo = codigo;
        this.horario = horario;
        this.mensagem = mensagem;
        this.descricao = descricao;
    }
   
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
