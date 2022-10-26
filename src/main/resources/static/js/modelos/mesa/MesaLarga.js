import {Mesa} from "./Mesa.js";


export class MesaLarga extends Mesa {
    constructor(id, eventoId, numero, personas, descripcion, top, left) {
        super(id, eventoId, numero, personas, descripcion);
        this.top = top;
        this.left = left;
    }

    addToCanvas(canvas) {
        // TODO: Implement
    }
}