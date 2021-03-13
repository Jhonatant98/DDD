package co.com.sofka.domain.juego.command;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.juego.Jugador;

import java.util.Set;

public class CrearJuego implements Command {

    private final Set<Jugador> juagadores;

    public CrearJuego(Set<Jugador> juagadores) {
        this.juagadores = juagadores;
    }

    public Set<Jugador> getJugadores() {
        return juagadores;
    }
}
