package br.com.bibliotecafacil.console.biblioteca.client;

import br.com.bibliotecafacil.console.api.dto.ErroApiDto;
import br.com.bibliotecafacil.console.biblioteca.dto.CadastroBibliotecaDto;
import br.com.bibliotecafacil.console.biblioteca.dto.ConsultaBibliotecaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Component
public class BibliotecaApiClient {

    private static final String MENSAGEM_API_INDISPONIVEL = "Não foi possível conectar à API do Biblioteca Fácil. Verifique se o backend está em execução.";

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public BibliotecaApiClient(final RestClient restClient, final ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    public List<ConsultaBibliotecaDto> listar() {
        try {
            return restClient.get()
                    .uri("/api/bibliotecas")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        } catch (final RestClientResponseException exception) {
            throw new IllegalArgumentException(extrairMensagemErro(exception));
        } catch (final ResourceAccessException exception) {
            throw new IllegalArgumentException(MENSAGEM_API_INDISPONIVEL);
        }
    }

    public void cadastrar(final CadastroBibliotecaDto cadastro) {
        try {
            restClient.post()
                    .uri("/api/bibliotecas")
                    .body(cadastro)
                    .retrieve()
                    .toBodilessEntity();
        } catch (final RestClientResponseException exception) {
            throw new IllegalArgumentException(extrairMensagemErro(exception));
        } catch (final ResourceAccessException exception) {
            throw new IllegalArgumentException(MENSAGEM_API_INDISPONIVEL);
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