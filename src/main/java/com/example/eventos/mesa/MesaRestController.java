package com.example.eventos.mesa;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
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
    public Mesa add(@RequestBody Mesa mesa, @RequestParam("idEvento") String idEvento){
        Evento evento = eventoService.getById(idEvento);

        mesaService.save(mesa);

        if (!evento.getTipo().equals("Evento individual")) {
            for (int i = 1; i <= mesa.getPersonas(); i++) {
                invitadoService.save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado" + i, "Mayor", ""));
            }
            for (int i = 1; i <= mesa.getNinyos(); i++) {
                invitadoService.save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Niño" + i, "Ninyo", ""));
            }
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
    public ResponseEntity<String> importarMesaInvitadosFromExcel(@RequestParam("file") MultipartFile file, @RequestParam("idEvento") String idEvento ) throws IOException {
        Evento evento =  eventoService.getById(idEvento);
        evento.setDistribucion(new Distribucion(""));
        eventoService.update(evento);
        mesaService.deleteMesas(idEvento);

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
                    Invitado invitado = new Invitado(sheet.getRow(j).getCell(i).getStringCellValue(), idEvento);
                    invitados.add(invitado);
                    if("Niño".equals(invitado.getTipo())){
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

            Mesa mesa = mesaService.save(new Mesa(textoMesa, idEvento, personas, ninyos));

            for (Invitado invitado: invitados) {
                invitado.setIdMesa(mesa.getId());
            }

            invitadoService.saveMany(invitados);
        }

        workbook.close();
        inputStream.close();

        return ResponseEntity.ok("ok");
    }
}
