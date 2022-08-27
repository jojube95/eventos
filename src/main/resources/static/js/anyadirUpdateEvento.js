function generarTitulo(){
    let tituloInput = $('#titulo');
    let selectedTipo = $('#tipo').val();
    let selectedHorario = $('#horario').val();

    if(selectedTipo === 'Evento comunal' || selectedTipo === 'Evento individual'){
        tituloInput.val('');
        tituloInput.prop('readonly', false);
    }
    else{
        tituloInput.val(selectedTipo + '-' + selectedHorario);
        tituloInput.prop('readonly', true);
    }
}

function anyadirHorario(){
    let tituloInput = $('#titulo');
    let selectedHorario = $('#horario').val();

    tituloInput.val(tituloInput.val() + '-' + selectedHorario);
}

$( document ).ready(function() {
    $('#fecha').datepicker({
        format: "yyyy-mm-dd",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    });

    generarTitulo();
});