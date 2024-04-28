import {EventoFactory} from "./factories/evento/EventoFactory.js";

let evento;

$( document ).ready(function() {
    $('#fecha').datepicker({
        format: "yyyy-mm-dd",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    });

    let tipoSelect = $("#tipo");

    evento = EventoFactory.crearEvento('', {value: tipoSelect.val()}, '', '', '');

    $("#tipo, #horario").change(function() {
        evento = EventoFactory.crearEvento('', {value: tipoSelect.val()}, '', '', '');
        rellenarPrecioNinyos(tipoSelect.val());
    });

    if(window.location.href.includes("eventoAnyadir")){
        rellenarPrecioNinyos(tipoSelect.val());
    }
});

function rellenarPrecioNinyos(selectedTipo){
    let precioMenuNinyosInput = $('#precioMenuNinyos');

    if(selectedTipo === "boda" || selectedTipo === "comunion"){
        precioMenuNinyosInput.val(precioNinyosBodaComunion);
    }
    else{
        precioMenuNinyosInput.val(precioNinyosOtros);
    }
}