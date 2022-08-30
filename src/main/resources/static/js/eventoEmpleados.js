let eventoEmpleadosDt;

$(document).ready(function() {
    eventoEmpleadosDt = $('#eventoEmpleados').DataTable({
        "sPaginationType": "full_numbers",
        columnDefs: [
            { visible: false, targets: [0, 1, 2, 3] },
        ],
        dom: 'Bfrtip',
        select: 'single',
        responsive: true,
        paging: false,
        info: false,
        altEditor: true,
        footerCallback: function () {
            let api = this.api();

            let totalPersonas = api
                .column(3, {search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return Number(a) + Number(b);
                }, 0);

            // Update footer
            $(api.column(3).footer()).html("Total: " + totalPersonas);
        }
    });
    updateProgresbar();
});

function anyadirClicked (){
    let empleadoId = $('#empleado option').filter(':selected').val();
    $.ajax({
        type: "POST",
        url: "/evento/empleados/anyadir?eventoId=" + idEvento + "&empleadoId=" + empleadoId,
        dataType: 'json',
        success: function (data) {
            eventoEmpleadosDt.row.add( [
                data.id,
                data.idEvento,
                data.idEmpleado,
                data.tipo,
                data.nombre,
                '<div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input"' + (data.fijo ? 'checked="checked"' : '') + '><label class="custom-control-label">' + (data.fijo ? 'Si' : 'No') + '</label></div>',
                '<div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input"' + (data.confirmado ? 'checked="checked"' : '') + '><label class="custom-control-label">' + (data.confirmado ? 'Si' : 'No') + '</label></div>',
                data.horasExtras,
                '<button type="button" class="btn btn-primary" eventoempleadoid="' + data.id + '" onclick="modificarClicked(this.getAttribute(\'eventoEmpleadoId\'))">Modificar</button>',
                '<button type="button" class="btn btn-danger" eventoempleadoid="' + data.id + '" onclick="eliminarClicked(this.getAttribute(\'eventoEmpleadoId\'))">Eliminar</button>'
            ] ).draw();
            updateProgresbar();
        }
    })
}

function eliminarClicked(eventoEmpleadoId){
    $.ajax({
        type: "POST",
        url: "/evento/empleados/eliminar?eventoEmpleadoId=" + eventoEmpleadoId,
        dataType: 'json',
        success: function () {
            let deletedRow = $('button[eventoempleadoid="' + eventoEmpleadoId + '"]').parent().parent();
            eventoEmpleadosDt.row(deletedRow).remove().draw();
            updateProgresbar();
        }
    });
}

function modificarClicked(eventoEmpleadoId){
    $.ajax({
        url: "/evento/empleados/modificar?eventoEmpleadoId=" + eventoEmpleadoId,
        success: function (data) {
            $("#eventoEmpleadoModificarModalHolder").html(data);
            $("#eventoEmpleadoModificarModal").modal("show");
        }
    })
}

function modificarModalClicked(eventoEmpleadoId){
    let confirmado = $('#confirmado option').filter(':selected').val();
    let horasExtras = $('#horasExtras').val();

    $.ajax({
        type: "POST",
        url: "/evento/empleados/modificar?eventoEmpleadoId=" + eventoEmpleadoId + "&confirmado=" + confirmado + "&horasExtras=" + horasExtras,
        success: function (data) {
            let modifiedRow = $('button[eventoempleadoid="' + eventoEmpleadoId + '"]').parent().parent();

            let checkboxConfirmado= modifiedRow.children('td').eq(2).children('div').children('input');
            let labelConfirmado = modifiedRow.children('td').eq(2).children('div').children('label');
            let columnHorasExtra = modifiedRow.children('td').eq(3);

            data.confirmado ? checkboxConfirmado.attr('checked', 'checked') : checkboxConfirmado.prop('checked', 'checked');
            data.confirmado ? labelConfirmado.text('Si') : labelConfirmado.text('No');
            columnHorasExtra.text(data.horasExtras);

            $("#eventoEmpleadoModificarModal").modal("hide");
            eventoEmpleadosDt.draw();
            updateProgresbar();
        }
    });
}

function updateProgresbar(){
    let camarerosConfirmados = $('#eventoEmpleados > tbody > tr > td:nth-child(3) > div > input[checked="checked"]').length;
    let camarerosNoConfirmados = $('#eventoEmpleados > tbody > tr > td:nth-child(3) > div > input').length - camarerosConfirmados;

    console.log(camarerosConfirmados);
    console.log(camarerosNoConfirmados);

    let porcentageConfirmados = (camarerosConfirmados / camarerosRecomendados) * 100;
    let porcentageNoConfirmados = (camarerosNoConfirmados / camarerosRecomendados) * 100;

    console.log(porcentageConfirmados);
    console.log(porcentageNoConfirmados);

    $("#progressbarConfirmados").css("width", porcentageConfirmados + '%');
    $("#progressbarNoConfirmados").css("width", porcentageNoConfirmados + '%');
}



