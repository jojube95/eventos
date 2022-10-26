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

    generarTitulo(evento);

    $("#tipo, #horario").change(function() {
        evento = EventoFactory.crearEvento('', {value: tipoSelect.val()}, '', '', '');
        generarTitulo(evento);
        rellenarPrecioNinyos(tipoSelect.val());
    });

    $("#titulo").change(function() {
        anyadirHorario();
    });

    if(window.location.href.includes("eventoAnyadir")){
        rellenarPrecioNinyos(tipoSelect.val());
    }
});

function generarTitulo(evento){
    let tituloInput = $('#titulo');
    let selectedTipo = $("#tipo option:selected").text();
    let selectedHorario = $("#horario option:selected").text();

    evento.generarTitulo(tituloInput, selectedTipo, selectedHorario);
}

function anyadirHorario(){
    let tituloInput = $('#titulo');
    let selectedHorario = $("#horario option:selected").text();

    tituloInput.val(tituloInput.val() + '-' + selectedHorario);
}

function rellenarPrecioNinyos(selectedTipo){
    let precioMenuNinyosInput = $('#precioMenuNinyos');

    if(selectedTipo === "boda" || selectedTipo === "comunion"){
        precioMenuNinyosInput.val(precioNinyosBodaComunion);
    }
    else{
        precioMenuNinyosInput.val(precioNinyosOtros);
    }
}