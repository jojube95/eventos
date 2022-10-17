package com.example.eventos.mesa;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import com.example.eventos.personas.Personas;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.eventos.config.Constants.*;

@RestController
public class MesaRestController {
    private final MesaService mesaService;

    private final InvitadoService invitadoService;

    private final EventoService eventoService;

    public MesaRestController(MesaService mesaService, InvitadoService invitadoService, EventoService eventoService) {
        this.mesaService = mesaService;
        this.invitadoService = invitadoService;
        this.eventoService = eventoService;
    }

    @PostMapping("/evento/mesas/add")
    public Mesa add(@RequestBody Mesa mesa, @RequestParam(EVENTO_ID) String eventoId){
        Evento evento = eventoService.getById(eventoId);

        mesaService.save(mesa);

        // TODO: Add polyphormism with Mesa -> MesaReserva and MesaService -> MesaReservaService
        if (!evento.getTipo().getValue().equals(EVENTO_TIPO_INDIVIDUAL)) {
            mesaService.generateInvitados(mesa);
        }

        return mesa;
    }

    @PostMapping("/evento/mesas/delete")
    public Mesa delete(@RequestBody Mesa mesa) {
        mesaService.delete(mesa);
        invitadoService.deleteInvitados(mesa.getId());
        return mesa;
    }

    @PostMapping("/evento/mesas/update")
    public Mesa update(@RequestBody Mesa mesa) {
        mesaService.save(mesa);
        return mesa;
    }

    @PostMapping("/evento/mesas/uploadExcel")
    public ResponseEntity<String> importarMesaInvitadosFromExcel(@RequestParam("file") MultipartFile file,
                                                                 @RequestParam(EVENTO_ID) String eventoId ) throws IOException {
        Evento evento =  eventoService.getById(eventoId);
        evento.setDistribucion(new Distribucion(""));
        eventoService.update(evento);
        mesaService.deleteMesas(eventoId);

        InputStream inputStream = file.getInputStream();

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        int columns = sheet.getRow(0).getPhysicalNumberOfCells();

        //Iterate over columns
        for (int i = 0 ; i < columns ; i++){
            List<Invitado> invitados = new ArrayList<>();
            int personas = 0;
            int ninyos = 0;

            //Iterate over column rows
            for (int j = 1; sheet.getRow(j) != null; j++){
                if(sheet.getRow(j).getCell(i) != null) {
                    //Create Invitado
                    Invitado invitado = new Invitado(sheet.getRow(j).getCell(i).getStringCellValue(), eventoId);
                    invitados.add(invitado);
                    // TODO: Add polyphormism InvitadoMayor, InvitadoNinyo and implements sumPersonas(Personas personas)
                    if(INVITADO_TIPO_NINYO.equals(invitado.getTipo())){
                        ninyos++;
                    }
                    else{
                        personas++;
                    }
                }
                else{
                    break;
                }
            }

            String textoMesa = sheet.getRow(0).getCell(i).getStringCellValue();

            Mesa mesa = mesaService.save(new Mesa(textoMesa, eventoId, new Personas(personas, ninyos)));

            for (Invitado invitado: invitados) {
                invitado.setMesaId(mesa.getId());
            }

            invitadoService.saveMany(invitados);
        }

        workbook.close();
        inputStream.close();

        return ResponseEntity.ok("ok");
    }
}
