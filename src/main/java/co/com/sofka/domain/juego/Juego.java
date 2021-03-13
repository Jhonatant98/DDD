package co.com.sofka.domain.juego;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.events.JuegoCreado;
import co.com.sofka.domain.juego.events.JuegoIniciado;
import co.com.sofka.domain.juego.values.JuegoId;
import co.com.sofka.domain.juego.values.JugadorId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Juego extends AggregateEvent<JuegoId> {

    protected Map<JugadorId, Jugador> jugadores;

    public Juego(JuegoId entityId, Set<Jugador> jugadores) {
        super(entityId);
        Map<JugadorId, Jugador> newJugadores = new HashMap<>();
        jugadores.forEach(jugador -> newJugadores.put(jugador.identity(), jugador));
        appendChange(new JuegoCreado(newJugadores)).apply();
    }

    private Juego(JuegoId entityId){
        super(entityId);
    }

    public static Juego from(JuegoId juegoId, List<DomainEvent> events){
        var juego =  new Juego(juegoId);
        events.forEach(juego::applyEvent);
        return juego;
    }

    public void iniciarJuego(){
        appendChange(new JuegoIniciado()).apply();
    }
}
