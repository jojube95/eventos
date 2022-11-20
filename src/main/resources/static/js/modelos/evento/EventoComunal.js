import {Evento} from "./Evento.js";

export class EventoComunal extends Evento {
    constructor(id, tipo, titulo, personas, fecha) {
        super(id, tipo, titulo, personas, fecha);
    }

    getCalendarioColor() {
        return 'DarkSeaGreen';
    }

    generarTitulo(tituloInput, selectedTipo, selectedHorario) {
        tituloInput.val('');
        tituloInput.prop('readonly', false);
    }
}