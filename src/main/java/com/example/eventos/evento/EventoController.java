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
import static com.example.eventos.config.Constants.*;

@Controller
public class EventoController {
    private final EventoService eventoService;

    private final MesaService mesaService;

    private final InvitadoService invitadoService;

    public EventoController(EventoService eventoService, MesaService mesaService, InvitadoService invitadoService) {
        this.eventoService = eventoService;
        this.mesaService = mesaService;
        this.invitadoService = invitadoService;
    }

    @GetMapping("/verEventos")
    public String verEventos(Model model) {
        List<Evento> eventos = eventoService.getEventos();
        model.addAttribute(EVENTOS, eventos);
        return EVENTOS_VER_PAGE;
    }

    @GetMapping("/anyadirEvento")
    public String anyadirEvento(Model model) {
        model.addAttribute(EVENTO, new Evento());
        return EVENTO_ANYADIR_PAGE;
    }

    @GetMapping("/updateEvento")
    public String updateEvento(@RequestParam(EVENTO_ID) String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(EVENTO, evento);
        return EVENTO_UPDATE_PAGE;
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
        return "redirect:/" + CALENDARIO_PAGE;
    }

    @GetMapping("/verEvento")
    public String verEvento(@RequestParam(EVENTO_ID) String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(EVENTO, evento);
        return EVENTO_VER_PAGE;
    }

    @PostMapping("/anyadirEvento")
    public String save(@ModelAttribute Evento evento) throws IOException {
        eventoService.save(evento);
        return "redirect:/" + CALENDARIO_PAGE;
    }

    @GetMapping("/eliminarEvento")
    public String eliminarEvento(@RequestParam(EVENTO_ID) String eventoId) {
        Evento evento = eventoService.getById(eventoId);
        eventoService.delete(evento);
        return "redirect:/" + CALENDARIO_PAGE;
    }

    @GetMapping("/evento/updateFecha")
    public String updateFecha(@RequestParam(EVENTO_ID) String eventoId, @RequestParam(EVENTO_FECHA) Date fecha) {
        Evento evento = eventoService.getById(eventoId);
        evento.setFecha(fecha);
        eventoService.update(evento);
        return "redirect:/" + CALENDARIO_PAGE;
    }

    @GetMapping("/evento/calcularPersonas")
    public String calcularPersonas(@RequestParam(EVENTO_ID) String eventoId, Model model){
        List<Mesa> mesas = mesaService.findByEvento(eventoId);
        int personas = 0;
        int ninyos = 0;
        for (Mesa mesa : mesas) {
            List<Invitado> invitados = invitadoService.findByMesa(mesa.getId());
            for (Invitado invitado: invitados) {
                if("Niño".equals(invitado.getTipo())){
                    ninyos++;
                }
                else{
                    personas++;
                }
            }
        }
        model.addAttribute(EVENTO_ID, eventoId);
        model.addAttribute(EVENTO_PERSONAS, personas);
        model.addAttribute(EVENTO_NINYOS, ninyos);
        return "fragments/eventoPersonasConfirmModal :: modalContents";
    }
}
