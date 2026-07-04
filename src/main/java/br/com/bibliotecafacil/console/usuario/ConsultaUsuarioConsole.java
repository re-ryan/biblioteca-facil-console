package br.com.bibliotecafacil.console.usuario;

import br.com.bibliotecafacil.console.Console;
import br.com.bibliotecafacil.console.usuario.client.UsuarioApiClient;
import br.com.bibliotecafacil.console.usuario.dto.ConsultaUsuarioDto;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Profile("console")
public class ConsultaUsuarioConsole {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Console console;
    private final UsuarioApiClient usuarioApiClient;

    public ConsultaUsuarioConsole(final Console console, final UsuarioApiClient usuarioApiClient) {
        this.console = console;
        this.usuarioApiClient = usuarioApiClient;
    }

    public void listar() {
        try {
            List<ConsultaUsuarioDto> usuarios = usuarioApiClient.listar();

            if (usuarios.isEmpty()) {
                this.console.exibirMensagem("Nenhum usuário cadastrado.");
                this.console.aguardarEnter();
                return;
            }

            this.console.exibirMensagem(montarMensagem(usuarios));
        } catch (final IllegalArgumentException e) {
            this.console.exibirMensagem(e.getMessage());
        }

        this.console.aguardarEnter();
    }

    private String montarMensagem(final List<ConsultaUsuarioDto> usuarios) {
        StringBuilder mensagem = new StringBuilder("Usuários cadastrados: ");

        for (final ConsultaUsuarioDto usuario : usuarios) {
            mensagem.append(this.formatarUsuario(usuario));
        }

        return mensagem.toString();
    }

    private String formatarUsuario(final ConsultaUsuarioDto usuario) {
        return "[" +
                "Código: " + usuario.getId() +
                " | Nome: " + usuario.getNomeCompleto() +
                " | Login: " + usuario.getLogin() +
                " | E-mail: " + usuario.getEmail() +
                " | Tipo: " + usuario.getTipoUsuario() +
                " | Nascimento: " + formatarDataNascimento(usuario) +
                " | Ativo: " + formatarAtivo(usuario) +
                "] ";
    }

    private String formatarDataNascimento(final ConsultaUsuarioDto usuario) {
        if (usuario.getDataNascimento() == null) {
            return "Não informado";
        }

        return usuario.getDataNascimento().format(FORMATO_DATA);
    }

    private String formatarAtivo(final ConsultaUsuarioDto usuario) {
        if (Boolean.TRUE.equals(usuario.getAtivo())) {
            return "Sim";
        }

        return "Não";
    }
}