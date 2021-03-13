package co.com.sofka.useCase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.Jugador;
import co.com.sofka.domain.juego.command.IniciarJuego;
import co.com.sofka.domain.juego.events.JuegoCreado;
import co.com.sofka.domain.juego.events.JuegoIniciado;
import co.com.sofka.domain.juego.values.JuegoId;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.juego.values.Saldo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IniciarJuegoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void iniciarJuego(){
        var juegoId = JuegoId.of("ffff");
        var command = new IniciarJuego(juegoId);

        when(repository.getEventsBy(juegoId.value())).thenReturn(eventStored());

        var iniciarJuegoUseCase = new IniciarJuegoUseCase();
        iniciarJuegoUseCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(juegoId.value())
                .syncExecutor(iniciarJuegoUseCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (JuegoIniciado)events.get(0);
        Assertions.assertEquals(juegoId.value(), event.aggregateRootId());
    }

    @Test
    void juegoYaIniciado(){
        var juegoId = JuegoId.of("ffff");
        var command = new IniciarJuego(juegoId);
        var events = new ArrayList<>(eventStored());

        events.add(new JuegoIniciado());

        when(repository.getEventsBy(juegoId.value())).thenReturn(events);

        var iniciarJuegoUseCase = new IniciarJuegoUseCase();
        iniciarJuegoUseCase.addRepository(repository);

        Assertions.assertThrows(BusinessException.class,() ->
                UseCaseHandler.getInstance()
                        .setIdentifyExecutor(juegoId.value())
                        .syncExecutor(iniciarJuegoUseCase, new RequestCommand<>(command))
                        .orElseThrow(), "El juego ya esta inicializado"
        );
    }

    private List<DomainEvent> eventStored() {
        return List.of(new JuegoCreado(Map.of(
                JugadorId.of("xxxx"), new Jugador(JugadorId.of("xxxx"), "Raul Alzate", new Saldo(1200d)),
                JugadorId.of("ffff"), new Jugador(JugadorId.of("ffff"), "Andres", new Saldo(1500d)),
                JugadorId.of("tttt"), new Jugador(JugadorId.of("tttt"), "Camilo", new Saldo(3200d))
        )));
    }
}