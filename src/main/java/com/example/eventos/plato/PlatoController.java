package com.example.eventos.plato;

import com.example.eventos.tipoPlato.TipoPlatoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static com.example.eventos.config.Constants.*;

@Controller
public class PlatoController {
    private final PlatoService platoService;
    private final TipoPlatoService tipoPlatoService;

    public PlatoController(PlatoService platoService, TipoPlatoService tipoPlatoService) {
        this.platoService = platoService;
        this.tipoPlatoService = tipoPlatoService;
    }

    @GetMapping("/platos")
    public String platos(Model model) {
        return PLATOS_PAGE;
    }

    @GetMapping("/platosAnyadir")
    public String add(Model model) {
        model.addAttribute(PLATO, new Plato());
        model.addAttribute(ATTRIBUTE_TIPOS_PLATO, tipoPlatoService.getTipoPlatos());
        return PLATO_ANYADIR_PAGE;
    }

    @PostMapping("/platoAnyadirUpdate")
    public String save(@ModelAttribute Plato plato) {
        return "redirect:/" + PLATOS_PAGE;
    }

    @GetMapping("/platoUpdate")
    public String update(@RequestParam(PLATO_ID) String platoId, Model model) {
        return PLATO_UPDATE_PAGE;
    }
}
