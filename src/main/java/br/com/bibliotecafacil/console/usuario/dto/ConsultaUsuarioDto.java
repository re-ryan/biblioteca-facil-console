package br.com.bibliotecafacil.console.usuario.dto;

import java.time.LocalDate;

public class ConsultaUsuarioDto {

    private Long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String login;
    private String email;
    private TipoUsuario tipoUsuario;
    private Boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(final String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(final LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(final TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(final Boolean ativo) {
        this.ativo = ativo;
    }
}