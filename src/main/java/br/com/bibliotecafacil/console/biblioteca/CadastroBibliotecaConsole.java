package br.com.bibliotecafacil.console.biblioteca;

import br.com.bibliotecafacil.console.Console;
import br.com.bibliotecafacil.console.biblioteca.client.BibliotecaApiClient;
import br.com.bibliotecafacil.console.biblioteca.dto.CadastroBibliotecaDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@Component
@Profile("console")
public class CadastroBibliotecaConsole {

    private static final String TITULO_BIBLIOTECA = "Cadastro de biblioteca";
    private static final String TITULO_ENDERECO = "Cadastro de endereço";
    private static final Pattern CARACTERES_NAO_NUMERICOS = Pattern.compile("\\D");

    private final Console console;
    private final BibliotecaApiClient bibliotecaApiClient;

    public CadastroBibliotecaConsole(final Console console, final BibliotecaApiClient bibliotecaApiClient) {
        this.console = console;
        this.bibliotecaApiClient = bibliotecaApiClient;
    }

    public void cadastrar() {
        try {
            CadastroBibliotecaDto cadastro = lerCadastro();
            bibliotecaApiClient.cadastrar(cadastro);

            this.console.exibirMensagem("Biblioteca cadastrada com sucesso.");
        } catch (final IllegalArgumentException e) {
            this.console.exibirMensagem(e.getMessage());
        }

        this.console.aguardarEnter();
    }

    private CadastroBibliotecaDto lerCadastro() {
        CadastroBibliotecaDto cadastro = new CadastroBibliotecaDto();

        preencherBiblioteca(cadastro);
        preencherEndereco(cadastro);

        return cadastro;
    }

    private void preencherBiblioteca(final CadastroBibliotecaDto cadastro) {
        cadastro.setNome(this.console.lerTextoObrigatorio(TITULO_BIBLIOTECA, "Nome"));
        cadastro.setCpfCnpj(lerNumerosObrigatorios(TITULO_BIBLIOTECA, "CPF/CNPJ"));
        cadastro.setEmail(this.console.lerTextoObrigatorio(TITULO_BIBLIOTECA, "E-mail"));
        cadastro.setTelefone(lerNumerosOpcionais(TITULO_BIBLIOTECA, "Telefone - opcional"));
    }

    private void preencherEndereco(final CadastroBibliotecaDto cadastro) {
        cadastro.setCep(lerNumerosObrigatorios(TITULO_ENDERECO, "CEP"));
        cadastro.setLogradouro(this.console.lerTextoObrigatorio(TITULO_ENDERECO, "Logradouro"));
        cadastro.setNumero(this.console.lerTextoObrigatorio(TITULO_ENDERECO, "Número"));
        cadastro.setComplemento(this.console.lerTexto(TITULO_ENDERECO, "Complemento - opcional"));
        cadastro.setBairro(this.console.lerTextoObrigatorio(TITULO_ENDERECO, "Bairro"));
        cadastro.setCidade(this.console.lerTextoObrigatorio(TITULO_ENDERECO, "Cidade"));
        cadastro.setUf(lerUf());
        cadastro.setLatitude(lerDecimalOpcional(TITULO_ENDERECO, "Latitude - opcional"));
        cadastro.setLongitude(lerDecimalOpcional(TITULO_ENDERECO, "Longitude - opcional"));
    }

    private String lerNumerosObrigatorios(final String titulo, final String campo) {
        while (true) {
            String valor = removerCaracteresNaoNumericos(this.console.lerTextoObrigatorio(titulo, campo));

            if (!valor.isBlank()) {
                return valor;
            }

            this.console.exibirMensagem("O campo " + campo + " deve possuir números.");
            this.console.aguardarEnter();
        }
    }

    private String lerNumerosOpcionais(final String titulo, final String campo) {
        String valor = removerCaracteresNaoNumericos(this.console.lerTexto(titulo, campo));

        if (valor.isBlank()) {
            return null;
        }

        return valor;
    }

    private String lerUf() {
        while (true) {
            String valor = this.console.lerTextoObrigatorio(TITULO_ENDERECO, "UF").toUpperCase();

            if (valor.length() == 2) {
                return valor;
            }

            this.console.exibirMensagem("UF deve possuir exatamente 2 caracteres.");
            this.console.aguardarEnter();
        }
    }

    private BigDecimal lerDecimalOpcional(final String titulo, final String campo) {
        while (true) {
            String valor = this.console.lerTexto(titulo, campo);

            if (valor.isBlank()) {
                return null;
            }

            try {
                return new BigDecimal(valor.replace(",", "."));
            } catch (final NumberFormatException e) {
                this.console.exibirMensagem("O campo " + campo + " deve possuir um número decimal válido.");
                this.console.aguardarEnter();
            }
        }
    }

    private String removerCaracteresNaoNumericos(final String valor) {
        return CARACTERES_NAO_NUMERICOS.matcher(valor).replaceAll("");
    }
}