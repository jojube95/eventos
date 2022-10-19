package com.example.eventos.evento;

import com.example.eventos.horarioEvento.HorarioEventoService;
import com.example.eventos.parametros.ParametrosService;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEvento.TipoEventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Date;
import java.util.List;
import static com.example.eventos.config.Constants.*;

@Controller
public class EventoController {
    private final EventoService eventoService;
    private final ParametrosService parametrosService;
    private final TipoEventoService tipoEventoService;
    private final HorarioEventoService horarioEventoService;

    public EventoController(EventoService eventoService, ParametrosService parametrosService, TipoEventoService tipoEventoService, HorarioEventoService horarioEventoService) {
        this.eventoService = eventoService;
        this.parametrosService = parametrosService;
        this.tipoEventoService = tipoEventoService;
        this.horarioEventoService = horarioEventoService;
    }

    @GetMapping("/eventosVer")
    public String eventosVer(Model model) {
        List<Evento> eventos = eventoService.getEventos();
        model.addAttribute(EVENTOS, eventos);
        model.addAttribute(ATTRIBUTE_RATIO_BENEFICIOS, parametrosService.get().getRatioBeneficios());
        return EVENTOS_VER_PAGE;
    }

    @GetMapping("/eventoAnyadir")
    public String eventoAnyadir(Model model) {
        model.addAttribute(EVENTO, new Evento());
        model.addAttribute(ATTRIBUTE_TIPOS_EVENTO, tipoEventoService.getTipoEventos());
        model.addAttribute(ATTRIBUTE_HORARIOS_EVENTO, horarioEventoService.getHorarioEventos());
        model.addAttribute(ATTRIBUTE_PRECIO_NINYOS_BODA_COMUNION, parametrosService.get().getPrecioNinyosBodaComunion());
        model.addAttribute(ATTRIBUTE_PRECIO_NINYOS_OTROS, parametrosService.get().getPrecioNinyosOtros());
        return EVENTO_ANYADIR_PAGE;
    }

    @GetMapping("/eventoUpdate")
    public String eventoUpdate(@RequestParam(EVENTO_ID) String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(EVENTO, evento);
        model.addAttribute(ATTRIBUTE_TIPOS_EVENTO, tipoEventoService.getTipoEventos());
        model.addAttribute(ATTRIBUTE_HORARIOS_EVENTO, horarioEventoService.getHorarioEventos());
        model.addAttribute(ATTRIBUTE_PRECIO_NINYOS_BODA_COMUNION, parametrosService.get().getPrecioNinyosBodaComunion());
        model.addAttribute(ATTRIBUTE_PRECIO_NINYOS_OTROS, parametrosService.get().getPrecioNinyosOtros());
        return EVENTO_UPDATE_PAGE;
    }

    @PostMapping("/eventoUpdate")
    public String eventoUpdate(@ModelAttribute Evento evento) {
        Evento eventoToUpdate = eventoService.getById(evento.getId());
        eventoToUpdate.setFecha(evento.getFecha());
        eventoToUpdate.setLocalidad(evento.getLocalidad());
        eventoToUpdate.setTipo(evento.getTipo());
        eventoToUpdate.setHorario(evento.getHorario());
        eventoToUpdate.setTitulo(evento.getTitulo());
        eventoToUpdate.setSala(evento.getSala());
        eventoToUpdate.setPersonas(evento.getPersonas());
        eventoToUpdate.setPrecioMenu(evento.getPrecioMenu());
        eventoToUpdate.setPrecioMenuNinyos(evento.getPrecioMenuNinyos());
        eventoToUpdate.setConfirmado(evento.isConfirmado());
        eventoService.update(eventoToUpdate);
        return "redirect:/" + CALENDARIO_PAGE;
    }

    @GetMapping("/eventoVer")
    public String eventoVer(@RequestParam(EVENTO_ID) String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(EVENTO, evento);
        return EVENTO_VER_PAGE;
    }

    @PostMapping("/eventoAnyadir")
    public String save(@ModelAttribute Evento evento) {
        Evento eventoCreated = EventoFactory.crearEvento(evento);
        eventoService.save(eventoCreated);
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
    public String calcularPersonas(@RequestParam(EVENTO_ID) String eventoId, Model model) {
        Personas personas = eventoService.calcularPersonas(eventoId);

        model.addAttribute(EVENTO_ID, eventoId);
        model.addAttribute(PERSONAS, personas);
        return "fragments/eventoPersonasConfirmModal :: modalContents";
    }
}
