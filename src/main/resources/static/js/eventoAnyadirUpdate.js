$( document ).ready(function() {
    $('#fecha').datepicker({
        format: "yyyy-mm-dd",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    });

    generarTitulo();

    let tipoSelect = $("#tipo");

    tipoSelect.change(function() {
        rellenarPrecioNinyos($(this).val());
    });

    if(window.location.href.includes("eventoAnyadir")){
        rellenarPrecioNinyos(tipoSelect.val());
    }
});

function generarTitulo(){
    let tituloInput = $('#titulo');
    let selectedTipo = $("#tipo option:selected").text();
    let selectedHorario = $("#horario option:selected").text();

    if(tituloInput){
        if(selectedTipo === 'eventoComunal' || selectedTipo === 'eventoIndividual'){
            tituloInput.val('');
            tituloInput.prop('readonly', false);
        }
        else{
            tituloInput.val(selectedTipo + '-' + selectedHorario);
            tituloInput.prop('readonly', true);
        }
    }
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