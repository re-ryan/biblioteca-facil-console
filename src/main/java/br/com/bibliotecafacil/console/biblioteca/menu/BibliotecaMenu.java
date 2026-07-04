package br.com.bibliotecafacil.console.biblioteca.menu;

import br.com.bibliotecafacil.console.Console;
import br.com.bibliotecafacil.console.biblioteca.BibliotecaConsole;
import br.com.bibliotecafacil.console.menu.ConsoleMenu;
import br.com.bibliotecafacil.console.menu.OpcaoMenu;
import br.com.bibliotecafacil.console.menu.SecaoMenu;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Profile("console")
public class BibliotecaMenu extends ConsoleMenu {

    private final BibliotecaConsole bibliotecaConsole;

    public BibliotecaMenu(final Console consoleFacade, final BibliotecaConsole bibliotecaConsole) {
        super(consoleFacade);
        this.bibliotecaConsole = Objects.requireNonNull(bibliotecaConsole);
    }

    @Override
    protected List<SecaoMenu> obterSecoes() {
        List<SecaoMenu> secoes = new ArrayList<>();

        secoes.add(criarSecaoBibliotecas());

        return secoes;
    }

    @Override
    protected List<OpcaoMenu> obterOpcoesRodape() {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        opcoes.add(OpcaoMenu.criar("0", "Voltar").fecharMenu());

        return opcoes;
    }

    private SecaoMenu criarSecaoBibliotecas() {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        opcoes.add(OpcaoMenu.criar("1", "Cadastrar biblioteca").aoSelecionar(bibliotecaConsole::cadastrar));
        opcoes.add(OpcaoMenu.criar("2", "Listar bibliotecas").aoSelecionar(bibliotecaConsole::consultar));

        return SecaoMenu.criar("Bibliotecas", opcoes);
    }
}
