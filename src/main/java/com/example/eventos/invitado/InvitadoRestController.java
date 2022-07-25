package com.example.eventos.invitado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvitadoRestController {
    @Autowired
    private InvitadoService invitadoService;

    @PostMapping("/evento/mesas/invitados/add")
    public Invitado add(@RequestBody Invitado invitado, Model model){
        invitadoService.save(invitado);
        return invitado;
    }

    @PostMapping("/evento/mesas/invitados/delete")
    public Invitado delete(@RequestBody Invitado invitado, Model model){
        invitadoService.delete(invitado);
        return invitado;
    }

    @PostMapping("/evento/mesas/invitados/update")
    public Invitado update(@RequestBody Invitado invitado, Model model){
        invitadoService.save(invitado);
        return invitado;
    }
}
