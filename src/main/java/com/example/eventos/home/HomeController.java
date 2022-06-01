package com.example.eventos.home;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoRepository;
import com.example.eventos.evento.EventoService;
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
    public String home(Model model) {
        List<Evento> eventos = eventoService.findEventosByTipo("Boda");
        Evento evento = eventos.get(0);

        model.addAttribute("evento", evento);

        return "home";
    }

}
