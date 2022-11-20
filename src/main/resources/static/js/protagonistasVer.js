document.addEventListener('DOMContentLoaded', function() {
    $('#protagonistas').DataTable({searching: false, paging: false, info: false});
});

function eliminarClicked(eventoId, protagonistaIndex){
    location.href = "/evento/protagonistas/eliminar?eventoId=" + eventoId + "&protagonistaIndex=" + protagonistaIndex;
}

function anyadirClicked(eventoId){
    location.href = "/evento/protagonistas/anyadir?eventoId=" + eventoId;
}