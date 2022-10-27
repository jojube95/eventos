import {Mesa} from "./Mesa.js";
import {changeRowColor, rowColorAdded} from "../../mesas";


export class MesaLarga extends Mesa {
    constructor(id, eventoId, numero, personas, descripcion, top, left) {
        super(id, eventoId, numero, personas, descripcion);
        this.top = top;
        this.left = left;
    }

    addToCanvas(canvas) {
        // TODO: Move to calcularLongitudMesaLarga on Mesa class
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

        // TODO: Move to insertTextToObject on Mesa class
        this.insertTextToObject(mesaId, numero, mayores, ninyos, top, left, rect, canvas);
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
}