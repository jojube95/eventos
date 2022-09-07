package com.example.eventos.mesa;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTextArray;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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
        return "mesas";
    }

    @GetMapping("/evento/mesas/generarListado")
    public ResponseEntity<byte[]> generarListado(@RequestParam("eventoId") String eventoId) {
        List<Mesa> mesas = mesaService.findByEvento(eventoId);

        byte[] contents = listadoPdfGenerator(mesas);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
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
                addContent(document, mesa);

                List<Invitado> invitados = invitadoService.findByMesa(mesa.getId());
                addTable(document, invitados);
            }

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        /*
        try {
            document.open();
            document.add(new Chunk("buenas tardes"));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{3, 3});
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            for (Mesa mesa : mesas) {
                //PdfTextArray textArray = new PdfTextArray(String.valueOf(mesa.getNumero()));

                PdfPCell hcell;

                hcell = new PdfPCell(new Phrase("Nombre", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Descripción", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                List<Invitado> invitados = invitadoService.findByMesa(mesa.getId());

                for (Invitado invitado : invitados) {


                    PdfPCell cell;

                    cell = new PdfPCell(new Phrase((invitado.getNombre())));
                    cell.setPaddingLeft(5);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase((invitado.getDescripcion())));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setPaddingRight(5);
                    table.addCell(cell);
                }
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

         */

        return out.toByteArray();
    }
}
