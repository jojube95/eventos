import {Mesa} from "./Mesa.js";
import {changeRowColor, rowColorAdded} from "../../mesas";


export class MesaRedonda extends Mesa {
    constructor(id, eventoId, numero, personas, descripcion, top, left, canvas) {
        super(id, eventoId, numero, personas, descripcion);
        this.top = top;
        this.left = left;
    }

    addToCanvas(canvas) {
        let circle = new fabric.Circle({
            radius: 36,
            fill : 'white',
            stroke: 'black',
            strokeWidth: 1,
            originX: 'center',
            originY: 'center'
        });

        this.insertTextToObject(mesaId, numero, mayores, ninyos, top, left, circle, canvas);
    }

    // TODO: If duplicated with MesaLarga calss, create MesaCanvas that extends from Mesa and extend from this Redonda y larga. Make abstract method ass necessari
    insertTextToObject(mesaId, numero, mayores, ninyos, top, left, objectToInsert, canvas) {
        let text;

        if (ninyos > 0) {
            text = new fabric.Text("T-" + numero + "\n" + mayores + "p" + "\n" + ninyos + "x", {
                fontSize: 12,
                originX: 'center',
                originY: 'center'
            });
        }
        else{
            text = new fabric.Text("T-" + numero + "\n" + mayores + "p", {
                fontSize: 12,
                originX: 'center',
                originY: 'center'
            });
        }


        let group = new fabric.Group([ objectToInsert, text ], {
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
        group['mayores'] = mayores;
        group['ninyos'] = ninyos;

        this.addObjectToCanvas(group, canvas);
    }

    addObjectToCanvas(object, canvas){
        canvas.add(object);
        canvas.renderAll();

        // TODO: Move this to success function
        guardarDistribucion();
        changeRowColor(object.mesaId, rowColorAdded);
        // TODO: Move this to success function
    }
}