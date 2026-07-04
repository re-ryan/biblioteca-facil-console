package br.com.bibliotecafacil.console.api.dto;

import java.time.LocalDateTime;

public class ErroApiDto {

    private LocalDateTime dataHora;
    private int status;
    private String erro;
    private String mensagem;
    private String caminho;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(final LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(final String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(final String caminho) {
        this.caminho = caminho;
    }
}