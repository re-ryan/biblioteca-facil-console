package br.com.bibliotecafacil.console.acervo.menu;

import br.com.bibliotecafacil.console.Console;
import br.com.bibliotecafacil.console.menu.ConsoleMenu;
import br.com.bibliotecafacil.console.menu.OpcaoMenu;
import br.com.bibliotecafacil.console.menu.SecaoMenu;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("console")
public class AcervoMenu extends ConsoleMenu {

    public AcervoMenu(final Console consoleFacade) {
        super(consoleFacade);
    }

    @Override
    protected List<SecaoMenu> obterSecoes() {
        List<SecaoMenu> secoes = new ArrayList<>();

        secoes.add(criarSecaoAcervo());

        return secoes;
    }

    @Override
    protected List<OpcaoMenu> obterOpcoesRodape() {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        opcoes.add(OpcaoMenu.criar("0", "Voltar").fecharMenu());

        return opcoes;
    }

    private SecaoMenu criarSecaoAcervo() {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        opcoes.add(OpcaoMenu.criar("1", "Cadastrar livro").aoSelecionar(() -> exibirModuloEmConstrucao("Cadastro de livro")));

        opcoes.add(OpcaoMenu.criar("2", "Importar livros").aoSelecionar(() -> exibirModuloEmConstrucao("Importação de livros")));

        opcoes.add(OpcaoMenu.criar("3", "Listar livros").aoSelecionar(() -> exibirModuloEmConstrucao("Listagem de livros")));

        return SecaoMenu.criar("Acervo", opcoes);
    }
}
