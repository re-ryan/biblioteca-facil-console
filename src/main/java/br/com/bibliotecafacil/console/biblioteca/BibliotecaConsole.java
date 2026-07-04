package br.com.bibliotecafacil.console.biblioteca;

import org.springframework.stereotype.Component;

@Component
public class BibliotecaConsole {

    private final CadastroBibliotecaConsole cadastroBibliotecaConsole;
    private final ConsultaBibliotecaConsole consultaBibliotecaConsole;

    public BibliotecaConsole(
            final CadastroBibliotecaConsole cadastroBibliotecaConsole,
            final ConsultaBibliotecaConsole consultaBibliotecaConsole
    ) {
        this.cadastroBibliotecaConsole = cadastroBibliotecaConsole;
        this.consultaBibliotecaConsole = consultaBibliotecaConsole;
    }

    public void cadastrar() {
        this.cadastroBibliotecaConsole.cadastrar();
    }

    public void consultar() {
        this.consultaBibliotecaConsole.consultar();
    }
}
