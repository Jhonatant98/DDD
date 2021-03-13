package co.com.sofka.domain.juego.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Saldo implements ValueObject<Double> {

    private final Double value;

    public Saldo(Double value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Double value() {
        return value;
    }
}
