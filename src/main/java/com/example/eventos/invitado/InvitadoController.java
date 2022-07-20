package com.example.eventos.invitado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InvitadoController {
    @Autowired
    private InvitadoService invitadoService;

    @GetMapping("/evento/mesas/invitados")
    public String evento(@RequestParam("idMesa") String idMesa, Model model){
        List<Invitado> invitados = invitadoService.findByMesa(idMesa);
        model.addAttribute("invitados", invitados);
        model.addAttribute("idMesa", idMesa);
        return "fragments/invitadosModal :: modalContents";
    }
}
