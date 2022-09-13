package com.example.eventos.mesa;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.util.List;
import static com.example.eventos.pdf.PdfCreator.*;

@Controller
public class MesaController {
    Logger logger = LoggerFactory.getLogger(MesaController.class);

    private final MesaService mesaService;

    private final EventoService eventoService;

    private final InvitadoService invitadoService;

    public MesaController(MesaService mesaService, EventoService eventoService, InvitadoService invitadoService) {
        this.mesaService = mesaService;
        this.eventoService = eventoService;
        this.invitadoService = invitadoService;
    }

    @GetMapping("/evento/mesas")
    public String getMesas(@RequestParam("eventoId") String eventoId, Model model) {
        List<Mesa> mesas = mesaService.findByEvento(eventoId);
        Evento evento = eventoService.getById(eventoId);
        model.addAttribute("idEvento", eventoId);
        model.addAttribute("isEventoIndividual", evento.isEventoIndividual());
        model.addAttribute("distribucion", evento.getDistribucion().getDistribucion());
        model.addAttribute("mesas", mesas);
        model.addAttribute("sala", evento.getSala());
        return "mesas";
    }

    @GetMapping("/evento/mesas/generarListado")
    public ResponseEntity<byte[]> generarListado(@RequestParam("eventoId") String eventoId) {
        List<Mesa> mesas = mesaService.findByEventoOrderByNumero(eventoId);

        byte[] contents = listadoPdfGenerator(mesas);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        String filename = "llistat.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    private byte[] listadoPdfGenerator(List<Mesa> mesas) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();

            addMetaData(document);
            addTitle(document);

            for (Mesa mesa : mesas) {
                List<Invitado> invitados = invitadoService.findByMesa(mesa.getId());
                addTable(document, mesa, invitados);
            }

            document.close();

        } catch (DocumentException ex) {
            logger.error("Error occurred on generate Listado: ", ex);
        }

        return out.toByteArray();
    }
}
