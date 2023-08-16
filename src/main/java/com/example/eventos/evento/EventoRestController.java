package com.example.eventos.evento;

import com.example.eventos.personas.Personas;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.eventos.config.Constants.*;

@RestController
public class EventoRestController {

    private final EventoService eventoService;

    public EventoRestController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/evento/updatePersonas")
    public Evento update(@RequestBody Personas personas, @RequestParam(EVENTO_ID) String eventoId) {
        Evento evento = eventoService.getById(eventoId);
        evento.setPersonas(personas);
        eventoService.update(evento);
        return evento;
    }

    @GetMapping("/api/eventos")
    public List<Evento> getEventos(@RequestParam("year") int year){
        return eventoService.getEventosByYear(year);
    }
}
