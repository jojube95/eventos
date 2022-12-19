import {EventoEmpleado} from "./EventoEmpleado.js";

export class EventoEmpleadoCocinero extends EventoEmpleado{
    constructor(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, empleadosDataTable, buttonAnyadirEmpleado) {
        super(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, empleadosDataTable, buttonAnyadirEmpleado);
    }

    generarEventoEmpleadoRow() {
        return [
            this.id,
            this.evento.id,
            this.empleado.id,
            this.empleado.tipo.value,
            this.empleado.persona.nombre,
            '<div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input"' + (this.empleado.fijo ? 'checked="checked"' : '') + '><label class="custom-control-label">' + (this.empleado.fijo ? 'Si' : 'No') + '</label></div>',
            '<div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input"' + (this.confirmado ? 'checked="checked"' : '') + '><label class="custom-control-label">' + (this.confirmado ? 'Si' : 'No') + '</label></div>',
            this.horasExtras,
            '<button type="button" class="btn btn-primary" eventoempleadoid="' + this.id + '" onclick="modificarClicked(this.getAttribute(\'eventoEmpleadoId\'))">Modificar</button>',
            '<button type="button" class="btn btn-danger" eventoempleadoid="' + this.id + '" onclick="eliminarClicked(this.getAttribute(\'eventoEmpleadoId\'))">Eliminar</button>'
        ]
    }

    eliminarDeDropdown() {
        $('#empleadoCocinero > option[value="' + this.empleado.id + '"]').remove();
    }

    anyadirAlDropdown() {
        if(this.empleado.fijo) {
            $(this.generateEmpleadoOption()).insertBefore('#empleadoCocinero > option[value="noFijos"]');
        }
        else{
            $('#empleadoCocinero').append(this.generateEmpleadoOption());
        }
    }

    getColumnHorasExtra(){
        return 7;
    }
}