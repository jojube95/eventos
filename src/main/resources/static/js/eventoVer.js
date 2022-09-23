function modificarClicked(eventoId){
    location.href = "/eventoUpdate?eventoId=" + eventoId;
}

function protagonistasClicked(eventoId){
    location.href = "/evento/protagonistas?eventoId=" + eventoId;
}

function confirmarEliminar(eventoId){
    location.href = "/eliminarEvento?eventoId=" + eventoId;
}

function mesasClicked(eventoId){
    location.href = "/evento/mesas?eventoId=" + eventoId;
}

function empleadosClicked(eventoId){
    location.href = "/evento/empleados?eventoId=" + eventoId;
}

function calcularPersonasClicked(eventoId){
    $.ajax({
        url: "/evento/calcularPersonas?eventoId=" + eventoId,
        success: function (data) {
            $("#confirmPersonasModalHolder").html(data);
            $("#confirmPersonasModal").modal("show");
        }
    })
}

function confirmarClicked(eventoId, personas, ninyos){
    $.ajax({
        type: "POST",
        url: "/evento/updatePersonas?eventoId=" + eventoId + "&personas=" + personas + "&ninyos=" + ninyos,
        success: function () {
            $('#eventoPersonas').text(personas);
            $('#eventoNinyos').text(ninyos);
        },
        error: function (er) {
            if(er.status === 405){
                alert("No tiene permisos suficientes.")
            }
            else{
                alert("Error al hacer la petición.")
            }
        }
    })
}