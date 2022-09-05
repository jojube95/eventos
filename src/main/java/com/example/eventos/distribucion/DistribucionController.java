package com.example.eventos.distribucion;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.mesa.Mesa;
import com.example.eventos.mesa.MesaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class DistribucionController {
    private final MesaService mesaService;

    private final EventoService eventoService;

    public DistribucionController(MesaService mesaService, EventoService eventoService) {
        this.mesaService = mesaService;
        this.eventoService = eventoService;
    }

    @GetMapping("/evento/distribucion")
    public String distribucion(@RequestParam("eventoId") String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        List<Mesa> mesas = mesaService.findByEvento(eventoId);
        model.addAttribute("idEvento", eventoId);
        model.addAttribute("mesas", mesas);
        model.addAttribute("distribucion", evento.getDistribucion().getDistribucion());
        return "distribucion";
    }

    @GetMapping("/evento/distribucion/tipoMesaModal")
    public String modalTipoMesa(@RequestParam("mesaId") String mesaId, @RequestParam("numero") String numero, @RequestParam("personas") String personas, Model model){
        model.addAttribute("mesaId", mesaId);
        model.addAttribute("numero", numero);
        model.addAttribute("personas", personas);
        return "fragments/distribucionTipoMesaModal :: modalContents";
    }
}
