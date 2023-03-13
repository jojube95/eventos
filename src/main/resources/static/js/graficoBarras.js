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
        legend: { position: 'top', maxLines: 3 },
        bar: { groupWidth: '75%' },
        isStacked: true,
        series: getColorsEventTipe(data)
    };

    let chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}

function prepareData(year, dato) {
    let groups = eventos.reduce(function (res, act) {
        let date = new Date(act.fecha);
        if(new Date(act.fecha).getFullYear() === Number(year)) {
            let m = date.getMonth() + 1;
            let tipo = act.tipo.value;
            let valor;
            if(dato === 'comensales'){
                valor = act.personas.mayores;
            }
            else{
                valor = Number(act.personas.mayores) * Number(act.precioMenu) * ratioBeneficios;
            }
            if(res[m]) {
                if(res[m].hasOwnProperty(tipo)) {
                    res[m][tipo] += valor;
                }
                else {
                    res[m][tipo] = valor;
                }
            }
            else{
                res[m] = {group: String(m), [tipo]: valor};
            }
        }
        return res;
    }, {});

    let headerRow = tiposEventos.map(function (value) {
        return value.value
    });

    let filas = [];
    Object.keys(groups).forEach(key => {
        let comensalesTipo = [];

        headerRow.forEach(function (value, index) {
            if (groups[key][value]) {
                comensalesTipo[index] = groups[key][value];
            }
            else{
                comensalesTipo[index] = 0;
            }

        });
        filas.push([key].concat(comensalesTipo));
    });

    filas.unshift(['meses'].concat(headerRow));

    return google.visualization.arrayToDataTable(filas);
}

function getColorsEventTipe(data) {

    let headers = [];
    for (let i = 1; i < data.getNumberOfColumns(); i++) {
        headers.push(data.getColumnLabel(i));
    }

    return headers.reduce(function (res, act, index) {
        let evento = EventoFactory.crearEvento(null, {value: act}, null, null, null);

        res[index] = {color: evento.getCalendarioColor()};

        return res;
    }, {});
}