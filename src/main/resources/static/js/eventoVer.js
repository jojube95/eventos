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

function confirmarClicked(eventoId, mayores, ninyos){
    let personas = {mayores: mayores, ninyos: ninyos};

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/evento/updatePersonas?eventoId=" + eventoId,
        data: JSON.stringify(personas),
        dataType: 'json',
        success: function () {
            $('#eventoPersonas').text(personas.mayores);
            $('#eventoNinyos').text(personas.ninyos);
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