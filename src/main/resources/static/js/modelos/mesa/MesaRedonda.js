import {MesaCanvas} from "./MesaCanvas.js";

export class MesaRedonda extends MesaCanvas {
    constructor(id, eventoId, numero, personas, descripcion, top, left) {
        super(id, eventoId, numero, personas, descripcion, top, left);
    }

    addToCanvas(canvas, success) {
        let circle = new fabric.Circle({
            radius: 36,
            fill : 'white',
            stroke: 'black',
            strokeWidth: 1,
            originX: 'center',
            originY: 'center'
        });

        this.insertTextToObject(this.id, this.numero, this.personas, this.top, this.left, circle, canvas, success);
    }
}