package br.com.bibliotecafacil.console.usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.bibliotecafacil.console.Console;
import br.com.bibliotecafacil.console.usuario.client.UsuarioApiClient;
import br.com.bibliotecafacil.console.usuario.dto.CadastroUsuarioDto;
import br.com.bibliotecafacil.console.usuario.dto.TipoUsuario;

@Component
public class CadastroUsuarioConsole {

    private static final String TITULO = "Cadastro de usuário";
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Console console;
    private final UsuarioApiClient usuarioApiClient;

    public CadastroUsuarioConsole(final Console console, final UsuarioApiClient usuarioApiClient) {
        this.console = console;
        this.usuarioApiClient = usuarioApiClient;
    }

    public void cadastrar() {
        try {
            final CadastroUsuarioDto cadastro = lerCadastro();
            usuarioApiClient.cadastrar(cadastro);

            this.console.exibirMensagem("Usuário cadastrado com sucesso.");
        } catch (final IllegalArgumentException e) {
            this.console.exibirMensagem(e.getMessage());
        }

        this.console.aguardarEnter();
    }

    private CadastroUsuarioDto lerCadastro() {
        final CadastroUsuarioDto cadastro = new CadastroUsuarioDto();
        cadastro.setTipoUsuario(this.lerTipoUsuario());
        cadastro.setNomeCompleto(this.console.lerTextoObrigatorio(TITULO, "Nome completo"));
        cadastro.setDataNascimento(this.lerDataNascimento());
        cadastro.setLogin(this.console.lerTextoObrigatorio(TITULO, "Login"));
        cadastro.setEmail(this.console.lerTextoObrigatorio(TITULO, "E-mail"));
        cadastro.setSenha(this.console.lerTextoObrigatorio(TITULO, "Senha"));
        return cadastro;
    }

    private TipoUsuario lerTipoUsuario() {
        while (true) {
            String opcao = this.console.lerOpcao(
                    List.of(
                            "Cadastro de usuário",
                            "",
                            "Tipo de usuário",
                            "   1. Leitor",
                            "   2. Bibliotecário"
                    ),
                    List.of("0. Cancelar")
            );

            if ("1".equals(opcao)) {
                return TipoUsuario.LEITOR;
            }

            if ("2".equals(opcao)) {
                return TipoUsuario.BIBLIOTECARIO;
            }

            if ("0".equals(opcao)) {
                throw new IllegalArgumentException("Cadastro de usuário cancelado.");
            }

            this.console.exibirMensagem("Tipo de usuário inválido.");
            this.console.aguardarEnter();
        }
    }

    private LocalDate lerDataNascimento() {
        while (true) {
            String valor = this.console.lerTexto(TITULO, "Data de nascimento (dd/MM/yyyy) - opcional");

            if (valor.isBlank()) {
                return null;
            }

            try {
                return LocalDate.parse(valor, FORMATO_DATA);
            } catch (final DateTimeParseException e) {
                this.console.exibirMensagem("Data de nascimento inválida. Use o formato dd/MM/yyyy.");
                this.console.aguardarEnter();
            }
        }
    }
}