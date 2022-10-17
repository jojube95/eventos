package com.example.eventos.mesa;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.example.eventos.config.Constants.INVITADO_TIPO_MAYOR;
import static com.example.eventos.pdf.PdfCreator.*;
import static com.example.eventos.config.Constants.INVITADO_TIPO_NINYO;

@Service
public class MesaService {
    final Logger logger = LoggerFactory.getLogger(MesaService.class);

    private final MesaRepository mesaRepository;
    private final InvitadoRepository invitadoRepository;

    public MesaService(MesaRepository mesaRepository, InvitadoRepository invitadoRepository) {
        this.mesaRepository = mesaRepository;
        this.invitadoRepository = invitadoRepository;
    }

    public List<Mesa> findByEvento(String eventoId){
        return mesaRepository.findByEventoId(eventoId);
    }

    public List<Mesa> findByEventoOrderByNumero(String eventoId){
        return mesaRepository.findByEventoIdOrderByNumeroAsc(eventoId);
    }

    public Mesa save(Mesa mesa){
        return mesaRepository.save(mesa);
    }

    public void delete(Mesa mesa){
        invitadoRepository.deleteByMesaId(mesa.getId());
        mesaRepository.delete(mesa);
    }

    public void deleteMesas(String eventoId){
        invitadoRepository.deleteByEventoId(eventoId);
        mesaRepository.deleteByEventoId(eventoId);
    }

    public void generateInvitados(Mesa mesa) {
        // TODO: Move this logic to PersonasService
        for (int i = 1; i <= mesa.getPersonas().getMayores(); i++) {
            invitadoRepository.save(new Invitado(mesa.getEventoId(), mesa.getId(), "Invitado" + i, INVITADO_TIPO_MAYOR, ""));
        }
        for (int i = 1; i <= mesa.getPersonas().getNinyos(); i++) {
            invitadoRepository.save(new Invitado(mesa.getEventoId(), mesa.getId(), INVITADO_TIPO_NINYO + i, INVITADO_TIPO_NINYO, ""));
        }
    }

    public ResponseEntity<byte[]> listadoPdfGenerator(List<Mesa> mesas) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();

            addMetaData(document);
            addTitle(document);

            for (Mesa mesa : mesas) {
                List<Invitado> invitados = invitadoRepository.findByMesaId(mesa.getId());
                addTable(document, mesa, invitados);
            }

            document.close();

        } catch (DocumentException ex) {
            logger.error("Error occurred on generate Listado: ", ex);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        String filename = "llistat.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
}
