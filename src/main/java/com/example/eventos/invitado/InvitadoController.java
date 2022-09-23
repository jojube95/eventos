package com.example.eventos.invitado;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import static com.example.eventos.config.Constants.*;

@Controller
public class InvitadoController {
    private final InvitadoService invitadoService;

    public InvitadoController(InvitadoService invitadoService) {
        this.invitadoService = invitadoService;
    }

    @GetMapping("/evento/mesas/invitados")
    public String evento(@RequestParam(EVENTO_ID) String eventoId, @RequestParam(MESA_ID) String mesaId, Model model){
        List<Invitado> invitados = invitadoService.findByMesa(mesaId);
        model.addAttribute(INVITADOS, invitados);
        model.addAttribute(EVENTO_ID, eventoId);
        model.addAttribute(MESA_ID, mesaId);
        return "fragments/invitadosModal :: modalContents";
    }
}
