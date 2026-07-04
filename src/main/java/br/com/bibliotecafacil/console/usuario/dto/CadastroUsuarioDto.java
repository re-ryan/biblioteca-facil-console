package br.com.bibliotecafacil.console.usuario.dto;

import java.time.LocalDate;

public class CadastroUsuarioDto {

    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String login;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(final TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}