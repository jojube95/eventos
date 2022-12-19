export class EventoEmpleado {
    constructor(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, empleadosDataTable, buttonAnyadirEmpleado) {
        this.id = id;
        this.evento = evento;
        this.empleado = empleado;
        this.tipoEmpleado = tipoEmpleado;
        this.confirmado = confirmado;
        this.horasExtras = horasExtras;
        this.empleadosDataTable = empleadosDataTable;
        this.buttonAnyadirEmpleado = buttonAnyadirEmpleado;
    }

    anyadirEnDatatable() {
        this.empleadosDataTable.row.add(this.generarEventoEmpleadoRow()).draw();

        toggleLoadingSpinner(this.buttonAnyadirEmpleado);

        this.eliminarDeDropdown();
    }

    modificarEnDatatable() {
        this.modificarEventoEmpleadoRow();
        this.empleadosDataTable.draw();
    }

    eliminarEnDatatable() {
        this.empleadosDataTable.row(this.getEventoEmpleadoRow()).remove().draw();

        this.anyadirAlDropdown();
    }
    eliminarDeDropdown() {
        throw new Error('Method "eliminarDeDropdown()" must be implemented.');
    }

    anyadirAlDropdown() {
        throw new Error('Method "anyadirAlDropdown()" must be implemented.');
    }

    generarEventoEmpleadoRow() {
        throw new Error('Method "generarEventoEmpleadoRow()" must be implemented.');
    }

    modificarEventoEmpleadoRow() {
        let modifiedRow = this.getEventoEmpleadoRow();

        let checkboxConfirmado= modifiedRow.children('td').eq(this.getColumnConfirmado()).children('div').children('input');
        let labelConfirmado = modifiedRow.children('td').eq(this.getColumnConfirmado()).children('div').children('label');
        let columnHorasExtra = modifiedRow.children('td').eq(this.getColumnHorasExtra());

        this.confirmado ? checkboxConfirmado.attr('checked', 'checked') : checkboxConfirmado.removeAttr('checked');
        this.confirmado ? labelConfirmado.text('Si') : labelConfirmado.text('No');
        columnHorasExtra.text(this.horasExtras);

        $("#eventoEmpleadoModificarModal").modal("hide");
    }

    getEventoEmpleadoRow() {
        return $('button[eventoempleadoid="' + this.id + '"]').parent().parent();
    }

    generateEmpleadoOption() {
        return $('<option>', {
            value: this.empleado.id,
            text: this.empleado.persona.nombre
        })
    }

    getColumnConfirmado(){
        return 6;
    }

    getColumnHorasExtra(){
        throw new Error('Method "getColumnHorasExtra()" must be implemented.');
    }
}