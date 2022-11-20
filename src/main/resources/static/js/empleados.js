let empleadosDt;

document.addEventListener('DOMContentLoaded', function() {
    empleadosDt = $('#empleados').DataTable(
        {
            columnDefs: [
                {
                    visible: false,
                    targets: 0
                },
                {
                    orderable: false,
                    targets: [2, 3, 5, 6, 7]
                },
                { className: "dt-right", "targets": [ 5, 6, 7 ] }
            ],
            searching: false,
            paging: false,
            info: false
        }
    );
});

function modificarClicked(empleadoId){
    location.href = "/empleadoUpdate?empleadoId=" + empleadoId;
}

function historialClicked(empleadoId){
    location.href = "/empleadoHistorial?empleadoId=" + empleadoId;
}

function deshabilitarClicked(empleadoId){
    $('#confirmarDeshabilitarModal').modal('toggle');

    $('#modalDeshabilitarButton').on('click', function(){
        toggleLoadingSpinner($(this));

        ajaxCall("POST", "/deshabilitarEmpleado", {empleadoId: empleadoId}, {}, function () {
            $('#confirmarDeshabilitarModal').modal('toggle');
            deleteEmpleadoRow(empleadoId);
        });

    })
}

function deleteEmpleadoRow(empleadoId) {
    let deletedRow = $('button[empleadoid="' + empleadoId + '"]').parent().parent();
    empleadosDt.row(deletedRow).remove().draw();
    empleadosDt.draw();
}