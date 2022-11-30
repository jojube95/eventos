import {EventoEmpleado} from "./EventoEmpleado.js";

export class EventoEmpleadoCamarero extends EventoEmpleado{
    constructor(id, evento, empleado, tipoEmpleado, confirmado, horasExtras, empleadosDataTable) {
        super(id, evento, empleado, tipoEmpleado, confirmado, horasExtras);
        this.empleadosDataTable = empleadosDataTable;
    }

    anyadirEnDatatable() {
        this.empleadosDataTable.row.add(this.generarEventoEmpleadoRow()).draw();

        toggleLoadingSpinner($('#buttonAnyadirCamarero'));
    }

    modificarEnDatatable() {
        this.modificarEventoEmpleadoRow();
        this.empleadosDataTable.draw();
    }

    eliminarEnDatatable() {
        this.empleadosDataTable.row(this.getEventoEmpleadoRow()).remove().draw();
    }
}