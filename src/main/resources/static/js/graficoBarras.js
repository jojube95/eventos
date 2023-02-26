document.addEventListener('DOMContentLoaded', function() {
    // Load the Visualization API and the corechart package.
    google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChart);
});

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {
    $("#datepicker").datepicker({
        format: "yyyy",
        viewMode: "years",
        minViewMode: "years"
    });

    console.log(eventos);
    // Create the data table.
    var data = prepareData();

    var options = {
        width: 600,
        height: 400,
        legend: { position: 'top', maxLines: 3 },
        bar: { groupWidth: '75%' },
        //isStacked: true,
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}

function prepareData() {
    let dataTable = new google.visualization.DataTable();
    dataTable.addColumn('string', 'Mes');
    dataTable.addColumn('number', 'Comensales');

    let groupKey = 0;
    let groups = eventos.reduce(function (r, o) {
        let date = new Date(o.fecha);
        if(date.getFullYear() === 2023) {
            let m = date.getMonth() + 1;
            (r[m]) ? r[m].sum += o.personas.mayores : r[m] = {group: String(groupKey++), sum: o.personas.mayores};
        }
        return r;
    }, {});

    console.log(groups);
    Object.keys(groups).forEach(key => {
        dataTable.addRow([key, groups[key].sum]);
    });

    return dataTable;

}