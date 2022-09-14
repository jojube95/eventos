package com.example.eventos.mesa;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
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
    public Mesa add(@RequestBody Mesa mesa, @RequestParam("idEvento") String idEvento){
        Evento evento = eventoService.getById(idEvento);

        mesaService.save(mesa);

        if (!evento.getTipo().equals("Evento individual")) {
            for (int i = 1; i <= mesa.getPersonas(); i++) {
                invitadoService.save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado" + i, "Mayor", ""));
            }
            for (int i = 1; i <= mesa.getNinyos(); i++) {
                invitadoService.save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Niño" + i, "Ninyo", ""));
            }
        }
        return mesa;
    }

    @PostMapping("/evento/mesas/delete")
    public Mesa delete(@RequestBody Mesa mesa) {
        mesaService.delete(mesa);
        invitadoService.deleteInvitados(mesa.getId());
        return mesa;
    }

    @PostMapping("/evento/mesas/update")
    public Mesa update(@RequestBody Mesa mesa) {
        mesaService.save(mesa);
        return mesa;
    }

}
