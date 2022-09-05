package com.example.eventos.mesa;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class MesaController {
    private final MesaService mesaService;

    private final EventoService eventoService;

    public MesaController(MesaService mesaService, EventoService eventoService) {
        this.mesaService = mesaService;
        this.eventoService = eventoService;
    }

    @GetMapping("/evento/mesas")
    public String getMesas(@RequestParam("eventoId") String eventoId, Model model) {
        List<Mesa> mesas = mesaService.findByEvento(eventoId);
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute("idEvento", eventoId);
        model.addAttribute("isEventoIndividual", evento.isEventoIndividual());
        model.addAttribute("distribucion", evento.getDistribucion().getDistribucion());
        model.addAttribute("mesas", mesas);
        return "mesas";
    }
}
