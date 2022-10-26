import {EventoFactory} from "./factories/evento/EventoFactory.js";
import {MesaFactory} from "./factories/mesa/MesaFactory.js";
import {
    anyadirMesaToCanvas,
    deleteObject,
    getObjectFromCanvas,
    setMesaActiveObject,
    updateMesaOnCanvas
} from "./distribucion.js";

export let rowColorAdded = 'greenyellow';
export let rowColorNotAdded = 'LightYellow';
export let mesasDt;

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

function onAddRow(datatable, rowdata, success, error){
    toggleLoadingSpinner($("#addRowBtn"));

    rowdata.eventoId = evento.id;
    delete rowdata.id;

    addMesaAjax(rowdata, success, error);
}

function addMesaAjax(mesaRowData, success){
    let mesaObject = MesaFactory.crearMesa(mesaRowData.id, mesaRowData.eventoId, mesaRowData.numero, {mayores: mesaRowData.mayores, ninyos: mesaRowData.ninyos}, mesaRowData.descripcion);

    ajaxCall("POST", "/evento/mesas/add", {eventoId: evento.id}, JSON.stringify(mesaObject), function (mesa) {
        success(mesaObject.getDataTableRowData());
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

    let mesaObject = MesaFactory.crearMesa(rowdata[0].id, rowdata[0].eventoId, rowdata[0].numero, {mayores: rowdata[0].mayores, ninyos: rowdata[0].ninyos}, rowdata[0].descripcion);

    ajaxCall("POST", "/evento/mesas/delete", {}, JSON.stringify(mesaObject), function (mesa) {
        success(mesaObject.getDataTableRowData());
        deleteObject(getObjectFromCanvas(mesa));
    });
}

function onEditRow(datatable, rowdata, success){
    toggleLoadingSpinner($("#editRowBtn"));

    let mesaObject = MesaFactory.crearMesa(rowdata.id, rowdata.eventoId, rowdata.numero, {mayores: rowdata.mayores, ninyos: rowdata.ninyos}, rowdata.descripcion);

    ajaxCall("POST", "/evento/mesas/update", {}, JSON.stringify(mesaObject), function (mesa) {
        success(mesaObject.getDataTableRowData());
        updateMesaOnCanvas(mesa);
    });
}

export function cerrarInvitadosClicked(invitadosMayores, invitadosNinyos){
    let mesaSeleccionada = mesasDt.rows({ selected: true }).data()[0];

    let mesaObject = MesaFactory.crearMesa(mesaSeleccionada.id, mesaSeleccionada.eventoId, mesaSeleccionada.numero, {mayores: invitadosMayores, ninyos: invitadosNinyos}, mesaSeleccionada.descripcion);

    ajaxCall("POST", "/evento/mesas/update", {}, JSON.stringify(mesaObject), function () {
        mesasDt.row({ selected: true }).data(mesaObject.getDataTableRowData());
        mesasDt.draw();

        updateMesaOnCanvas(mesaObject);
    });
}

function exportarListadoClicked(){
    window.location = "/evento/mesas/generarListado?eventoId=" + evento.id;
}