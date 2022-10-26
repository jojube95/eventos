import {Mesa} from "./Mesa.js";


export class MesaReserva extends Mesa {
    constructor(id, eventoId, numero, personas, descripcion, representante, pagado) {
        super(id, eventoId, numero, personas, descripcion);
        this.representante = representante;
        this.pagado = pagado;
    }

    addToCanvas(canvas) {
        // TODO: Implement
    }
}