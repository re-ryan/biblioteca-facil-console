package br.com.bibliotecafacil.console;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Profile("console")
public class LeitorConsole {

	private final Scanner scanner;
	private final LayoutConsole painelConsole;

	public LeitorConsole(final LayoutConsole painelConsole) {
		this.scanner = new Scanner(System.in);
		this.painelConsole = painelConsole;
	}

	public String lerOpcao(final List<String> linhasConteudo, final List<String> linhasRodape) {
		this.painelConsole.exibirMenu(linhasConteudo, linhasRodape);

		String valor = scanner.nextLine().trim();

		this.painelConsole.fecharCaixa();

		return valor;
	}

	public String lerTexto(final String titulo, final String campo) {
		this.painelConsole.exibirFormulario(titulo, campo);

		String valor = scanner.nextLine().trim();

		this.painelConsole.fecharCaixa();

		return valor;
	}

	public String lerTextoObrigatorio(final String titulo, final String campo) {
		while (true) {
			String valor = lerTexto(titulo, campo);

			if (!valor.isBlank()) {
				return valor;
			}

			this.painelConsole.exibirMensagem("O campo " + campo + " é obrigatório.");
			this.aguardarEnter();
		}
	}

	public void aguardarEnter() {
		this.painelConsole.exibirContinuacao();

		this.scanner.nextLine();

		this.painelConsole.fecharCaixa();
	}
}
