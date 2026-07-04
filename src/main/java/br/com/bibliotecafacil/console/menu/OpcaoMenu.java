package br.com.bibliotecafacil.console.menu;

import java.util.Objects;

public class OpcaoMenu {

    private final String codigo;
    private final String descricao;
    private final Runnable acao;
    private final boolean manterMenuAberto;

    private OpcaoMenu(final String codigo, final String descricao) {
        validarCodigo(codigo);
        validarDescricao(descricao);

        this.codigo = codigo.trim();
        this.descricao = descricao.trim();
        this.acao = () -> {
        };
        this.manterMenuAberto = true;
    }

    private OpcaoMenu(final OpcaoMenu opcaoMenu, final Runnable acao) {
        Objects.requireNonNull(opcaoMenu);

        this.codigo = opcaoMenu.codigo;
        this.descricao = opcaoMenu.descricao;
        this.acao = Objects.requireNonNull(acao);
        this.manterMenuAberto = opcaoMenu.manterMenuAberto;
    }

    private OpcaoMenu(final OpcaoMenu opcaoMenu, final boolean manterMenuAberto) {
        Objects.requireNonNull(opcaoMenu);

        this.codigo = opcaoMenu.codigo;
        this.descricao = opcaoMenu.descricao;
        this.acao = opcaoMenu.acao;
        this.manterMenuAberto = manterMenuAberto;
    }

    public static OpcaoMenu criar(final String codigo, final String descricao) {
        return new OpcaoMenu(codigo, descricao);
    }

    public OpcaoMenu aoSelecionar(final Runnable acao) {
        return new OpcaoMenu(this, acao);
    }

    public OpcaoMenu fecharMenu() {
        return new OpcaoMenu(this, false);
    }

    public boolean possuiCodigo(final String codigoInformado) {
        return codigo.equals(codigoInformado);
    }

    public void executar() {
        acao.run();
    }

    public boolean deveManterMenuAberto() {
        return manterMenuAberto;
    }

    public String formatar() {
        return "   " + codigo + ". " + descricao;
    }

    private void validarCodigo(final String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código da opção de menu é obrigatório.");
        }
    }

    private void validarDescricao(final String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição da opção de menu é obrigatória.");
        }
    }
}
