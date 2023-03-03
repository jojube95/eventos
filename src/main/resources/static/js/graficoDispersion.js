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
    });

    $("#datepicker").val(new Date().getFullYear());

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
        vAxis: {title: 'Comensales'}
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
            filas.push(['', evento.personas.mayores, evento.precioMenu, evento.tipo.value, (evento.personas.mayores * evento.precioMenu) * 0.325]);
        }
    });

    return google.visualization.arrayToDataTable(filas);
}