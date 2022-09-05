package com.example.eventos.mesa;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MesaRestController {
    private final MesaService mesaService;

    private final InvitadoService invitadoService;

    public MesaRestController(MesaService mesaService, InvitadoService invitadoService) {
        this.mesaService = mesaService;
        this.invitadoService = invitadoService;
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
        mesaService.delete(mesa);
        invitadoService.deleteInvitados(mesa.getId());
        return mesa;
    }

    @PostMapping("/evento/mesas/update")
    public Mesa update(@RequestBody Mesa mesa) throws JSONException {
        mesaService.save(mesa);
        return mesa;
    }

}
