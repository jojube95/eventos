package com.example.eventos.protagonista;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.List;
import static com.example.eventos.config.Constants.*;

@Controller
public class ProtagonistaController {

    private final EventoService eventoService;

    public ProtagonistaController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/evento/protagonistas")
    public String updateFecha(@RequestParam(EVENTO_ID) String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(EVENTO, evento);
        return PROTAGONISTAS_VER_PAGE;
    }

    @GetMapping("/evento/protagonistas/eliminar")
    public String eliminarEvento(@RequestParam(EVENTO_ID) String eventoId, @RequestParam("protagonistaIndex") int protagonistaIndex,
                                 RedirectAttributes redirectAttributes) {
        Evento evento = eventoService.getById(eventoId);
        evento.getProtagonistas().remove(protagonistaIndex);
        eventoService.update(evento);
        redirectAttributes.addAttribute(EVENTO_ID, evento.getId());
        return "redirect:/evento/protagonistas";
    }

    @GetMapping("/evento/protagonistas/anyadir")
    public String eventoAnyadir(@RequestParam(EVENTO_ID) String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(EVENTO, evento);
        model.addAttribute(PROTAGONISTA, new Protagonista());
        return PROTAGONISTA_ANYADIR_PAGE;
    }

    @PostMapping("/evento/protagonistas/anyadir")
    public String save(@ModelAttribute Protagonista protagonista, @RequestParam(EVENTO_ID) String eventoId,
                       RedirectAttributes redirectAttributes) throws IOException {
        Evento evento = eventoService.getById(eventoId);
        List<Protagonista> protagonistas = evento.getProtagonistas();
        protagonistas.add(protagonista);
        evento.setProtagonistas(protagonistas);
        eventoService.update(evento);
        redirectAttributes.addAttribute(EVENTO_ID, evento.getId());
        return "redirect:/evento/protagonistas";
    }
}
