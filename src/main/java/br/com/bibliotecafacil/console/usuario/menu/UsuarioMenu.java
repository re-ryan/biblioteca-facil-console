package br.com.bibliotecafacil.console.usuario.menu;

import br.com.bibliotecafacil.console.Console;
import br.com.bibliotecafacil.console.menu.ConsoleMenu;
import br.com.bibliotecafacil.console.menu.OpcaoMenu;
import br.com.bibliotecafacil.console.menu.SecaoMenu;
import br.com.bibliotecafacil.console.usuario.UsuarioConsole;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Profile("console")
public class UsuarioMenu extends ConsoleMenu {

    private final UsuarioConsole usuarioConsole;

    public UsuarioMenu(final Console consoleFacade, final UsuarioConsole usuarioConsole) {
        super(consoleFacade);
        this.usuarioConsole = Objects.requireNonNull(usuarioConsole);
    }

    @Override
    protected List<SecaoMenu> obterSecoes() {
        List<SecaoMenu> secoes = new ArrayList<>();

        secoes.add(criarSecaoUsuarios());

        return secoes;
    }

    @Override
    protected List<OpcaoMenu> obterOpcoesRodape() {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        opcoes.add(OpcaoMenu.criar("0", "Voltar").fecharMenu());

        return opcoes;
    }

    private SecaoMenu criarSecaoUsuarios() {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        opcoes.add(OpcaoMenu.criar("1", "Cadastrar usuário").aoSelecionar(usuarioConsole::cadastrar));
        opcoes.add(OpcaoMenu.criar("2", "Listar usuários").aoSelecionar(usuarioConsole::listar));

        return SecaoMenu.criar("Usuários", opcoes);
    }
}
