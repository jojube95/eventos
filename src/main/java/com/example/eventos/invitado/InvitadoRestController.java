package com.example.eventos.invitado;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvitadoRestController {
    private final InvitadoService invitadoService;

    public InvitadoRestController(InvitadoService invitadoService) {
        this.invitadoService = invitadoService;
    }

    @PostMapping("/evento/mesas/invitados/add")
    public Invitado add(@RequestBody Invitado invitado){
        invitadoService.save(invitado);
        return invitado;
    }

    @PostMapping("/evento/mesas/invitados/delete")
    public Invitado delete(@RequestBody Invitado invitado){
        invitadoService.delete(invitado);
        return invitado;
    }

    @PostMapping("/evento/mesas/invitados/update")
    public Invitado update(@RequestBody Invitado invitado){
        invitadoService.save(invitado);
        return invitado;
    }
}
