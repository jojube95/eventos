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
    });

    $("#datepicker").val(new Date().getFullYear());

    $("#dato").change(function() {
        drawChart($("#datepicker").val(), $(this).val());
    });

    google.charts.load('current', {'packages':['corechart']});

    google.charts.setOnLoadCallback(function () {
        drawChart(2023, 'comensales')
    });
});

function drawChart(year, dato) {
    //let data = prepareData(year, dato);

    let data = google.visualization.arrayToDataTable([
        ['ID', 'Life Expectancy', 'Fertility Rate', 'Region', 'Size'],
        ['',    150,              40,      'North America', 5],
        ['',    125,              42,      'Europe', 5],
        ['',    50,               40,      'Europe', 5],
        ['',    60,              45,      'Middle East', 5],
        ['',    80.05,              47,         'Europe', 5],
        ['',    150,              80,       'Middle East', 5],
        ['',    56,              35,      'Middle East', 5],
        ['',    126,              45,      'Middle East', 5],
        ['',    67,               43,      'Europe', 5],
        ['',    69,              45,      'North America', 5]
    ]);

    let options = {
        width: 800,
        height: 600,
        title: 'Fertility rate vs life expectancy in selected countries (2010).' +
            ' X=Life Expectancy, Y=Fertility, Bubble size=Population, Bubble color=Region',
        hAxis: {title: 'Life Expectancy'},
        vAxis: {title: 'Fertility Rate'}
    };

    let chart = new google.visualization.BubbleChart(document.getElementById('chart_div'));
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
                valor = Number(act.personas.mayores) * Number(act.precioMenu) * 0.325;
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
    Object.keys(groups).forEach(key => {
        filas.push([key].concat(groups[key]));
    });

    filas.unshift(headerRow);

    return google.visualization.arrayToDataTable(filas);
}