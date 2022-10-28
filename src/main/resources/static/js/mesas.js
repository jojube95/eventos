import {EventoFactory} from "./factories/evento/EventoFactory.js";
import {MesaFactory} from "./factories/mesa/MesaFactory.js";
import {
    anyadirMesaToCanvas,
    createMesaCanvas,
    deleteObject,
    updateMesaOnCanvas
} from "./distribucion.js";

window.createMesaCanvas = createMesaCanvas;

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
        // TODO: Create mesaCanvas object and call getObjectFromCanvas
        if(getObjectFromCanvas(mesa) === undefined) {
            let mesaObject = MesaFactory.crearMesa(mesa.id, mesa.eventoId, mesa.numero, {mayores: mesa.mayores, ninyos: mesa.ninyos}, mesa.descripcion, mesa.representante, mesa.pagado);
            showTipoMesaModalContent(mesaObject);
        }
    } );

    mesasTbody.on('click', 'tr', function () {
        let mesa = mesasDt.row( this ).data();
        // TODO: Create mesaCanvas object and call setActive
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

    let mesaObject = MesaFactory.crearMesa('', evento.id, rowdata.numero, {mayores: rowdata.mayores, ninyos: rowdata.ninyos}, rowdata.descripcion, rowdata.representante, rowdata.pagado);

    addMesaAjax(mesaObject, success, error);
}

function addMesaAjax(mesaObject, success){
    ajaxCall("POST", "/evento/mesas/add", {eventoId: evento.id}, JSON.stringify(mesaObject), function (mesa) {
        mesaObject.id = mesa.id;
        success(mesaObject.getDataTableRowData());

        anyadirMesaToCanvas(mesaObject);
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