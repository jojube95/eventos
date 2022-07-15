package com.example.eventos.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/verEventos")
    public String verEventos(Model model) {
        List<Evento> eventos = eventoService.getEventos();
        model.addAttribute("eventos", eventos);
        return "verEventos";
    }

    @GetMapping("/anyadirEvento")
    public String anyadirEvento(Model model) {
        model.addAttribute("evento", new Evento());
        return "anyadirEvento";
    }

    @GetMapping("/updateEvento")
    public String updateEvento(@RequestParam("eventoId") String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute("evento", evento);
        return "updateEvento";
    }

    @PostMapping("/updateEvento")
    public String updateEvento(@ModelAttribute Evento evento) {
        eventoService.save(evento);

        return "redirect:/verEventos";
    }

    @GetMapping("/verEvento")
    public String verEvento(@RequestParam("eventoId") String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute("evento", evento);
        return "verEvento";
    }

    @PostMapping("/anyadirEvento")
    public String save(@ModelAttribute Evento evento) {
        eventoService.save(evento);

        return "redirect:/home";
    }
}
