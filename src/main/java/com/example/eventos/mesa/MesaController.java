package com.example.eventos.mesa;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.InvitadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.example.eventos.config.Constants.*;

@Controller
public class MesaController {
    private final MesaService mesaService;

    private final EventoService eventoService;

    public MesaController(MesaService mesaService, EventoService eventoService, InvitadoService invitadoService) {
        this.mesaService = mesaService;
        this.eventoService = eventoService;
    }

    @GetMapping("/evento/mesas")
    public String getMesas(@RequestParam(EVENTO_ID) String eventoId, Model model) {
        List<Mesa> mesas = mesaService.findByEvento(eventoId);
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute(EVENTO, evento);
        model.addAttribute("isEventoIndividual", evento.isEventoIndividual());
        model.addAttribute(MESAS, mesas);
        return MESAS_PAGE;
    }

    @GetMapping("/evento/mesas/generarListado")
    public ResponseEntity<byte[]> generarListado(@RequestParam(EVENTO_ID) String eventoId) {
        List<Mesa> mesas = mesaService.findByEventoOrderByNumero(eventoId);

        return mesaService.listadoPdfGenerator(mesas);
    }
}
