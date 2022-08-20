package com.example.eventos.evento;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EventoRestController {

    private final EventoService eventoService;

    public EventoRestController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/evento/updatePersonas")
    public Evento update(@RequestParam("eventoId") String eventoId, @RequestParam("personas") int personas) throws IOException {
        Evento evento = eventoService.getById(eventoId);
        evento.setPersonas(personas);
        eventoService.update(evento);
        return evento;
    }
}
