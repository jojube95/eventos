import {Evento} from "./Evento.js";

export class EventoBoda extends Evento {
    constructor(id, tipo, titulo, personas, fecha, descripcion) {
        super(id, tipo, titulo, personas, fecha, descripcion);
    }

    getCalendarioColor() {
        return 'FireBrick';
    }
}