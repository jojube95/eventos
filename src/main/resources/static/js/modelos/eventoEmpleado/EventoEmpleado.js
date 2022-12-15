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
        throw new Error('Method "modificarEventoEmpleadoRow()" must be implemented.');
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

    generateEmpleadoOption() {
        return $('<option>', {
            value: this.empleado.id,
            text: this.empleado.persona.nombre
        })
    }
}