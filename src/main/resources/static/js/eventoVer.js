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
    ajaxCall("GET", "/evento/calcularPersonas", {eventoId: eventoId}, {}, calcularPersonasClickedCallback);
}

function calcularPersonasClickedCallback (data) {
    $("#confirmPersonasModalHolder").html(data);
    $("#confirmPersonasModal").modal("show");
}

function confirmarClicked(eventoId, mayores, ninyos){
    let personas = {mayores: mayores, ninyos: ninyos};

    ajaxCall("POST", "/evento/updatePersonas", {eventoId: eventoId}, JSON.stringify(personas), function () {
        confirmarClickedCallback(personas)
    });
}

function confirmarClickedCallback(personas) {
    $('#eventoPersonas').text(personas.mayores);
    $('#eventoNinyos').text(personas.ninyos);
}