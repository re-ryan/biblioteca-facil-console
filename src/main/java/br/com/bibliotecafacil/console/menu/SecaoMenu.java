package br.com.bibliotecafacil.console.menu;

import java.util.ArrayList;
import java.util.List;

public class SecaoMenu {

    private final String titulo;
    private final List<OpcaoMenu> opcoes;

    private SecaoMenu(final String titulo, final List<OpcaoMenu> opcoes) {
        validarTitulo(titulo);
        validarOpcoes(opcoes);

        this.titulo = titulo.trim();
        this.opcoes = List.copyOf(opcoes);
    }

    public static SecaoMenu criar(final String titulo, final List<OpcaoMenu> opcoes) {
        return new SecaoMenu(titulo, opcoes);
    }

    public List<OpcaoMenu> obterOpcoes() {
        return opcoes;
    }

    public List<String> obterLinhas() {
        List<String> linhas = new ArrayList<>();

        linhas.add(titulo);

        for (final OpcaoMenu opcaoMenu : opcoes) {
            linhas.add(opcaoMenu.formatar());
        }

        return linhas;
    }

    private void validarTitulo(final String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título da seção de menu é obrigatório.");
        }
    }

    private void validarOpcoes(final List<OpcaoMenu> opcoes) {
        if (opcoes == null || opcoes.isEmpty()) {
            throw new IllegalArgumentException("Seção de menu deve possuir ao menos uma opção.");
        }
    }
}
