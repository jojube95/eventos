package com.example.eventos.grafico;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.tipoEvento.TipoEvento;
import com.example.eventos.tipoEvento.TipoEventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.example.eventos.config.Constants.*;

@Controller
public class GraficoController {
    private final EventoService eventoService;
    private final TipoEventoService tipoEventoService;

    public GraficoController(EventoService eventoService, TipoEventoService tipoEventoService) {
        this.eventoService = eventoService;
        this.tipoEventoService = tipoEventoService;
    }

    @GetMapping("/graficoBarras")
    public String graficoBarras(Model model) {
        List<Evento> eventos = eventoService.getEventosOrderByDate();
        List<TipoEvento> tiposEventos = tipoEventoService.getTipoEventos();

        model.addAttribute(EVENTOS, eventos);
        model.addAttribute(ATTRIBUTE_TIPOS_EVENTOS, tiposEventos);
        return GRAFICOS_BARRAS_PAGE;
    }

    @GetMapping("/graficoPastel")
    public String graficoPastel(Model model) {
        List<Evento> eventos = eventoService.getEventosOrderByDate();
        List<TipoEvento> tiposEventos = tipoEventoService.getTipoEventos();

        model.addAttribute(EVENTOS, eventos);
        model.addAttribute(ATTRIBUTE_TIPOS_EVENTOS, tiposEventos);
        return GRAFICOS_PASTEL_PAGE;
    }
}
