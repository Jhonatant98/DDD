package co.com.sofka.domain.juego.command;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.juego.values.JuegoId;

public class IniciarJuego implements Command {

    private final JuegoId juegoId;

    public IniciarJuego(JuegoId juegoId) {
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
