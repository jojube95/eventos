document.addEventListener('DOMContentLoaded', function() {
    $("#datepicker").datepicker({
        format: "yyyy",
        viewMode: "years",
        minViewMode: "years",
        autoclose: true
    }).change(function () {
        drawChart($(this).val());
    });

    google.charts.load('current', {'packages':['corechart']});

    google.charts.setOnLoadCallback(function () {
        drawChart(2023)
    });
});

function drawChart(year) {
    let data = prepareData(year);

    let options = {
        width: 600,
        height: 400,
        legend: { position: 'top', maxLines: 3 },
        bar: { groupWidth: '75%' },
        isStacked: true,
    };

    let chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}

function prepareData(year) {
    let groups = eventos.reduce(function (res, act) {
        let date = new Date(act.fecha);
        if(new Date(act.fecha).getFullYear() === Number(year)) {
            let m = date.getMonth() + 1;
            let tipo = act.tipo.value;
            if(res[m]) {
                if(res[m].hasOwnProperty(tipo)) {
                    res[m][tipo] += act.personas.mayores;
                }
                else {
                    res[m][tipo] = act.personas.mayores;
                }
            }
            else{
                res[m] = {group: String(m), [tipo]: act.personas.mayores};
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