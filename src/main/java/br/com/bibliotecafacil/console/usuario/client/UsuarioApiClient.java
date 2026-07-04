package br.com.bibliotecafacil.console.usuario.client;

import br.com.bibliotecafacil.console.api.dto.ErroApiDto;
import br.com.bibliotecafacil.console.usuario.dto.CadastroUsuarioDto;
import br.com.bibliotecafacil.console.usuario.dto.ConsultaUsuarioDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Component
public class UsuarioApiClient {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public UsuarioApiClient(final RestClient restClient, final ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    public List<ConsultaUsuarioDto> listar() {
        try {
            return restClient.get()
                    .uri("/api/usuarios")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        } catch (final RestClientResponseException exception) {
            throw new IllegalArgumentException(extrairMensagemErro(exception));
        }
    }

    public void cadastrar(final CadastroUsuarioDto cadastro) {
        try {
            restClient.post()
                    .uri("/api/usuarios")
                    .body(cadastro)
                    .retrieve()
                    .toBodilessEntity();
        } catch (final RestClientResponseException exception) {
            throw new IllegalArgumentException(extrairMensagemErro(exception));
        }
    }

    private String extrairMensagemErro(final RestClientResponseException exception) {
        try {
            ErroApiDto erro = objectMapper.readValue(exception.getResponseBodyAsString(), ErroApiDto.class);

            if (erro.getMensagem() != null && !erro.getMensagem().isBlank()) {
                return erro.getMensagem();
            }
        } catch (final Exception ignored) {
        }

        return "Erro ao comunicar com a API do Biblioteca Fácil.";
    }
}