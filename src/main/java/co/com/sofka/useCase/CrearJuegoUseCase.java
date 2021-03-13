package co.com.sofka.useCase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.juego.Juego;
import co.com.sofka.domain.juego.command.CrearJuego;
import co.com.sofka.domain.juego.values.JuegoId;

public class CrearJuegoUseCase extends UseCase<RequestCommand<CrearJuego>, ResponseEvents> {


    @Override
    public void executeUseCase(RequestCommand<CrearJuego> response) {
        var command = response.getCommand();
        var juegoId = new JuegoId();

        if(command.getJugadores().size()<3){
            throw new BusinessException(juegoId.value(), "Debe jugar minimo 3 jugadores");
        }

        var juego = new Juego(new JuegoId(), command.getJugadores());
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
