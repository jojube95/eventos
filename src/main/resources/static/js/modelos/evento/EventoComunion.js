import {Evento} from "./Evento.js";

export class EventoComunion extends Evento {
    constructor(id, tipo, titulo, personas, fecha) {
        super(id, tipo, titulo, personas, fecha);
    }

    getCalendarioColor() {
        return 'DeepSkyBlue';
    }
}