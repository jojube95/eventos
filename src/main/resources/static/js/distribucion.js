import {
    rowColorNotAdded,
    rowColorAdded,
    changeRowColor,
    mesasDt,
    showTipoMesaModalContent,
    setAddedColorToMesaAdded
} from "./mesas.js";
import {MesaFactory} from "./factories/mesa/MesaFactory.js";

let deleteIcon = "data:image/svg+xml,%3C%3Fxml version='1.0' encoding='utf-8'%3F%3E%3C!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'%3E%3Csvg version='1.1' id='Ebene_1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='595.275px' height='595.275px' viewBox='200 215 230 470' xml:space='preserve'%3E%3Ccircle style='fill:%23F44336;' cx='299.76' cy='439.067' r='218.516'/%3E%3Cg%3E%3Crect x='267.162' y='307.978' transform='matrix(0.7071 -0.7071 0.7071 0.7071 -222.6202 340.6915)' style='fill:white;' width='65.545' height='262.18'/%3E%3Crect x='266.988' y='308.153' transform='matrix(0.7071 0.7071 -0.7071 0.7071 398.3889 -83.3116)' style='fill:white;' width='65.544' height='262.179'/%3E%3C/g%3E%3C/svg%3E";
let reverseIcon = "data:image/png;base64,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";

let imgDelete = document.createElement('img');
let imgReverse = document.createElement('img');

imgDelete.src = deleteIcon;
imgReverse.src = reverseIcon;

export let canvas;

$(document).ready(function() {
    canvas = new fabric.Canvas('canvas');

    fabric.Object.prototype.controls.deleteControl = new fabric.Control({
        x: 0.5,
        y: -0.5,
        offsetY: 15,
        cursorStyle: 'pointer',
        mouseUpHandler: clickDeleteIcon,
        render: function (ctx, left, top, styleOverride, fabricObject) {
            renderIcon(this, ctx, left, top, styleOverride, fabricObject, imgDelete);
        },
        cornerSize: 24
    });

    fabric.Object.prototype.controls.reverseControl = new fabric.Control({
        x: 0.5,
        y: -0.5,
        offsetY: 45,
        cursorStyle: 'pointer',
        mouseUpHandler: clickReverseIcon,
        render: function (ctx, left, top, styleOverride, fabricObject) {
            renderIcon(this, ctx, left, top, styleOverride, fabricObject, imgReverse);
        },
        cornerSize: 24
    });

    onCanvasObjectClick();

    onCanvasObjectDoubleClick();

    loadCanvas();

    $('#guardarButton').on('click', function () {
        guardarClicked();
    });

    $('#exportarDistribucionButton').on('click', function () {
        exportarDistribucionClicked();
    });
});

export function createMesaCanvas(id, eventoId, numero, mayores, ninyos, descripcion, tipo) {
    let mesa = MesaFactory.crearMesaCanvas(id, eventoId, numero, {mayores: mayores, ninyos: ninyos}, descripcion, tipo, 100, 100);
    mesa.addToCanvas(canvas, successAddMesaToCanvas);
}


export function onAnyadirMesaToCanvas(mesa){
    if (mesa.personasCabenEnRedonda()) {
        showTipoMesaModalContent(mesa);
    }
    else{
        let mesaCanvas = MesaFactory.crearMesaCanvas(mesa.id, mesa.eventoId, mesa.numero, mesa.personas, mesa.descripcion, 'larga', 100, 100);
        mesaCanvas.addToCanvas(canvas, successAddMesaToCanvas);
    }
    $('#distribucionTipoMesaModal').modal("hide");
    setAddedColorToMesaAdded();
}

export function updateMesaOnCanvas(mesa){
    canvas.getObjects().forEach(function(object) {
        if (object.mesaId === mesa.id){
            let mesaCanvas = MesaFactory.crearMesaCanvasByCanvasObject(object);
            mesaCanvas.personas = mesa.personas;
            mesaCanvas.numero = mesa.numero;

            mesaCanvas.update(canvas, function() {
                guardarDistribucion();
            });
        }
    });
}

function changeTipoMesa(object){
    object._objects[0].type = object._objects[0].type === 'rect' ? 'circle' : 'rect';

    let mesaCanvas = MesaFactory.crearMesaCanvasByCanvasObject(object);

    mesaCanvas.update(canvas, function() {
        guardarDistribucion();
    });
}

function loadCanvas(){
    canvas.loadFromJSON(evento.distribucion.mapa,function(){
        canvas.renderAll.bind(canvas);

        let objects = canvas.getObjects();

        objects.forEach(function(object) {
            changeRowColor(object.mesaId, rowColorAdded);

            object.setControlsVisibility({
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
        });
    });
    loadBackgroundImage();

    $('tbody > tr').not('[style]').css('background-color', rowColorNotAdded);
}

function loadBackgroundImage(){
    canvas.setBackgroundImage(null, function(){
        fabric.Image.fromURL("./../images/" + sala + "Background.png", (img) => {
            canvas.setBackgroundImage(img);
            canvas.renderAll();
        });
    });
}

function exportarDistribucionClicked(){
    let imgData = canvas.toDataURL({format: "image/jpeg", multiplier: 1 });


    let pdf;

    console.log(sala);

    if (sala === 'Sala1') {
        pdf = new jsPDF('portrait');
    }
    else{
        pdf = new jsPDF('landscape');
    }

    pdf.addImage(imgData, 'JPEG', 0, 0);
    pdf.save("distribució.pdf");
}

export function guardarDistribucion(success){
    let json = canvas.toJSON(['mesaId', 'eventoId', 'numero', 'mayores', 'ninyos']);

    ajaxCall("POST", "/evento/distribucion/guardar", {eventoId: evento.id}, JSON.stringify(json), success);
}

function guardarClicked(){
    let guardarButton = $("#guardarButton");
    toggleLoadingSpinner(guardarButton);

    guardarDistribucion(function () {
        toggleLoadingSpinner(guardarButton);
    });
}

function onCanvasObjectClick(){
    fabric.util.addListener(canvas.upperCanvasEl, 'click', function (e) {
        let object = canvas.findTarget(e);

        if (object !== undefined){
            selectRow(object.mesaId);
        }
    });
}

function selectRow(mesaId) {
    let index = $('tr[mesaId="' + mesaId + '"]').index();
    mesasDt.row(':eq(' + index + ')').select();
}

function onCanvasObjectDoubleClick(){
    fabric.util.addListener(canvas.upperCanvasEl, 'dblclick', function (e) {
        let mesa = canvas.findTarget(e);
        changeTipoMesa(mesa);
    });
}

function clickDeleteIcon(eventData, transform) {
    let object = transform.target;

    let mesaCanvas = MesaFactory.crearMesaCanvasByCanvasObject(object);

    mesaCanvas.delete(canvas, function() {
        guardarDistribucion();
        changeRowColor(object.mesaId, rowColorNotAdded);
    });
}

function clickReverseIcon(eventData, transform){
    let object = transform.target;

    let mesaCanvas = MesaFactory.crearMesaCanvasByCanvasObject(object);

    mesaCanvas.reverse(canvas, function() {
        guardarDistribucion()
    });
}

function renderIcon(object, ctx, left, top, styleOverride, fabricObject, image) {
    let size = object.cornerSize;
    ctx.save();
    ctx.translate(left, top);
    ctx.rotate(fabric.util.degreesToRadians(fabricObject.angle));
    ctx.drawImage(image, -size/2, -size/2, size, size);
    ctx.restore();
}

function successAddMesaToCanvas(object) {
    guardarDistribucion();
    changeRowColor(object.mesaId, rowColorAdded);
}