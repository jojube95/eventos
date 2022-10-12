const moneda = '€';

function initEventDateFilterFields(minDateField, maxDateField) {
    let minInitDate = new Date();
    let maxInitDate = new Date();
    minInitDate.setMonth(minInitDate.getMonth() - 1);
    maxInitDate.setMonth(maxInitDate.getMonth() + 1);

    let minDate = initDatepicker(minDateField, minInitDate);

    let maxDate = initDatepicker(maxDateField, maxInitDate);

    $.fn.dataTable.ext.search.push(
        function( settings, data ) {
            return filterDate(settings, data, minDate, maxDate);
        }
    );
}

function initEventDateFilterOnChange(minDateField, maxDateField, table) {
    minDateField.on('change', function () {
        table.draw();
    });
    maxDateField.on('change', function () {
        table.draw();
    });
}

function initDatepicker(field, initDate) {
    return field.datepicker({
        format: "yyyy-mm-dd",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    }).datepicker("setDate", initDate);
}

function updateFooter(column, value, api) {
    $(api.column(column).footer()).html(value);
}

function getSumByColumn(column, api) {
    return api.column(column, {search: 'applied'}).data()
        .reduce(function (a, b) {
            return Number(a) + Number(b);
        }, 0);
}

function getTotalNeto(api) {
    return api.rows({search: 'applied'})
        .data()
        .reduce(function (a, b) {
            return a + getEventoBruto(b, 6, 7, 8, 9)
        }, 0) * ratioBeneficios;
}

function getEventoNeto(filaEvento, columnaMayores, columnaPrecioMenu, columnaNinyos, columnaPrecioMenuNinyos) {
    return Math.round((getEventoBruto(filaEvento, columnaMayores, columnaPrecioMenu, columnaNinyos, columnaPrecioMenuNinyos)) * ratioBeneficios);
}

function getEventoBruto(filaEvento, columnaMayores, columnaPrecioMenu, columnaNinyos, columnaPrecioMenuNinyos) {
    return (filaEvento[columnaMayores] * filaEvento[columnaPrecioMenu]) + (filaEvento[columnaNinyos] * filaEvento[columnaPrecioMenuNinyos]);
}

function addSelectHeaderToCheckBox(api, column) {
    let tableColumn = api.column([column]);
    let select = $('<select class="filtro-select"><option value=""></option></select>')
        .appendTo($(tableColumn.header()))
        .on('change', function () {
            let val = $.fn.dataTable.util.escapeRegex($(this).val());

            tableColumn.search(val, false, false).draw();
        });

    select.append('<option value="si">Sí</option>');
    select.append('<option value="no">No</option>');
}

function filterDate(settings, data, minDateInput, maxDateInput){
    let min = minDateInput.val();
    let max = maxDateInput.val();

    if (minDateInput.val()){
        min = new Date(minDateInput.val());
    }
    if (maxDateInput.val()){
        max = new Date(maxDateInput.val());
    }
    let date = new Date( data[0] );

    return (min === '' && max === '') ||
        (min === '' && date <= max) ||
        (min <= date && max === '') ||
        (min <= date && date <= max);
}

function addSelectHeaderTo(table, columns){
    table.api()
        .columns(columns)
        .every(function () {
            let column = this;
            let select = $('<select class="filtro-select"><option value=""></option></select>')
                .appendTo($(column.header()))
                .on('change', function () {
                    let val = $.fn.dataTable.util.escapeRegex($(this).val());

                    column.search(val ? '^' + val + '$' : '', true, false).draw();
                });

            column
                .data()
                .unique()
                .sort()
                .each(function (d) {
                    select.append('<option value="' + d + '">' + d + '</option>');
                });
        });
}

function rowClicked(eventoId){
    location.href = "/eventoVer?eventoId=" + eventoId;
}