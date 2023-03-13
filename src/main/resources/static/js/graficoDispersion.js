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
        drawChart($(this).val());
    }).val(new Date().getFullYear());

    google.charts.load('current', {'packages':['corechart']});

    google.charts.setOnLoadCallback(function () {
        drawChart(2023)
    });
});

function drawChart(year) {
    let data = prepareData(year);

    let options = {
        width: 800,
        height: 600,
        title: 'Comenslaes vs precio menú.',
        hAxis: {title: 'Precio Menú'},
        vAxis: {title: 'Comensales'},
        colors: getColorsEventTipe(data)
    };

    let chart = new google.visualization.BubbleChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}

function prepareData(year) {
    let headerRow = ['ID', 'Comensales', 'Precio Menú', 'Tipo', 'Beneficio'];

    let filas = [];

    filas.push(headerRow);

    eventos.forEach(evento => {
        if(new Date(evento.fecha).getFullYear() === Number(year)) {
            filas.push(['', evento.personas.mayores, evento.precioMenu, evento.tipo.value, (evento.personas.mayores * evento.precioMenu) * ratioBeneficios]);
        }
    });

    return google.visualization.arrayToDataTable(filas);
}

function getColorsEventTipe(data) {
    let colorMap = {};

    tiposEventos.forEach(function (tipoEvento) {
        let evento = EventoFactory.crearEvento(null, tipoEvento, null, null, null);

        colorMap[tipoEvento.value] = evento.getCalendarioColor();
    });

    let colors = [];

    for (let i = 0; i < data.getNumberOfRows(); i++) {
        let tipo = data.getValue(i, 3);
        if (colors.indexOf(colorMap[tipo]) === -1) {
            colors.push(colorMap[tipo]);
        }
    }

    return colors;
}