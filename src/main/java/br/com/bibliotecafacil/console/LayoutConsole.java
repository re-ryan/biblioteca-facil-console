package br.com.bibliotecafacil.console;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("console")
public class LayoutConsole {

	private static final int LARGURA_CONTEUDO = 100;
	private static final String NOME_APLICACAO = "Biblioteca Fácil";
	private static final String VERSAO = "V1 Console";
	private static final String PROMPT = "biblioteca-facil:v1 > ";

	public void exibirMenu(final List<String> linhasConteudo, final List<String> linhasRodape) {
		this.abrirCaixa();

		this.imprimirLinhas(linhasConteudo);
		this.imprimirSeparador();
		this.imprimirLinhas(linhasRodape);
		this.imprimirSeparador();
		this.imprimirLinha("Selecione uma opção");
		this.imprimirPrompt(PROMPT);
	}

	public void exibirFormulario(final String titulo, final String campo) {
		this.abrirCaixa();

		this.imprimirLinha(titulo);
		this.imprimirLinha("");
		this.imprimirLinha(campo);
		this.imprimirPrompt(PROMPT);
	}

	public void exibirContinuacao() {
		this.abrirCaixa();

		this.imprimirLinha("Pressione ENTER para continuar.");
		this.imprimirPrompt(PROMPT);
	}

	public void exibirMensagem(final String mensagem) {
		this.abrirCaixa();

		for (String linha : quebrarTexto(mensagem)) {
			this.imprimirLinha(linha);
		}

		this.imprimirRodape();
		System.out.println();
	}

	public void fecharCaixa() {
		System.out.println();
		this.imprimirRodape();
		System.out.println();
	}

	private void abrirCaixa() {
		this.imprimirTopo();
		this.imprimirCabecalho();
		this.imprimirSeparador();
	}

	private void imprimirLinhas(final List<String> linhas) {
		for (String linha : linhas) {
			this.imprimirLinha(linha);
		}
	}

	private void imprimirCabecalho() {
		int espacosEntreTextos = LARGURA_CONTEUDO - NOME_APLICACAO.length() - VERSAO.length();

		String linha = NOME_APLICACAO + repetir(" ", espacosEntreTextos) + VERSAO;

		this.imprimirLinha(linha);
	}

	private void imprimirLinha(final String texto) {
		System.out.println("║ " + this.preencherDireita(texto) + " ║");
	}

	private void imprimirPrompt(final String texto) {
		System.out.print("║ " + texto);
	}

	private void imprimirTopo() {
		System.out.println("╔" + this.repetir("═", LARGURA_CONTEUDO + 2) + "╗");
	}

	private void imprimirSeparador() {
		System.out.println("╠" + this.repetir("═", LARGURA_CONTEUDO + 2) + "╣");
	}

	private void imprimirRodape() {
		System.out.println("╚" + this.repetir("═", LARGURA_CONTEUDO + 2) + "╝");
	}

	private String preencherDireita(final String texto) {
		String valor = this.limitar(texto);
		return valor + this.repetir(" ", LARGURA_CONTEUDO - valor.length());
	}

	private String limitar(final String texto) {
		if (texto == null) {
			return "";
		}

		if (texto.length() <= LARGURA_CONTEUDO) {
			return texto;
		}

		return texto.substring(0, LARGURA_CONTEUDO);
	}

	private List<String> quebrarTexto(final String texto) {
		List<String> linhas = new ArrayList<>();

		if (texto == null || texto.isBlank()) {
			linhas.add("");
			return linhas;
		}

		String restante = texto.trim();

		while (restante.length() > LARGURA_CONTEUDO) {
			int posicaoCorte = restante.lastIndexOf(" ", LARGURA_CONTEUDO);

			if (posicaoCorte <= 0) {
				posicaoCorte = LARGURA_CONTEUDO;
			}

			linhas.add(restante.substring(0, posicaoCorte).trim());
			restante = restante.substring(posicaoCorte).trim();
		}

		if (!restante.isBlank()) {
			linhas.add(restante);
		}

		return linhas;
	}

	private String repetir(final String texto, final int quantidade) {
		return texto.repeat(Math.max(0, quantidade));
	}
}
