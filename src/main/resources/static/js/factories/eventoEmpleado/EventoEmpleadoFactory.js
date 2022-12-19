import {EventoEmpleado} from "../../modelos/eventoEmpleado/EventoEmpleado.js";
import {EventoEmpleadoCamarero} from "../../modelos/eventoEmpleado/EventoEmpleadoCamarero.js";
import {EventoEmpleadoCocinero} from "../../modelos/eventoEmpleado/EventoEmpleadoCocinero.js";


export class EventoEmpleadoFactory {
    static crearEventoEmpleado(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, dataTableCamareros, dataTablesCocineros, buttonAnyadirCamarero, buttonAnyadirCocinero) {
        if (tipoEmpleado.value === 'camarero') {
            return new EventoEmpleadoCamarero(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, dataTableCamareros, buttonAnyadirCamarero);
        } else if (tipoEmpleado.value === 'cocinero') {
            return new EventoEmpleadoCocinero(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, dataTablesCocineros, buttonAnyadirCocinero);
        } else {
            return new EventoEmpleado(id, evento, empleado, tipoEmpleado, confirmado, horasExtras);
        }
    }
}