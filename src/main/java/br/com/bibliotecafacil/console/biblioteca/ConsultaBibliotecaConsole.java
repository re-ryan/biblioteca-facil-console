package br.com.bibliotecafacil.console.biblioteca;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.bibliotecafacil.console.Console;
import br.com.bibliotecafacil.console.biblioteca.client.BibliotecaApiClient;
import br.com.bibliotecafacil.console.biblioteca.dto.ConsultaBibliotecaDto;

@Component
public class ConsultaBibliotecaConsole {

    private final Console console;
    private final BibliotecaApiClient bibliotecaApiClient;

    public ConsultaBibliotecaConsole(final Console console, final BibliotecaApiClient bibliotecaApiClient) {
        this.console = console;
        this.bibliotecaApiClient = bibliotecaApiClient;
    }

    public void consultar() {
        try {
            List<ConsultaBibliotecaDto> bibliotecas = this.bibliotecaApiClient.listar();

            if (bibliotecas.isEmpty()) {
                this.console.exibirMensagem("Nenhuma biblioteca cadastrada.");
                this.console.aguardarEnter();
                return;
            }

            this.console.exibirMensagem(montarMensagem(bibliotecas));
        } catch (final IllegalArgumentException e) {
            this.console.exibirMensagem(e.getMessage());
        }

        this.console.aguardarEnter();
    }

    private String montarMensagem(final List<ConsultaBibliotecaDto> bibliotecas) {
        StringBuilder mensagem = new StringBuilder("Bibliotecas cadastradas: ");

        for (final ConsultaBibliotecaDto biblioteca : bibliotecas) {
            mensagem.append(this.formatarBiblioteca(biblioteca));
        }

        return mensagem.toString();
    }

    private String formatarBiblioteca(final ConsultaBibliotecaDto biblioteca) {
        return "[" +
                "Código: " + biblioteca.getId() +
                " | Nome: " + biblioteca.getNome() +
                " | CPF/CNPJ: " + biblioteca.getCpfCnpj() +
                " | E-mail: " + biblioteca.getEmail() +
                " | Telefone: " + formatarOpcional(biblioteca.getTelefone()) +
                " | Endereço: " + formatarEndereco(biblioteca) +
                " | Ativo: " + formatarAtivo(biblioteca) +
                "] ";
    }

    private String formatarEndereco(final ConsultaBibliotecaDto biblioteca) {
        return biblioteca.getLogradouro() +
                ", " + biblioteca.getNumero() +
                complemento(biblioteca) +
                " - " + biblioteca.getBairro() +
                " - " + biblioteca.getCidade() +
                "/" + biblioteca.getUf() +
                " - CEP: " + biblioteca.getCep();
    }

    private String complemento(final ConsultaBibliotecaDto biblioteca) {
        if (biblioteca.getComplemento() == null || biblioteca.getComplemento().isBlank()) {
            return "";
        }

        return " - " + biblioteca.getComplemento();
    }

    private String formatarOpcional(final String valor) {
        if (valor == null || valor.isBlank()) {
            return "Não informado";
        }

        return valor;
    }

    private String formatarAtivo(final ConsultaBibliotecaDto biblioteca) {
        if (Boolean.TRUE.equals(biblioteca.getAtivo())) {
            return "Sim";
        }

        return "Não";
    }
}