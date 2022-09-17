package com.example.eventos.mesa;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;

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
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("idEvento") String idEvento ) throws IOException, ParseException {
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
            int[] personasNinyos = getPersonasNinyosFromRow(sheet, i);
            String textoMesa = sheet.getRow(0).getCell(i).getStringCellValue();

            //Create Mesa, returning id
            Mesa mesa = createMesaBy(textoMesa, idEvento, personasNinyos[0], personasNinyos[1]);
            System.out.println(mesa);

            //Iterate over column rows
            for (int j = 1; sheet.getRow(j) != null; j++){
                if(sheet.getRow(j).getCell(i) != null) {
                    //Create Invitado
                    createInvitadoBy(sheet.getRow(j).getCell(i).getStringCellValue(), idEvento, mesa.getId());
                }
                else{
                    break;
                }
            }
        }

        workbook.close();
        inputStream.close();

        return ResponseEntity.ok("ok");
    }

    private Mesa createMesaBy(String textoMesa, String idEvento, int personas, int ninyos) throws ParseException {
        String[] valoresMesa = textoMesa.split("-");
        int numero = 0;
        String descripcion = "";

        for (int i = 0; i < valoresMesa.length; i++) {
            if (i == 0) {
                numero = Integer.parseInt(valoresMesa[0].replaceAll("[^0-9]", ""));
            }
            else{
                descripcion = valoresMesa[i].trim();
            }
        }

        Mesa mesa = new Mesa(idEvento, personas, ninyos, numero, descripcion);

        return mesaService.save(mesa);
    }

    private void createInvitadoBy(String textoInvitado, String idEvento, String idMesa){
        String[] myData = textoInvitado.split("-");
        String nombre = "";
        String tipo = "";
        String descripcion = "";
        
        for (int i = 0; i < myData.length; i++) {
            if (i == 0) {
                nombre = myData[0].trim();
            }
            else{
                if (myData[i].trim().equals("x")) {
                    tipo = "Niño";
                }
                else{
                    descripcion = myData[i].trim();
                }
            }
        }
        if(tipo.isEmpty()){
            tipo = "Mayor";
        }
        invitadoService.save(new Invitado(idEvento, idMesa, nombre, tipo, descripcion));
    }

    private int[] getPersonasNinyosFromRow(Sheet sheet, int columnNumber){
        int[] personasNinyos = new int[2];

        int personas = 0;
        int ninyos = 0;

        for (int j = 1; sheet.getRow(j) != null; j++){
            boolean esNinyo = false;
            if(sheet.getRow(j).getCell(columnNumber) != null) {
                String[] myData = sheet.getRow(j).getCell(columnNumber).getStringCellValue().split("-");
                for (String s: myData) {
                    if (s.trim().equals("x")) {
                        esNinyo = true;
                        break;
                    }
                }
                if (esNinyo){
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
        personasNinyos[0] = personas;
        personasNinyos[1] = ninyos;
        return personasNinyos;
    }
}
