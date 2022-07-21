package com.example.eventos.evento;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import com.example.eventos.mesa.Mesa;
import com.example.eventos.mesa.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Controller
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private InvitadoService invitadoService;

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
        eventoService.update(evento);
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
        return "redirect:/calendario";
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

    @GetMapping("/evento/updateFecha")
    public String updateFecha(@RequestParam("id") String id, @RequestParam("fecha") Date fecha, Model model){
        Evento evento = eventoService.getById(id);
        evento.setFecha(fecha);
        eventoService.update(evento);
        return "redirect:/calendario";
    }

    @GetMapping("/evento/calcularPersonas")
    public String calcularPersonas(@RequestParam("eventoId") String eventoId, Model model){
        List<Mesa> mesas = mesaService.findByEvento(eventoId);
        int personas = 0;
        for (Mesa mesa : mesas) {
            List<Invitado> invitados = invitadoService.findByMesa(mesa.getId());
            personas += invitados.size();
        }
        model.addAttribute("eventoId", eventoId);
        model.addAttribute("personas", personas);
        return "fragments/eventoPersonasConfirmModal :: modalContents";
    }
}
