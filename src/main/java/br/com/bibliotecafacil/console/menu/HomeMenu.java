package br.com.bibliotecafacil.console.menu;

import br.com.bibliotecafacil.console.Console;
import br.com.bibliotecafacil.console.RoteadorConsole;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Profile("console")
public class HomeMenu extends ConsoleMenu {

    private final RoteadorConsole navegacao;

    public HomeMenu(final Console consoleFacade, final RoteadorConsole roteador) {
        super(consoleFacade);
        this.navegacao = Objects.requireNonNull(roteador);
    }

    @Override
    protected List<SecaoMenu> obterSecoes() {
        List<SecaoMenu> secoes = new ArrayList<>();

        secoes.add(criarSecaoAdministracao());
        secoes.add(criarSecaoConsultaPublica());

        return secoes;
    }

    @Override
    protected List<OpcaoMenu> obterOpcoesRodape() {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        opcoes.add(OpcaoMenu.criar("0", "Encerrar aplicação").aoSelecionar(() -> exibirMensagem("Biblioteca Fácil finalizado.")).fecharMenu());

        return opcoes;
    }

    private SecaoMenu criarSecaoAdministracao() {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        opcoes.add(OpcaoMenu.criar("1", "Acervo").aoSelecionar(navegacao::abrirAcervo));
        opcoes.add(OpcaoMenu.criar("2", "Bibliotecas").aoSelecionar(navegacao::abrirBibliotecas));
        opcoes.add(OpcaoMenu.criar("3", "Usuários").aoSelecionar(navegacao::abrirUsuarios));

        return SecaoMenu.criar("Administração", opcoes);
    }

    private SecaoMenu criarSecaoConsultaPublica() {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        opcoes.add(OpcaoMenu.criar("4", "Buscar livros na rede").aoSelecionar(() -> exibirModuloEmConstrucao("Busca de livros na rede")));

        return SecaoMenu.criar("Consulta pública", opcoes);
    }
}
