package com.example.eventos.mesa;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MesaRestController {
    private final MesaService mesaService;

    private final InvitadoService invitadoService;

    private final EventoService eventoService;

    public MesaRestController(MesaService mesaService, InvitadoService invitadoService, EventoService eventoService) {
        this.mesaService = mesaService;
        this.invitadoService = invitadoService;
        this.eventoService = eventoService;
    }

    @PostMapping("/evento/mesas/add")
    public Mesa add(@RequestBody Mesa mesa){
        mesaService.save(mesa);
        for (int i = 1; i <= mesa.getPersonas(); i++) {
            invitadoService.save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado" + i, ""));
        }
        return mesa;
    }

    @PostMapping("/evento/mesas/delete")
    public Mesa delete(@RequestBody Mesa mesa) throws JSONException {
        Evento evento = eventoService.getById(mesa.getIdEvento());
        mesaService.delete(mesa);
        invitadoService.deleteInvitados(mesa.getId());
        evento.getDistribucion().deleteMesa(mesa);
        eventoService.update(evento);
        return mesa;
    }

    @PostMapping("/evento/mesas/update")
    public Mesa update(@RequestBody Mesa mesa) throws JSONException {
        Evento evento = eventoService.getById(mesa.getIdEvento());
        mesaService.save(mesa);
        evento.getDistribucion().updateMesa(mesa);
        eventoService.update(evento);
        return mesa;
    }

}
