package br.com.bibliotecafacil.console.usuario;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("console")
public class UsuarioConsole {

    private final CadastroUsuarioConsole cadastroUsuarioConsole;
    private final ConsultaUsuarioConsole listagemUsuarioConsole;

    public UsuarioConsole(final CadastroUsuarioConsole cadastroUsuarioConsole, final ConsultaUsuarioConsole listagemUsuarioConsole) {
        this.cadastroUsuarioConsole = cadastroUsuarioConsole;
        this.listagemUsuarioConsole = listagemUsuarioConsole;
    }

    public void cadastrar() {
        this.cadastroUsuarioConsole.cadastrar();
    }

    public void listar() {
        this.listagemUsuarioConsole.listar();
    }
}
