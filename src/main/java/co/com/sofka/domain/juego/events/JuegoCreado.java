package co.com.sofka.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.Jugador;
import co.com.sofka.domain.juego.values.JugadorId;

import java.util.Map;

public class JuegoCreado extends DomainEvent {

    private final Map<JugadorId, Jugador> jugadores;

    public JuegoCreado(Map<JugadorId, Jugador> jugadores) {
        super("nomemientas.juego.creado");
        this.jugadores = jugadores;
    }

    public Map<JugadorId, Jugador> getJugadores() { return jugadores; }
}
