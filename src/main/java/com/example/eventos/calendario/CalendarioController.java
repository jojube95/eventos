package com.example.eventos.calendario;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import static com.example.eventos.config.Constants.CALENDARIO_PAGE;
import static com.example.eventos.json.JsonUtils.toJson;

@Controller
public class CalendarioController {

    private final EventoService eventoService;

    public CalendarioController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/")
    public String redirectCalendario() {
        return "redirect:/" + CALENDARIO_PAGE;
    }

    @GetMapping("/calendario")
    public String calendario(Model model) throws JsonProcessingException {
        List<Evento> eventos = eventoService.getEventos();

        model.addAttribute("eventosJson", toJson(eventos));

        return CALENDARIO_PAGE;
    }

}
