package com.example.eventos.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventoRestController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/evento/updatePersonas")
    public Evento update(@RequestParam("eventoId") String eventoId, @RequestParam("personas") int personas, Model model){
        Evento evento = eventoService.getById(eventoId);
        evento.setPersonas(personas);
        eventoService.update(evento);
        return evento;
    }
}
