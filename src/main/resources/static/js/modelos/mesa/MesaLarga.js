import {MesaCanvas} from "./MesaCanvas.js";

export class MesaLarga extends MesaCanvas {
    constructor(id, eventoId, numero, personas, descripcion, top, left) {
        super(id, eventoId, numero, personas, descripcion, top, left);
    }

    addToCanvas(canvas, success) {
        let tableLength = this.calcularLongitudMesaLarga();

        let rect = new fabric.Rect({
            width : 40,
            height : tableLength,
            fill : 'white',
            stroke: 'black',
            strokeWidth: 1,
            originX: 'center',
            originY: 'center'
        });

        this.insertTextToObject(this.id, this.numero, this.personas, this.top, this.left, rect, canvas, success);
    }

    calcularLongitudMesaLarga(){
        let mesas = this.calcularLargasApoyos();
        return (mesas.largas * 80) + (mesas.apoyos * 26)
    }

    calcularLargasApoyos(){
        let mayoresUltimaMesa = this.personas.mayores % 6;

        if (this.getTotalPersonas() <= 2){
            return {'largas' : 0, 'apoyos': 2}
        }
        else if (this.getTotalPersonas() > 2 && this.getTotalPersonas() <= 6){
            return {'largas' : 1, 'apoyos': 0}
        }
        else if (mayoresUltimaMesa > 0 && mayoresUltimaMesa <= 2){
            return {'largas' : Math.ceil(this.getTotalPersonas() / 6) - 1, 'apoyos': 1}
        }
        else{
            return {'largas' : Math.ceil(this.getTotalPersonas() / 6), 'apoyos': 0}
        }
    }

    reverse(eventData, transform){
        // TODO: Use get object from canvas to get object
        let object = transform.target;

        let height = object.height;
        let width = object.width;

        let height1 = object._objects[0].height;
        let width1 = object._objects[0].width;

        object.set('height', width);
        object.set('width', height);

        object._objects[0].set('height', width1);
        object._objects[0].set('width', height1);

        canvas.renderAll();
        guardarDistribucion();

    }
}