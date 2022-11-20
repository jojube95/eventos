import {MesaCanvas} from "./MesaCanvas.js";
import {MesaFactory} from "../../factories/mesa/MesaFactory.js";

const TABLE_RADIUS = 36;

export class MesaRedonda extends MesaCanvas {
    constructor(id, eventoId, numero, personas, descripcion, top, left) {
        super(id, eventoId, numero, personas, descripcion, top, left);
    }

    addToCanvas(canvas, success) {
        let circle = new fabric.Circle({
            radius: TABLE_RADIUS,
            fill : 'white',
            stroke: 'black',
            strokeWidth: 1,
            originX: 'center',
            originY: 'center'
        });

        this.insertTextToObject(circle, canvas, success);
    }

    update(canvas, success) {
        if(this.personasCabenEnRedonda()) {
            this.delete(canvas, success);
            let nuevaMesaCanvas = MesaFactory.crearMesaCanvas(this.id, this.eventoId, this.numero, this.personas, this.descripcion, 'redonda', this.top, this.left);
            nuevaMesaCanvas.addToCanvas(canvas, success);
        }
        else{
            this.delete(canvas, success);
            let nuevaMesaCanvas = MesaFactory.crearMesaCanvas(this.id, this.eventoId, this.numero, this.personas, this.descripcion, 'larga', this.top, this.left);
            nuevaMesaCanvas.addToCanvas(canvas, success);
        }

    }
}