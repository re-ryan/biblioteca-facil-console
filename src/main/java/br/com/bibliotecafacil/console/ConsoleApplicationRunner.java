package br.com.bibliotecafacil.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.bibliotecafacil.console.menu.HomeMenu;

@Component
public class ConsoleApplicationRunner implements CommandLineRunner {

    private final HomeMenu principalMenu;

    public ConsoleApplicationRunner(final HomeMenu principalMenu) {
        this.principalMenu = principalMenu;
    }

    @Override
    public void run(String... args) {
        this.principalMenu.exibir();
    }
}
