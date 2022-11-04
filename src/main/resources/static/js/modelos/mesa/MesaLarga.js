import {MesaCanvas} from "./MesaCanvas.js";

const TABLE_WIDTH = 40;
const TABLE_LENGTH = 80;
const TABLE_APOYO_LENGTH = 26;
const MAX_PERSONAS_TABLE = 6;

export class MesaLarga extends MesaCanvas {
    constructor(id, eventoId, numero, personas, descripcion, top, left) {
        super(id, eventoId, numero, personas, descripcion, top, left);
    }

    addToCanvas(canvas, success) {
        let tableLength = this.calcularLongitudMesaLarga();

        let rect = new fabric.Rect({
            width : TABLE_WIDTH,
            height : tableLength,
            fill : 'white',
            stroke: 'black',
            strokeWidth: 1,
            originX: 'center',
            originY: 'center'
        });

        this.insertTextToObject(rect, canvas, success);
    }

    calcularLongitudMesaLarga(){
        let mesas = this.calcularLargasApoyos();
        return (mesas.largas * TABLE_LENGTH) + (mesas.apoyos * TABLE_APOYO_LENGTH)
    }

    calcularLargasApoyos(){
        let personasUltimaMesa = (this.personas.mayores +  this.personas.ninyos) % 6;

        if (this.getTotalPersonas() <= 2){
            return {'largas' : 0, 'apoyos': 2}
        }
        else if (this.getTotalPersonas() > 2 && this.getTotalPersonas() <= MAX_PERSONAS_TABLE){
            return {'largas' : 1, 'apoyos': 0}
        }
        else if (personasUltimaMesa > 0 && personasUltimaMesa <= 2){
            return {'largas' : Math.ceil(this.getTotalPersonas() / MAX_PERSONAS_TABLE) - 1, 'apoyos': 1}
        }
        else{
            return {'largas' : Math.ceil(this.getTotalPersonas() / MAX_PERSONAS_TABLE), 'apoyos': 0}
        }
    }

    update(canvas, success) {
        this.delete(canvas, function() {});
        this.addToCanvas(canvas, success);
    }
}