import {Evento} from "./Evento.js";

export class EventoComunal extends Evento {
    constructor(id, tipo, titulo, personas, fecha, descripcion) {
        super(id, tipo, titulo, personas, fecha, descripcion);
    }

    getCalendarioColor() {
        return 'DarkSeaGreen';
    }

    generarTitulo(tituloInput, selectedTipo, selectedHorario) {
        tituloInput.val('');
        tituloInput.prop('readonly', false);
    }
}