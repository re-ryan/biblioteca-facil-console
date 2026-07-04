package br.com.bibliotecafacil.console;

import br.com.bibliotecafacil.console.acervo.menu.AcervoMenu;
import br.com.bibliotecafacil.console.biblioteca.menu.BibliotecaMenu;
import br.com.bibliotecafacil.console.usuario.menu.UsuarioMenu;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Profile("console")
public class RoteadorConsole {

    private final AcervoMenu acervoMenu;
    private final BibliotecaMenu bibliotecaMenu;
    private final UsuarioMenu usuarioMenu;

    public RoteadorConsole(
            final AcervoMenu acervoMenu,
            final BibliotecaMenu bibliotecaMenu,
            final UsuarioMenu usuarioMenu
    ) {
        this.acervoMenu = Objects.requireNonNull(acervoMenu);
        this.bibliotecaMenu = Objects.requireNonNull(bibliotecaMenu);
        this.usuarioMenu = Objects.requireNonNull(usuarioMenu);
    }

    public void abrirAcervo() {
        this.acervoMenu.exibir();
    }

    public void abrirBibliotecas() {
        this.bibliotecaMenu.exibir();
    }

    public void abrirUsuarios() {
        this.usuarioMenu.exibir();
    }
}
