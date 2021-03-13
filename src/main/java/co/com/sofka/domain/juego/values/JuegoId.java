package co.com.sofka.domain.juego.values;

import co.com.sofka.domain.generic.Identity;

public class JuegoId extends Identity {

    public JuegoId(String value) {
        super(value);
    }

    public JuegoId(){}

    public static JuegoId of(String value){ return new JuegoId(value);}
}
