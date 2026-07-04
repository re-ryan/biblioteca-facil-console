package br.com.bibliotecafacil.console;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Console {

    private final LeitorConsole leitorConsole;
    private final LayoutConsole painelConsole;

    public Console(final LeitorConsole leitorConsole, final LayoutConsole painelConsole) {
        this.leitorConsole = Objects.requireNonNull(leitorConsole);
        this.painelConsole = Objects.requireNonNull(painelConsole);
    }

    public String lerOpcao(final List<String> linhasConteudo, final List<String> linhasRodape) {
        return this.leitorConsole.lerOpcao(linhasConteudo, linhasRodape);
    }

    public String lerTexto(final String titulo, final String campo) {
        return this.leitorConsole.lerTexto(titulo, campo);
    }

    public String lerTextoObrigatorio(final String titulo, final String campo) {
        return this.leitorConsole.lerTextoObrigatorio(titulo, campo);
    }

    public void exibirMensagem(final String mensagem) {
        this.painelConsole.exibirMensagem(mensagem);
    }

    public void aguardarEnter() {
        this.leitorConsole.aguardarEnter();
    }
}
