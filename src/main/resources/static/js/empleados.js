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
    console.log(empleadoId);
    location.href = "/empleadoUpdate?empleadoId=" + empleadoId;
}

function historialClicked(empleadoId){
    location.href = "/empleadoHistorial?empleadoId=" + empleadoId;
}

function eliminarClicked(empleadoId){
    $('#confirmarEliminarModal').modal('toggle');

    $('#modalEliminarButton').on('click', function(){
        toggleLoadingSpinner($(this));
        $.ajax({
            type: "POST",
            url: "/eliminarEmpleado?empleadoId=" + empleadoId,
            dataType: 'json',
            success: function () {
                $('#confirmarEliminarModal').modal('toggle');
                let deletedRow = $('button[empleadoid="' + empleadoId + '"]').parent().parent();
                empleadosDt.row(deletedRow).remove().draw();
                empleadosDt.draw();
            }
        });
    })
}