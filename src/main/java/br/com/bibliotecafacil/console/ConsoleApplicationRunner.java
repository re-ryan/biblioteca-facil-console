package br.com.bibliotecafacil.console;

import br.com.bibliotecafacil.console.menu.HomeMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("console")
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
