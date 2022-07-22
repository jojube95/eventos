package com.example.eventos.mesa;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MesaRestController {
    @Autowired
    private MesaService mesaService;

    @Autowired
    private InvitadoService invitadoService;

    @PostMapping("/evento/mesas/add")
    public Mesa add(@RequestBody Mesa mesa, Model model){
        mesaService.save(mesa);
        for (int i = 1; i <= mesa.getPersonas(); i++) {
            invitadoService.save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado" + i, ""));
        }
        return mesa;
    }

    @PostMapping("/evento/mesas/delete")
    public Mesa delete(@RequestBody Mesa mesa, Model model){
        mesaService.delete(mesa);
        invitadoService.deleteInvitados(mesa.getId());
        return mesa;
    }

    @PostMapping("/evento/mesas/update")
    public Mesa update(@RequestBody Mesa mesa, Model model){
        mesaService.save(mesa);
        return mesa;
    }

}
