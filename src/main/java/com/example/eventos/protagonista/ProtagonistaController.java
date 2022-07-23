package com.example.eventos.protagonista;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProtagonistaController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/evento/protagonistas")
    public String updateFecha(@RequestParam("eventoId") String eventoId, Model model){
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute("evento", evento);
        return "verProtagonistas";
    }

    @GetMapping("/evento/protagonistas/eliminar")
    public String eliminarEvento(@RequestParam("eventoId") String eventoId, @RequestParam("protagonistaIndex") int protagonistaIndex, RedirectAttributes redirectAttributes) {
        Evento evento = eventoService.getById(eventoId);
        evento.getProtagonistas().remove(protagonistaIndex);
        eventoService.update(evento);
        redirectAttributes.addAttribute("eventoId", evento.getId());
        return "redirect:/evento/protagonistas";
    }

    @GetMapping("/evento/protagonistas/anyadir")
    public String anyadirEvento(@RequestParam("eventoId") String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute("evento", evento);
        model.addAttribute("protagonista", new Protagonista());
        return "anyadirProtagonista";
    }

    @PostMapping("/evento/protagonistas/anyadir")
    public String save(@ModelAttribute Protagonista protagonista, @RequestParam("eventoId") String eventoId, RedirectAttributes redirectAttributes, Model model) {
        Evento evento = eventoService.getById(eventoId);
        List<Protagonista> protagonistas = evento.getProtagonistas();
        protagonistas.add(protagonista);
        evento.setProtagonistas(protagonistas);
        eventoService.update(evento);
        redirectAttributes.addAttribute("eventoId", evento.getId());
        return "redirect:/evento/protagonistas";
    }
}
