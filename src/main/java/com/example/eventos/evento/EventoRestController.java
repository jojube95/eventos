package com.example.eventos.evento;

import com.example.eventos.personas.Personas;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
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

    @GetMapping("/api/evento")
    public Evento getEvento(@RequestParam("id") String eventoId) {
        return eventoService.getById(eventoId);
    }

    @GetMapping("/api/evento/years")
    public List<Object> getEventoYears() {
        AggregationResults<org.bson.Document> aggregationResults = eventoService.getEventoYears();

        return aggregationResults.getMappedResults().stream().map( n -> n.get("_id")).collect(Collectors.toList());
    }
}
