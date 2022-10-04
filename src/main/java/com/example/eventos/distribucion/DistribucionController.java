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
                                @RequestParam(PERSONAS_MAYORES) String mayores, @RequestParam(PERSONAS_NINYOS) String ninyos, Model model){
        model.addAttribute(MESA_ID, mesaId);
        model.addAttribute(MESA_NUMERO, numero);
        model.addAttribute(PERSONAS_MAYORES, mayores);
        model.addAttribute(PERSONAS_NINYOS, ninyos);
        return "fragments/distribucionTipoMesaModal :: modalContents";
    }
}
