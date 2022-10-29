import {MesaRedonda} from "../../modelos/mesa/MesaRedonda.js";
import {MesaLarga} from "../../modelos/mesa/MesaLarga.js";
import {Mesa} from "../../modelos/mesa/Mesa.js";
import {MesaReserva} from "../../modelos/mesa/MesaReserva.js";
import {MesaCanvas} from "../../modelos/mesa/MesaCanvas.js";


export class MesaFactory {
    static crearMesa(id, eventoId, numero, personas, descripcion, representante, pagado) {
        if (representante !== undefined && pagado !== undefined) {
            return new MesaReserva(id, eventoId, numero, personas, descripcion, representante, pagado);
        } else{
            return new Mesa(id, eventoId, numero, personas, descripcion);
        }
    }

    static crearMesaCanvas(id, eventoId, numero, personas, descripcion, tipo, top, left) {
        if (tipo === 'redonda' || tipo === 'circle') {
            return new MesaRedonda(id, eventoId, numero, personas, descripcion, top, left);
        } else if (tipo === 'larga' || tipo === 'rect') {
            return new MesaLarga(id, eventoId, numero, personas, descripcion, top, left);
        }
        else{
            return new MesaCanvas(id, eventoId, numero, personas, descripcion, top, left);
        }
    }

    static crearMesaCanvasByCanvasObject(object) {
        if (object._objects[0].type === 'circle') {
            return new MesaRedonda(object.mesaId, undefined, object.numero, {mayores: object.mayores, ninyos: object.ninyos}, undefined, object.top, object.left);
        } else if (object._objects[0].type === 'rect') {
            return new MesaLarga(object.mesaId, undefined, object.numero, {mayores: object.mayores, ninyos: object.ninyos}, undefined, object.top, object.left);
        }
    }
}