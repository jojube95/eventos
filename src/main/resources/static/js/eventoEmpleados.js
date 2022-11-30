import {EventoEmpleadoFactory} from "./factories/eventoEmpleado/EventoEmpleadoFactory.js";

window.anyadirCamareroClicked = anyadirCamareroClicked;
window.anyadirCocineroClicked = anyadirCocineroClicked;
window.eliminarClicked = eliminarClicked;
window.modificarClicked = modificarClicked;
window.modificarModalClicked = modificarModalClicked;

let eventoEmpleadosCamarerosDt;
let eventoEmpleadosCocinerosDt;

let eventoEmpleadoDtParams = {
    "sPaginationType": "full_numbers",
    columnDefs: [
        { visible: false, targets: [0, 1, 2, 3] }
    ],
    dom: 'Bfrtip',
    select: 'single',
    responsive: true,
    paging: false,
    info: false,
    altEditor: true,
    footerCallback: function () {
        let api = this.api();
        let rows = api.rows({search:'applied'}).count();
        $(api.column(4).footer()).text("Total: " + rows);
    }
}
$(document).ready(function() {
    eventoEmpleadosCamarerosDt = $('#eventoEmpleadosCamareros').DataTable(eventoEmpleadoDtParams);

    eventoEmpleadosCocinerosDt = $('#eventoEmpleadosCocineros').DataTable(eventoEmpleadoDtParams);

    updateProgresbar('camarero');
    updateProgresbar('cocinero');
});

function anyadirCamareroClicked (){
    let anyadirButton = $('#buttonAnyadirCamarero');
    toggleLoadingSpinner(anyadirButton);

    let empleadoId = $('#empleadoCamarero option').filter(':selected').val();

    anyadirEmpleado(empleadoId);
}

function anyadirCocineroClicked (){
    let anyadirButton = $('#buttonAnyadirCocinero');
    toggleLoadingSpinner(anyadirButton);

    let empleadoId = $('#empleadoCocinero option').filter(':selected').val();

    anyadirEmpleado(empleadoId);
}

function anyadirEmpleado(empleadoId) {
    ajaxCall("POST", "/evento/empleados/anyadir", {eventoId: eventoId, empleadoId: empleadoId}, {}, function (data) {
        let eventoEmpleado = EventoEmpleadoFactory.crearEventoEmpleado(data.id, data.evento, data.empleado, data.tipoEmpleado, data.confirmado, data.horasExtras, eventoEmpleadosCamarerosDt, eventoEmpleadosCocinerosDt);
        eventoEmpleado.anyadirEnDatatable();
        updateProgresbar(eventoEmpleado.tipoEmpleado.value);
    });
}

function eliminarClicked(eventoEmpleadoId){
    toggleLoadingSpinner($('button[eventoempleadoid="' + eventoEmpleadoId + '"]'));

    ajaxCall("POST", "/evento/empleados/eliminar", {eventoEmpleadoId: eventoEmpleadoId}, {}, function (data) {
        let eventoEmpleado = EventoEmpleadoFactory.crearEventoEmpleado(data.id, data.evento, data.empleado, data.tipoEmpleado, data.confirmado, data.horasExtras, eventoEmpleadosCamarerosDt, eventoEmpleadosCocinerosDt);
        eventoEmpleado.eliminarEnDatatable();
        updateProgresbar(eventoEmpleado.tipoEmpleado.value);
    });
}

function modificarClicked(eventoEmpleadoId){
    ajaxCall("GET", "/evento/empleados/modificar", {eventoEmpleadoId: eventoEmpleadoId}, {}, function (data) {
        $("#eventoEmpleadoModificarModalHolder").html(data);
        $("#eventoEmpleadoModificarModal").modal("show");
    });
}

function modificarModalClicked(eventoEmpleadoId){
    toggleLoadingSpinner($('#modalEventoModificarButton'));

    let confirmado = $('#confirmado option').filter(':selected').val();
    let horasExtras = $('#horasExtras').val();

    ajaxCall("POST", "/evento/empleados/modificar", {eventoEmpleadoId: eventoEmpleadoId, confirmado: confirmado, horasExtras: horasExtras}, {}, function (data) {
        let eventoEmpleado = EventoEmpleadoFactory.crearEventoEmpleado(data.id, data.evento, data.empleado, data.tipoEmpleado, data.confirmado, data.horasExtras, eventoEmpleadosCamarerosDt, eventoEmpleadosCocinerosDt);
        eventoEmpleado.modificarEnDatatable();
        updateProgresbar(eventoEmpleado.tipoEmpleado.value);
    });
}

function updateProgresbar(tipoEmpleado){
    if (tipoEmpleado === 'camarero') {
        let camarerosConfirmados = $('#eventoEmpleadosCamareros > tbody > tr > td:nth-child(3) > div > input[checked="checked"]').length;
        let camarerosNoConfirmados = $('#eventoEmpleadosCamareros > tbody > tr > td:nth-child(3) > div > input').length - camarerosConfirmados;

        let porcentageConfirmados = (camarerosConfirmados / camarerosRecomendados) * 100;
        let porcentageNoConfirmados = (camarerosNoConfirmados / camarerosRecomendados) * 100;

        $("#progressbarCamarerosConfirmados").css("width", porcentageConfirmados + '%');
        $("#progressbarCamarerosNoConfirmados").css("width", porcentageNoConfirmados + '%');
    }
    else if(tipoEmpleado === 'cocinero') {
        let cocinerosConfirmados = $('#eventoEmpleadosCocineros > tbody > tr > td:nth-child(3) > div > input[checked="checked"]').length;
        let cocinerosNoConfirmados = $('#eventoEmpleadosCocineros > tbody > tr > td:nth-child(3) > div > input').length - cocinerosConfirmados;

        let porcentageConfirmados = (cocinerosConfirmados / cocinerosRecomendados) * 100;
        let porcentageNoConfirmados = (cocinerosNoConfirmados / cocinerosRecomendados) * 100;

        $("#progressbarCocinerosConfirmados").css("width", porcentageConfirmados + '%');
        $("#progressbarCocinerosNoConfirmados").css("width", porcentageNoConfirmados + '%');
    }
}