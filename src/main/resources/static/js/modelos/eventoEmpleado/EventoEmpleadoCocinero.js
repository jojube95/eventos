import {EventoEmpleado} from "./EventoEmpleado.js";

export class EventoEmpleadoCocinero extends EventoEmpleado{
    constructor(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, empleadosDataTable) {
        super(id, evento, empleado, tipoEmpleado, confirmado, horasExtras);
        this.empleadosDataTable = empleadosDataTable;
    }

    anyadirEnDatatable() {
        this.empleadosDataTable.row.add(this.generarEventoEmpleadoRow()).draw();

        toggleLoadingSpinner($('#buttonAnyadirCocinero'));

        this.eliminarDeDropdown();
    }

    modificarEnDatatable() {
        this.modificarEventoEmpleadoRow();
        this.empleadosDataTable.draw();
    }

    modificarEventoEmpleadoRow() {
        let modifiedRow = this.getEventoEmpleadoRow();

        let checkboxConfirmado= modifiedRow.children('td').eq(6).children('div').children('input');
        let labelConfirmado = modifiedRow.children('td').eq(6).children('div').children('label');
        let columnHorasExtra = modifiedRow.children('td').eq(7);

        this.confirmado ? checkboxConfirmado.attr('checked', 'checked') : checkboxConfirmado.removeAttr('checked');
        this.confirmado ? labelConfirmado.text('Si') : labelConfirmado.text('No');
        columnHorasExtra.text(this.horasExtras);

        $("#eventoEmpleadoModificarModal").modal("hide");
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

    eliminarEnDatatable() {
        this.empleadosDataTable.row(this.getEventoEmpleadoRow()).remove().draw();

        this.anyadirAlDropdown();
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
}