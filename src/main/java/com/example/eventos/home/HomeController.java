package com.example.eventos.home;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/home")
    public String home(Model model) throws JsonProcessingException {
        List<Evento> eventos = eventoService.getEventos();

        ObjectMapper Obj = new ObjectMapper();

        String eventosJson = Obj.writeValueAsString(eventos);

        model.addAttribute("eventosJson", eventosJson);

        return "home";
    }

}
