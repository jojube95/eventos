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

    updateProgresbar();
});

function anyadirClicked (){
    let anyadirButton = $('#buttonAnyadir');
    toggleLoadingSpinner(anyadirButton);

    let empleadoId = $('#empleado option').filter(':selected').val();

    ajaxCall("POST", "/evento/empleados/anyadir", {eventoId: eventoId, empleadoId: empleadoId}, {}, function (data) {
        // TODO: Obtener empleado con el data que devuelve el Ajax
        // TODO: Crear un empleado usando el EmpleadoFactory
        // TODO: Implementar las funciones addEventoEmpleadoRow(), toggleLoadingSpinner() en las clases EmpleadoCamarero, EmpleadoCocinero
        addEventoEmpleadoRow(data);
        updateProgresbar();
        toggleLoadingSpinner(anyadirButton);
    });
}

function eliminarClicked(eventoEmpleadoId){
    toggleLoadingSpinner($('button[eventoempleadoid="' + eventoEmpleadoId + '"]'));

    ajaxCall("POST", "/evento/empleados/eliminar", {eventoEmpleadoId: eventoEmpleadoId}, {}, function () {
        // TODO: Obtener empleado con el data que devuelve el Ajax
        // TODO: Crear un empleado usando el EmpleadoFactory
        // TODO: Implementar las funciones addEventoEmpleadoRow(), toggleLoadingSpinner() en las clases EmpleadoCamarero, EmpleadoCocinero
        deleteEventoEmpleadoRow(eventoEmpleadoId);
        updateProgresbar();
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
        // TODO: Obtener empleado con el data que devuelve el Ajax
        // TODO: Crear un empleado usando el EmpleadoFactory
        // TODO: Implementar las funciones addEventoEmpleadoRow(), toggleLoadingSpinner() en las clases EmpleadoCamarero, EmpleadoCocinero
        updateEventoEmpleadoRow(data);
        updateProgresbar();
    });
}

function addEventoEmpleadoRow(eventoEmpleado) {
    eventoEmpleadosDt.row.add( [
        eventoEmpleado.id,
        eventoEmpleado.evento.id,
        eventoEmpleado.empleado.id,
        eventoEmpleado.empleado.tipo.value,
        eventoEmpleado.empleado.persona.nombre,
        '<div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input"' + (eventoEmpleado.empleado.fijo ? 'checked="checked"' : '') + '><label class="custom-control-label">' + (eventoEmpleado.empleado.fijo ? 'Si' : 'No') + '</label></div>',
        '<div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input"' + (eventoEmpleado.confirmado ? 'checked="checked"' : '') + '><label class="custom-control-label">' + (eventoEmpleado.confirmado ? 'Si' : 'No') + '</label></div>',
        eventoEmpleado.horasExtras,
        '<button type="button" class="btn btn-primary" eventoempleadoid="' + eventoEmpleado.id + '" onclick="modificarClicked(this.getAttribute(\'eventoEmpleadoId\'))">Modificar</button>',
        '<button type="button" class="btn btn-danger" eventoempleadoid="' + eventoEmpleado.id + '" onclick="eliminarClicked(this.getAttribute(\'eventoEmpleadoId\'))">Eliminar</button>'
    ] ).draw();
}

function deleteEventoEmpleadoRow(eventoEmpleadoId) {
    let deletedRow = $('button[eventoempleadoid="' + eventoEmpleadoId + '"]').parent().parent();
    eventoEmpleadosDt.row(deletedRow).remove().draw();
}

function updateEventoEmpleadoRow(eventoEmpleado) {
    let modifiedRow = $('button[eventoempleadoid="' + eventoEmpleado.id + '"]').parent().parent();

    let checkboxConfirmado= modifiedRow.children('td').eq(2).children('div').children('input');
    let labelConfirmado = modifiedRow.children('td').eq(2).children('div').children('label');
    let columnHorasExtra = modifiedRow.children('td').eq(3);

    eventoEmpleado.confirmado ? checkboxConfirmado.attr('checked', 'checked') : checkboxConfirmado.removeAttr('checked');
    eventoEmpleado.confirmado ? labelConfirmado.text('Si') : labelConfirmado.text('No');
    columnHorasExtra.text(eventoEmpleado.horasExtras);

    $("#eventoEmpleadoModificarModal").modal("hide");
    eventoEmpleadosDt.draw();
}

function updateProgresbar(){
    // TODO: Add tipoEmpleado option
    let camarerosConfirmados = $('#eventoEmpleados > tbody > tr > td:nth-child(3) > div > input[checked="checked"]').length;
    let camarerosNoConfirmados = $('#eventoEmpleados > tbody > tr > td:nth-child(3) > div > input').length - camarerosConfirmados;

    let porcentageConfirmados = (camarerosConfirmados / camarerosRecomendados) * 100;
    let porcentageNoConfirmados = (camarerosNoConfirmados / camarerosRecomendados) * 100;

    $("#progressbarConfirmados").css("width", porcentageConfirmados + '%');
    $("#progressbarNoConfirmados").css("width", porcentageNoConfirmados + '%');
}