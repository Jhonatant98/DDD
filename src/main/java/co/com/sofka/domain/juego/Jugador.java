package co.com.sofka.domain.juego;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.juego.values.Saldo;

public class Jugador extends Entity<JugadorId> {

    protected final static Integer VALUE_SCORE_DEFAULT = 0;
    protected String alias;
    protected Integer score;
    protected Saldo saldo;

    public Jugador(JugadorId entityId, String alias, Saldo saldo) {
        super(entityId);
        this.alias = alias;
        this.saldo = saldo;
        this.score = VALUE_SCORE_DEFAULT;
    }

}
