import {EventoFactory} from "./factories/eventoFactory.js";
import {
    anyadirMesaToCanvas,
    deleteObject,
    getObjectFromCanvas,
    setMesaActiveObject,
    updateMesaOnCanvas
} from "./distribucion.js";

export let rowColorAdded = 'greenyellow';
export let rowColorNotAdded = 'LightYellow';
let mesasDt;

$(document).ready(function() {
    let eventoObject = EventoFactory.crearEvento(evento.id, evento.tipo, evento.titulo, evento.personas, evento.fecha);

    mesasDt = eventoObject.initMesasTable($('#mesas'), onAddRow, onEditRow, onDeleteRow);

    onAddClicked();

    let mesasTbody = $('#mesas tbody');

    mesasTbody.on('dblclick', 'tr', function () {
        let mesa = mesasDt.row( this ).data();
        if(getObjectFromCanvas(mesa) === undefined) {
            anyadirClicked(mesa.id, mesa.numero, mesa.mayores, mesa.ninyos, this);
        }
    } );

    mesasTbody.on('click', 'tr', function () {
        let mesa = mesasDt.row( this ).data();
        setMesaActiveObject(mesa);
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

function onAddRow(datatable, rowdata, success, error){
    toggleLoadingSpinner($("#addRowBtn"));

    rowdata.eventoId = evento.id;
    delete rowdata.id;

    addMesaAjax(rowdata, success, error);
}

function addMesaAjax(mesaRowData, success){
    ajaxCall("POST", "/evento/mesas/add", {eventoId: evento.id}, JSON.stringify(insertPersonas(mesaRowData)), function (mesa) {
        success(extractPersonas(mesa));
        let params = {mesaId: mesa.id, numero: mesa.numero, mayores: mesa.mayores, ninyos: mesa.ninyos};
        ajaxCall("GET", "/evento/distribucion/tipoMesaModal", params, {}, function (data) {
            anyadirMesaToCanvas(mesa.id, mesa.numero, mesa.mayores, mesa.ninyos, 150, 100, data);
        });
    });
}

function anyadirClicked(mesaId, numero, mayores, ninyos){
    let params = {mesaId: mesaId, numero: numero, mayores: mayores, ninyos: ninyos};
    ajaxCall("GET", "/evento/distribucion/tipoMesaModal", params, {}, function (data) {
        anyadirMesaToCanvas(mesaId, numero, mayores, ninyos, 150, 100, data);
    });
}

export function changeRowColor(mesaId, color){
    $('tr[mesaId="' + mesaId + '"]').css('background-color', color);
}

function onDeleteRow(datatable, rowdata, success){
    toggleLoadingSpinner($("#deleteRowBtn"));

    ajaxCall("POST", "/evento/mesas/delete", {}, JSON.stringify(insertPersonas(rowdata[0])), function (mesa) {
        success(extractPersonas(mesa));
        deleteObject(getObjectFromCanvas(mesa));
    });
}

function onEditRow(datatable, rowdata, success){
    toggleLoadingSpinner($("#editRowBtn"));

    ajaxCall("POST", "/evento/mesas/update", {}, JSON.stringify(insertPersonas(rowdata)), function (mesa) {
        success(extractPersonas(mesa));
        updateMesaOnCanvas(mesa);
    });
}

export function cerrarInvitadosClicked(invitadosMayores, invitadosNinyos){
    let mesaSeleccionada = mesasDt.rows({ selected: true }).data()[0];
    mesaSeleccionada.mayores = invitadosMayores.toString();
    mesaSeleccionada.ninyos = invitadosNinyos.toString();

    ajaxCall("POST", "/evento/mesas/update", {}, JSON.stringify(insertPersonas(mesaSeleccionada)), function () {
        mesasDt.row({ selected: true }).data(extractPersonas(mesaSeleccionada));
        mesasDt.draw();

        updateMesaOnCanvas(mesaSeleccionada);
    });
}

function exportarListadoClicked(){
    window.location = "/evento/mesas/generarListado?eventoId=" + evento.id;
}

// TODO: Solved with object Mesa
function extractPersonas(mesa) {
    mesa.mayores = mesa.personas.mayores;
    mesa.ninyos = mesa.personas.ninyos;
    delete mesa.personas;

    return mesa;
}

// TODO: Solved with object Mesa
function insertPersonas(mesa) {
    let personas = {'mayores': mesa.mayores, 'ninyos': mesa.ninyos}
    delete mesa.personas;
    delete mesa.ninyos;
    mesa.personas = personas;

    return mesa;
}