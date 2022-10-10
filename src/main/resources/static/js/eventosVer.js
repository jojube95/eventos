const ratioBeneficios = 0.325;
const moneda = '€';

$( document ).ready(function() {
    let userIsAdmin = $('#userRole').text() === "[ROLE_ADMIN]";

    initEventDateFilterFields($('#fechaMin'), $('#fechaMax'));

    let table = initEventosTable(userIsAdmin);

    $('#fechaMin, #fechaMax').on('change', function () {
        table.draw();
    });
});

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

function initDatepicker(field, initDate) {
    return field.datepicker({
        format: "yyyy-mm-dd",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    }).datepicker("setDate", initDate);
}

function initEventosTable(userIsAdmin) {
    if (userIsAdmin) {
        return initEventosAdminTable();
    }
    else{
        return initEventosUserTable();
    }
}

function initEventosAdminTable() {
    return $('#eventos').DataTable({
        order: [0, 'asc'],
        columnDefs: [
            {orderable: false, targets: [1, 2, 3, 4, 5, 10]},
            {className: "dt-right", targets: [6, 7, 8, 9, 11]},
            {
                targets: (11),
                render: function (data, type, row) {
                    return getEventoNeto(row, 6, 7, 8, 9) + moneda;
                }
            }
        ],
        lengthMenu: [
            [10, 25, 50, -1],
            [10, 25, 50, 'All'],
        ],
        initComplete: function () {
            addSelectHeaderTo(this, [2, 3, 4, 5]);
            addSelectHeaderToCheckBox(this.api(), 10);
        },
        footerCallback: function () {
            let api = this.api();
            let rows = api.rows({search: 'applied'}).count();

            updateFooter(6, getSumByColumn(6, api), api);
            updateFooter(7, Math.round(getSumByColumn(7, api) / rows), api);
            updateFooter(8, getSumByColumn(8, api), api);
            updateFooter(9, Math.round(getSumByColumn(9, api) / rows), api);
            updateFooter(11, Math.round(getTotalNeto(api)) + moneda, api);
        }
    });
}

function initEventosUserTable() {
    return $('#eventos').DataTable({
        order: [0, 'asc'],
        columnDefs: [
            {orderable: false, targets: ([1, 2, 3, 4, 5, 8])},
            {className: "dt-right", targets: ([6, 7])}
        ],
        lengthMenu: [
            [10, 25, 50, -1],
            [10, 25, 50, 'All'],
        ],
        initComplete: function () {
            addSelectHeaderTo(this, [2, 3, 4, 5]);
            addSelectHeaderToCheckBox(this.api(), 8);
        },
        footerCallback: function () {
            let api = this.api();

            updateFooter(6, getSumByColumn(6, api), api);
            updateFooter(7, getSumByColumn(7, api), api);
        }
    });
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

function rowClicked(eventoId){
    location.href = "/eventoVer?eventoId=" + eventoId;
}