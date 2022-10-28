import {Mesa} from "./Mesa.js";
import {changeRowColor, rowColorNotAdded} from "../../mesas.js";

export class MesaCanvas extends Mesa{
    constructor(id, eventoId, numero, personas, descripcion, top, left) {
        super(id, eventoId, numero, personas, descripcion);
        this.top = top;
        this.left = left;
    }

    addToCanvas(canvas, success) {
        throw new Error('Method "addToCanvas()" must be implemented.');
    }

    insertTextToObject(mesaId, numero, personas, top, left, object, canvas, success) {
        let text;

        if (personas.ninyos > 0) {
            text = new fabric.Text("T-" + numero + "\n" + personas.mayores + "p" + "\n" + personas.ninyos + "x", {
                fontSize: 12,
                originX: 'center',
                originY: 'center'
            });
        }
        else{
            text = new fabric.Text("T-" + numero + "\n" + personas.mayores + "p", {
                fontSize: 12,
                originX: 'center',
                originY: 'center'
            });
        }


        let group = new fabric.Group([ object, text ], {
            left: left,
            top: top,
            hasRotatingPoint: false
        });

        group.setControlsVisibility({
            tl: false,
            tr: false,
            br: false,
            bl: false,
            ml: false,
            mt: false,
            mr: false,
            mb: false,
            mtr: false
        });

        group['mesaId'] = mesaId;
        group['numero'] = numero;
        group['mayores'] = personas.mayores;
        group['ninyos'] = personas.ninyos;

        this.addObjectToCanvas(group, canvas, success);
    }

    addObjectToCanvas(object, canvas, success){
        canvas.add(object);
        canvas.renderAll();

        success(object);
    }

    reverse(eventData, transform){
        // TODO: Use get object from canvas to get object
        let object = transform.target;

        let height = object.height;
        let width = object.width;

        let height1 = object._objects[0].height;
        let width1 = object._objects[0].width;

        let tipo = object._objects[0].type;

        // TODO: Move to Mesa class
        if(tipo === 'rect'){
            object.set('height', width);
            object.set('width', height);

            object._objects[0].set('height', width1);
            object._objects[0].set('width', height1);

            canvas.renderAll();
            guardarDistribucion();
        }
    }

    delete(canvas){
        // TODO: Use get object from canvas to get object
        canvas.remove(object);
        canvas.renderAll();
        // TODO: Add success function
        guardarDistribucion();
        changeRowColor(object.mesaId, rowColorNotAdded);
    }

    setActive(canvas) {
        let object = this.getObjectFromCanvas(canvas);
        canvas.setActiveObject(object).requestRenderAll();
    }

    getObjectFromCanvas(canvas) {
        let res;
        canvas.getObjects().forEach(function(object) {
            if (object.mesaId === this.id){
                res = object;
            }
        });
        return res;
    }
}