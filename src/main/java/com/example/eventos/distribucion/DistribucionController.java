package com.example.eventos.distribucion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.eventos.config.Constants.*;

@Controller
public class DistribucionController {

    @GetMapping("/evento/distribucion/tipoMesaModal")
    public String modalTipoMesa(@RequestParam(MESA_ID) String mesaId, @RequestParam(MESA_NUMERO) String numero,
                                @RequestParam(MESA_PERSONAS) String personas, @RequestParam(MESA_NINYOS) String ninyos, Model model){
        model.addAttribute(MESA_ID, mesaId);
        model.addAttribute(MESA_NUMERO, numero);
        model.addAttribute(MESA_PERSONAS, personas);
        model.addAttribute(MESA_NINYOS, ninyos);
        return "fragments/distribucionTipoMesaModal :: modalContents";
    }
}
