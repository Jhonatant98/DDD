package co.com.sofka.useCase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.juego.Juego;
import co.com.sofka.domain.juego.command.IniciarJuego;

public class IniciarJuegoUseCase extends UseCase<RequestCommand<IniciarJuego>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<IniciarJuego> response) {
        var command = response.getCommand();
        var juego = Juego.from(command.getJuegoId(), retrieveEvents());
        try {
            juego.iniciarJuego();
            emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
        }catch (RuntimeException e){
            emit().onError(new BusinessException(juego.identity().value(), e.getMessage()));
        }
    }
}
