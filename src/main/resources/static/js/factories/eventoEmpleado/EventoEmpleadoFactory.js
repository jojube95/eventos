import {EventoEmpleado} from "../../modelos/eventoEmpleado/EventoEmpleado.js";
import {EventoEmpleadoCamarero} from "../../modelos/eventoEmpleado/EventoEmpleadoCamarero.js";
import {EventoEmpleadoCocinero} from "../../modelos/eventoEmpleado/EventoEmpleadoCocinero.js";


export class EventoEmpleadoFactory {
    static crearEventoEmpleado(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, dataTableCamareros, dataTablesCocineros) {
        if (tipoEmpleado.value === 'camarero') {
            return new EventoEmpleadoCamarero(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, dataTableCamareros);
        } else if (tipoEmpleado.value === 'cocinero') {
            return new EventoEmpleadoCocinero(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, dataTablesCocineros);
        } else {
            return new EventoEmpleado(id, evento, empleado, tipoEmpleado, confirmado, horasExtras);
        }
    }
}