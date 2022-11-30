export class EventoEmpleado {
    constructor(id, evento, empleado, tipoEmpleado, confirmado, horasExtras) {
        this.id = id;
        this.evento = evento;
        this.empleado = empleado;
        this.tipoEmpleado = tipoEmpleado;
        this.confirmado = confirmado;
        this.horasExtras = horasExtras;
    }

    anyadirEnDatatable() {
        throw new Error('Method "anyadirEnDatatable()" must be implemented.');
    }

    modificarEnDatatable() {
        throw new Error('Method "modificarEnDatatable()" must be implemented.');
    }

    eliminarEnDatatable() {
        throw new Error('Method "eliminarEnDatatable()" must be implemented.');
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

    modificarEventoEmpleadoRow() {
        let modifiedRow = this.getEventoEmpleadoRow();

        let checkboxConfirmado= modifiedRow.children('td').eq(2).children('div').children('input');
        let labelConfirmado = modifiedRow.children('td').eq(2).children('div').children('label');
        let columnHorasExtra = modifiedRow.children('td').eq(3);

        this.confirmado ? checkboxConfirmado.attr('checked', 'checked') : checkboxConfirmado.removeAttr('checked');
        this.confirmado ? labelConfirmado.text('Si') : labelConfirmado.text('No');
        columnHorasExtra.text(this.horasExtras);

        $("#eventoEmpleadoModificarModal").modal("hide");
    }

    getEventoEmpleadoRow() {
        return $('button[eventoempleadoid="' + this.id + '"]').parent().parent();
    }

    toggleLoadingSpinner(element) {
        if (element.find(".spinner-border").length === 0){
            element.prop("disabled", true);
            element.append(" <span class=\"spinner-border spinner-border-sm\" role=\"status\" aria-hidden=\"true\"></span>");
        }
        else{
            element.prop("disabled", false);
            element.find(".spinner-border").remove();
        }
    }
}