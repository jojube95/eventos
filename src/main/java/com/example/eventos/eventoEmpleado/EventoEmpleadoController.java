package com.example.eventos.eventoEmpleado;

import com.example.eventos.empleado.Empleado;
import com.example.eventos.empleado.EmpleadoService;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.parametros.ParametrosService;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import static com.example.eventos.config.Constants.*;

@Controller
public class EventoEmpleadoController {
    private final EventoEmpleadoService eventoEmpleadoService;
    private final EventoService eventoService;
    private final EmpleadoService empleadoService;
    private final ParametrosService parametrosService;

    public EventoEmpleadoController(EventoEmpleadoService eventoEmpleadoService, EventoService eventoService, EmpleadoService empleadoService,
                                    ParametrosService parametrosService) {
        this.eventoEmpleadoService = eventoEmpleadoService;
        this.eventoService = eventoService;
        this.empleadoService = empleadoService;
        this.parametrosService = parametrosService;
    }

    @GetMapping("/evento/empleados")
    public String eventoEmpleados(@RequestParam(EVENTO_EMPELADO_EVENTO_ID) String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        List<Empleado> camarerosFijos = empleadoService.getByTipoAndFijo(new TipoEmpleado(EMPLEADO_TIPO_CAMARERO), true);
        List<Empleado> camarerosNoFijos = empleadoService.getByTipoAndFijo(new TipoEmpleado(EMPLEADO_TIPO_CAMARERO), false);
        List<Empleado> cocinerosFijos = empleadoService.getByTipoAndFijo(new TipoEmpleado(EMPLEADO_TIPO_COCINERO), true);
        List<Empleado> cocinerosNoFijos = empleadoService.getByTipoAndFijo(new TipoEmpleado(EMPLEADO_TIPO_COCINERO), false);
        List<EventoEmpleado> eventoEmpleadosCamareros = eventoEmpleadoService.getByEventoIdAndTipoEmpleado(eventoId, new TipoEmpleado("camarero"));
        List<EventoEmpleado> eventoEmpleadosCocineros = eventoEmpleadoService.getByEventoIdAndTipoEmpleado(eventoId, new TipoEmpleado("cocinero"));

        model.addAttribute(EVENTO_EMPELADOS_CAMAREROS, eventoEmpleadosCamareros);
        model.addAttribute(EVENTO_EMPELADOS_COCIINEROS, eventoEmpleadosCocineros);
        model.addAttribute(ATTRIBUTE_CAMAREROS_FIJOS, camarerosFijos);
        model.addAttribute(ATTRIBUTE_CAMAREROS_NO_FIJOS, camarerosNoFijos);
        model.addAttribute(ATTRIBUTE_COCINEROS_FIJOS, cocinerosFijos);
        model.addAttribute(ATTRIBUTE_COCINEROS_NO_FIJOS, cocinerosNoFijos);
        model.addAttribute(EVENTO_EMPELADO_EVENTO_ID, eventoId);
        model.addAttribute(PERSONAS, evento.getPersonas());
        model.addAttribute(ATTRIBUTE_CAMAREROS_RECOMENDADOS, evento.getCamarerosRecomendados(parametrosService.get().getRatioCamarerosEvento()));
        return EVENTO_EMPELADOS_PAGE;
    }

    @GetMapping("/evento/empleados/modificar")
    public String modificarVer(@RequestParam(EVENTO_EMPELADO_ID) String eventoEmpleadoId, Model model) {
        EventoEmpleado eventoEmpleado = eventoEmpleadoService.getById(eventoEmpleadoId);
        model.addAttribute(eventoEmpleado);
        return "fragments/eventoEmpleadoModificarModal :: modalContents";
    }
}
