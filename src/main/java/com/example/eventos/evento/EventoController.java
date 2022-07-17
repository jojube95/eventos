package com.example.eventos.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/eliminarEvento")
    public String eliminarEvento(@RequestParam("eventoId") String eventoId) {
        Evento evento = eventoService.getById(eventoId);

        eventoService.delete(evento);

        return "redirect:/verEventos";
    }

    @GetMapping("/evento/{id}")
    public String evento(@PathVariable("id") String id, Model model){
        Evento evento = eventoService.getById(id);
        model.addAttribute(evento);
        return "fragments/eventoModal :: modalContents";
    }
}
