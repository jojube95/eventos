import {Mesa} from "./Mesa.js";


export class MesaReserva extends Mesa {
    constructor(id, eventoId, numero, personas, descripcion, representante, pagado) {
        super(id, eventoId, numero, personas, descripcion);
        this.representante = representante;
        this.pagado = pagado;
    }

    getDataTableRowData() {
        return {id: this.id, eventoId: this.eventoId, numero: this.numero, mayores: this.personas.mayores, ninyos: this.personas.ninyos,
            descripcion: this.descripcion, representante: this.representante, pagado: this.pagado}
    }
}