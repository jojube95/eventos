import {MesaRedonda} from "../../modelos/mesa/MesaRedonda.js";
import {MesaLarga} from "../../modelos/mesa/MesaLarga.js";
import {Mesa} from "../../modelos/mesa/Mesa.js";
import {MesaReserva} from "../../modelos/mesa/MesaReserva.js";


export class MesaFactory {
    static crearMesa(id, eventoId, numero, personas, descripcion, representante, pagado) {
        if (representante !== undefined && pagado !== undefined) {
            return new MesaReserva(id, eventoId, numero, personas, descripcion, representante, pagado);
        } else{
            return new Mesa(id, eventoId, numero, personas, descripcion);
        }
    }

    static crearMesaCanvas(id, eventoId, numero, personas, descripcion, tipo, top, left) {
        if (tipo === 'redonda') {
            return new MesaRedonda(id, eventoId, numero, personas, descripcion, top, left);
        } else if (tipo === 'larga') {
            return new MesaLarga(id, eventoId, numero, personas, descripcion, top, left);
        }
    }
}