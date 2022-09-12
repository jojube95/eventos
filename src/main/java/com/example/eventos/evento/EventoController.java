package com.example.eventos.evento;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import com.example.eventos.mesa.Mesa;
import com.example.eventos.mesa.MesaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class EventoController {
    private final EventoService eventoService;

    private final MesaService mesaService;

    private final InvitadoService invitadoService;

    private static final String EVENTO_ATTRIBUTE = "evento";
    private static final String EVENTOS_ATTRIBUTE = "eventos";
    private static final String EVENTO_ID_ATTRIBUTE = "eventoId";
    private static final String PERSONAS_ATTRIBUTE = "personas";

    public EventoController(EventoService eventoService, MesaService mesaService, InvitadoService invitadoService) {
        this.eventoService = eventoService;
        this.mesaService = mesaService;
        this.invitadoService = invitadoService;
    }

    @GetMapping("/verEventos")
    public String verEventos(Model model) {
        List<Evento> eventos = eventoService.getEventos();
        model.addAttribute(EVENTOS_ATTRIBUTE, eventos);
        return "verEventos";
    }

    @GetMapping("/anyadirEvento")
    public String anyadirEvento(Model model) {
        model.addAttribute(EVENTO_ATTRIBUTE, new Evento());
        return "anyadirEvento";
    }

    @GetMapping("/updateEvento")
    public String updateEvento(@RequestParam("eventoId") String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(EVENTO_ATTRIBUTE, evento);
        return "updateEvento";
    }

    @PostMapping("/updateEvento")
    public String updateEvento(@ModelAttribute Evento evento) throws IOException {
        Evento eventoToUpdate = eventoService.getById(evento.getId());
        eventoToUpdate.setFecha(evento.getFecha());
        eventoToUpdate.setLocalidad(evento.getLocalidad());
        eventoToUpdate.setTipo(evento.getTipo());
        eventoToUpdate.setHorario(evento.getHorario());
        eventoToUpdate.setTitulo(evento.getTitulo());
        eventoToUpdate.setSala(evento.getSala());
        eventoToUpdate.setPersonas(evento.getPersonas());
        eventoToUpdate.setPrecioMenu(evento.getPrecioMenu());
        eventoToUpdate.setNinyos(evento.getNinyos());
        eventoToUpdate.setPrecioMenuNinyos(evento.getPrecioMenuNinyos());
        eventoToUpdate.setConfirmado(evento.isConfirmado());
        eventoService.update(eventoToUpdate);
        return "redirect:/calendario";
    }

    @GetMapping("/verEvento")
    public String verEvento(@RequestParam("eventoId") String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(EVENTO_ATTRIBUTE, evento);
        return "verEvento";
    }

    @PostMapping("/anyadirEvento")
    public String save(@ModelAttribute Evento evento) throws IOException {
        eventoService.save(evento);
        return "redirect:/calendario";
    }

    @GetMapping("/eliminarEvento")
    public String eliminarEvento(@RequestParam("eventoId") String eventoId) {
        Evento evento = eventoService.getById(eventoId);
        eventoService.delete(evento);
        return "redirect:/calendario";
    }

    @GetMapping("/evento")
    public String evento(@RequestParam("eventoId") String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(evento);
        return "fragments/eventoModal :: modalContents";
    }

    @GetMapping("/evento/updateFecha")
    public String updateFecha(@RequestParam("id") String id, @RequestParam("fecha") Date fecha) {
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
        model.addAttribute(EVENTO_ID_ATTRIBUTE, eventoId);
        model.addAttribute(PERSONAS_ATTRIBUTE, personas);
        return "fragments/eventoPersonasConfirmModal :: modalContents";
    }
}
