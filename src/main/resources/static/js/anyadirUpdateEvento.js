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

    if(window.location.href.includes("anyadirEvento")){
        rellenarPrecioNinyos(tipoSelect.val());
    }
});

function generarTitulo(){
    let tituloInput = $('#titulo');
    let selectedTipo = $('#tipo').val();
    let selectedHorario = $('#horario').val();

    if(tituloInput){
        if(selectedTipo === 'Evento comunal' || selectedTipo === 'Evento individual'){
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
    let selectedHorario = $('#horario').val();

    tituloInput.val(tituloInput.val() + '-' + selectedHorario);
}

function rellenarPrecioNinyos(selectedTipo){
    let precioMenuNinyosInput = $('#precioMenuNinyos');

    if(selectedTipo === "Boda" || selectedTipo === "Comunion"){
        precioMenuNinyosInput.val("25.0");
    }
    else{
        precioMenuNinyosInput.val("15.0");
    }
}