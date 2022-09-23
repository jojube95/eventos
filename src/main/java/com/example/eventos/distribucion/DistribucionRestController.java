package com.example.eventos.distribucion;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.example.eventos.config.Constants.EVENTO_ID;

@RestController
public class DistribucionRestController {

    private final EventoService eventoService;

    public DistribucionRestController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/evento/distribucion/guardar")
    public Evento save(@RequestParam(EVENTO_ID) String eventoId, @RequestBody String distribucion) {
        Distribucion distribucionObject = new Distribucion(distribucion);
        Evento evento = eventoService.getById(eventoId);
        evento.setDistribucion(distribucionObject);
        eventoService.update(evento);
        return evento;
    }
}
