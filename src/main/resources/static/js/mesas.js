import {EventoFactory} from "./factories/evento/EventoFactory.js";
import {MesaFactory} from "./factories/mesa/MesaFactory.js";
import {
    onAnyadirMesaToCanvas, canvas,
    createMesaCanvas, guardarDistribucion
} from "./distribucion.js";

window.createMesaCanvas = createMesaCanvas;

export let rowColorAdded = 'greenyellow';
export let rowColorNotAdded = 'LightYellow';
export let mesasDt;

$(document).ready(function() {
    let eventoObject = EventoFactory.crearEvento(evento.id, evento.tipo, evento.titulo, evento.personas, evento.fecha);

    mesasDt = eventoObject.initMesasTable($('#mesas'), onAddRow, onDeleteRow);

    onAddClicked();

    let mesasTbody = $('#mesas tbody');

    mesasTbody.on('dblclick', 'tr', function () {
        let mesa = mesasDt.row( this ).data();

        let mesaCanvas = MesaFactory.crearMesaCanvas(mesa.id, mesa.eventoId, mesa.numero, {mayores: mesa.mayores, ninyos: mesa.ninyos}, mesa.descripcion, mesa.tipo, 100, 100);

        if(mesaCanvas.getObjectFromCanvas(canvas) === undefined) {
            let mesaObject = MesaFactory.crearMesa(mesa.id, mesa.eventoId, mesa.numero, {mayores: mesa.mayores, ninyos: mesa.ninyos}, mesa.descripcion, mesa.representante, mesa.pagado);
            onAnyadirMesaToCanvas(mesaObject);
        }
    } );

    mesasTbody.on('click', 'tr', function () {
        let mesa = mesasDt.row( this ).data();
        let mesaCanvas = MesaFactory.crearMesaCanvas(mesa.id, mesa.eventoId, mesa.numero, {mayores: mesa.mayores, ninyos: mesa.ninyos}, mesa.descripcion, mesa.tipo, 100, 100);
        mesaCanvas.setActive(canvas);
    } );

    $("#file-upload-form").on("submit", function (e) {
        toggleLoadingSpinner($("#importarDistribucionButton"));

        e.preventDefault();

        $.ajax({
            url: "/evento/mesas/uploadExcel?eventoId=" + evento.id,
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                toggleLoadingSpinner($("#importarDistribucionButton"));
                location.reload();
            },
            error: function (err) {
                console.error(err);
            }
        });
    });

    $('#exportarListadoButton').on('click', function () {
        exportarListadoClicked();
    });
});

function onAddClicked(){
    $('.dt-buttons > button:first-child').on( "click", function() {
        waitForElm('#numero').then(() => {
            let tableNumbers = $('#mesas > tbody > tr > td:first-child').get().map((element) => {
                let number = Number($(element).text());
                if(isNaN(number)) {
                    return 0
                }
                else{
                    return number;
                }
            });

            let nextTableNumber = Math.max.apply(Math, tableNumbers) + 1;

            $('#numero').val(nextTableNumber);
        });
    });
}

function onAddRow(datatable, rowdata, success){
    toggleLoadingSpinner($("#addRowBtn"));

    let mesaObject = MesaFactory.crearMesa(undefined, evento.id, rowdata.numero, {mayores: rowdata.mayores, ninyos: rowdata.ninyos}, rowdata.descripcion, rowdata.representante, rowdata.pagado);

    addMesaAjax(mesaObject, success);
}

function addMesaAjax(mesaObject, success){
    ajaxCall("POST", "/evento/mesas/add", {eventoId: evento.id}, JSON.stringify(mesaObject), function (mesa) {
        mesaObject.id = mesa.id;

        success(mesaObject.getDataTableRowData());

        onAnyadirMesaToCanvas(mesaObject);
    });
}

export function showTipoMesaModalContent(mesa) {
    let params = {mesaId: mesa.id, numero: mesa.numero, mayores: mesa.personas.mayores, ninyos: mesa.personas.ninyos};
    ajaxCall("GET", "/evento/distribucion/tipoMesaModal", params, {}, function (htmlModal) {
        $("#distribucionTipoMesaModalHolder").html(htmlModal);
        $('#distribucionTipoMesaModal').modal("show");
    });
}

export function setAddedColorToMesaAdded() {
    $('#mesas tbody tr:last').css('background-color', rowColorAdded);
}

export function changeRowColor(mesaId, color){
    $('tr[mesaId="' + mesaId + '"]').css('background-color', color);
}

function onDeleteRow(datatable, rowdata, success){
    toggleLoadingSpinner($("#deleteRowBtn"));

    let mesaObject = MesaFactory.crearMesa(rowdata[0].id, rowdata[0].eventoId, rowdata[0].numero, {mayores: rowdata[0].mayores, ninyos: rowdata[0].ninyos}, rowdata[0].descripcion);

    ajaxCall("POST", "/evento/mesas/delete", {}, JSON.stringify(mesaObject), function (mesa) {
        success(mesaObject.getDataTableRowData());
        let mesaCanvas = MesaFactory.crearMesaCanvas(mesa.id, mesa.eventoId, mesa.numero, {mayores: mesa.mayores, ninyos: mesa.ninyos}, mesa.descripcion, mesa.tipo);
        mesaCanvas.delete(canvas, function() {
            guardarDistribucion();
        });
    });
}

function exportarListadoClicked(){
    window.location = "/evento/mesas/generarListado?eventoId=" + evento.id;
}