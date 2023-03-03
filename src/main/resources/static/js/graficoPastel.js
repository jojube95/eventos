import {EventoFactory} from "./factories/evento/EventoFactory.js";

document.addEventListener('DOMContentLoaded', function() {
    $("#datepicker").datepicker({
        format: "yyyy",
        viewMode: "years",
        minViewMode: "years",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    }).change(function () {
        drawChart($(this).val(), $("#dato").val());
    }).val(new Date().getFullYear());

    $("#dato").change(function() {
        drawChart($("#datepicker").val(), $(this).val());
    });

    google.charts.load('current', {'packages':['corechart']});

    google.charts.setOnLoadCallback(function () {
        drawChart(2023, 'comensales')
    });
});

function drawChart(year, dato) {
    let data = prepareData(year, dato);

    let options = {
        width: 800,
        height: 600,
        colors: getColorsEventTipe(data)
    };

    let chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}

function prepareData(year, dato) {
    let groups = eventos.reduce(function (res, act) {
        if(new Date(act.fecha).getFullYear() === Number(year)) {
            let tipo = act.tipo.value;
            let valor;
            if(dato === 'comensales'){
                valor = act.personas.mayores;
            }
            else{
                valor = Number(act.personas.mayores) * Number(act.precioMenu) * ratioBeneficios;
            }

            if(res[tipo]) {
                res[tipo] += valor;
            }
            else{
                res[tipo] = valor;
            }
        }
        return res;
    }, {});

    let headerRow = ["Tipo", "Valor"];

    let filas = [];

    filas.push(headerRow);

    Object.keys(groups).forEach(key => {
        filas.push([key].concat(groups[key]));
    });

    return google.visualization.arrayToDataTable(filas);
}

function getColorsEventTipe(data) {

    let colors = [];

    for (let i = 0; i < data.getNumberOfRows(); i++) {
        let evento = EventoFactory.crearEvento(null, {value: data.getValue(i, 0)}, null, null, null);

        colors.push(evento.getCalendarioColor());
    }

    return colors;
}