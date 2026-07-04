package br.com.bibliotecafacil.console.menu;

import br.com.bibliotecafacil.console.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ConsoleMenu {

    private final Console consoleFacade;

    protected ConsoleMenu(final Console consoleFacade) {
        this.consoleFacade = Objects.requireNonNull(consoleFacade);
    }

    public final void exibir() {
        boolean executando = true;

        while (executando) {
            List<SecaoMenu> secoes = obterSecoes();
            List<OpcaoMenu> opcoesRodape = obterOpcoesRodape();

            String opcaoInformada = consoleFacade.lerOpcao(montarLinhasConteudo(secoes),montarLinhasRodape(opcoesRodape));

            executando = executarOpcao(opcaoInformada,obterTodasOpcoes(secoes, opcoesRodape));
        }
    }

    protected abstract List<SecaoMenu> obterSecoes();

    protected abstract List<OpcaoMenu> obterOpcoesRodape();

    protected void exibirModuloEmConstrucao(final String nomeModulo) {
        consoleFacade.exibirMensagem(nomeModulo + " ainda será implementado.");
        consoleFacade.aguardarEnter();
    }

    protected void exibirMensagem(final String mensagem) {
        consoleFacade.exibirMensagem(mensagem);
    }

    private boolean executarOpcao(final String opcaoInformada, final List<OpcaoMenu> opcoesMenu) {
        for (OpcaoMenu opcaoMenu : opcoesMenu) {
            if (opcaoMenu.possuiCodigo(opcaoInformada)) {
                opcaoMenu.executar();
                return opcaoMenu.deveManterMenuAberto();
            }
        }

        return exibirOpcaoInvalida();
    }

    private List<OpcaoMenu> obterTodasOpcoes(final List<SecaoMenu> secoes, final List<OpcaoMenu> opcoesRodape) {
        List<OpcaoMenu> opcoes = new ArrayList<>();

        for (SecaoMenu secaoMenu : secoes) {
            opcoes.addAll(secaoMenu.obterOpcoes());
        }

        opcoes.addAll(opcoesRodape);

        return opcoes;
    }

    private List<String> montarLinhasConteudo(final List<SecaoMenu> secoes) {
        List<String> linhas = new ArrayList<>();

        for (int indice = 0; indice < secoes.size(); indice++) {
            linhas.addAll(secoes.get(indice).obterLinhas());

            if (indice < secoes.size() - 1) {
                linhas.add("");
            }
        }

        return linhas;
    }

    private List<String> montarLinhasRodape(final List<OpcaoMenu> opcoesRodape) {
        List<String> linhas = new ArrayList<>();

        for (OpcaoMenu opcaoMenu : opcoesRodape) {
            linhas.add(opcaoMenu.formatar());
        }

        return linhas;
    }

    private boolean exibirOpcaoInvalida() {
        consoleFacade.exibirMensagem("Opção inválida.");
        consoleFacade.aguardarEnter();

        return true;
    }
}
