package com.example.eventos.grafico;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.example.eventos.config.Constants.EVENTOS;
import static com.example.eventos.config.Constants.GRAFICOS_BARRAS_PAGE;

@Controller
public class GraficoController {
    private final EventoService eventoService;

    public GraficoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/graficoBarras")
    public String graficoBarras(Model model) {
        List<Evento> eventos = eventoService.getEventosOrderByDate();
        model.addAttribute(EVENTOS, eventos);
        return GRAFICOS_BARRAS_PAGE;
    }
}
