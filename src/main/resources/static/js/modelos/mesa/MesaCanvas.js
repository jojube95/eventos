import {Mesa} from "./Mesa.js";

const FONT_SIZE = 12;

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
                fontSize: FONT_SIZE,
                originX: 'center',
                originY: 'center'
            });
        }
        else{
            text = new fabric.Text("T-" + numero + "\n" + personas.mayores + "p", {
                fontSize: FONT_SIZE,
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

    reverse(canvas, success){
        let object = this.getObjectFromCanvas(canvas);

        let height = object.height;
        let width = object.width;

        let height1 = object._objects[0].height;
        let width1 = object._objects[0].width;

        object.set('height', width);
        object.set('width', height);

        object._objects[0].set('height', width1);
        object._objects[0].set('width', height1);

        canvas.renderAll();

        success();
    }

    delete(canvas, success){
        let object = this.getObjectFromCanvas(canvas);
        canvas.remove(object);
        canvas.renderAll();

        success();
    }

    update(canvas) {
        throw new Error('Method "update()" must be implemented.');
    }

    setActive(canvas) {
        let object = this.getObjectFromCanvas(canvas);
        canvas.setActiveObject(object).requestRenderAll();
    }

    getObjectFromCanvas(canvas) {
        let self = this;
        let res;
        canvas.getObjects().forEach(function(object) {
            if (object.mesaId === self.id){
                res = object;
            }
        });
        return res;
    }
}