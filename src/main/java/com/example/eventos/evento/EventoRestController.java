package com.example.eventos.evento;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.example.eventos.config.Constants.*;

@RestController
public class EventoRestController {

    private final EventoService eventoService;

    public EventoRestController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/evento/updatePersonas")
    public Evento update(@RequestParam(EVENTO_ID) String eventoId, @RequestParam(EVENTO_PERSONAS) int personas, @RequestParam(EVENTO_NINYOS) int ninyos) {
        Evento evento = eventoService.getById(eventoId);
        evento.setPersonas(personas);
        evento.setNinyos(ninyos);
        eventoService.update(evento);
        return evento;
    }
}
