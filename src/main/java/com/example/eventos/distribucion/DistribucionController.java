package com.example.eventos.distribucion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DistribucionController {

    public DistribucionController() {

    }

    @GetMapping("/evento/distribucion/tipoMesaModal")
    public String modalTipoMesa(@RequestParam("mesaId") String mesaId, @RequestParam("numero") String numero, @RequestParam("personas") String personas, Model model){
        model.addAttribute("mesaId", mesaId);
        model.addAttribute("numero", numero);
        model.addAttribute("personas", personas);
        return "fragments/distribucionTipoMesaModal :: modalContents";
    }
}
