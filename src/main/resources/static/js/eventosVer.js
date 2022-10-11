$( document ).ready(function() {
    let userIsAdmin = $('#userRole').text() === "[ROLE_ADMIN]";

    let minDateField = $('#fechaMin');
    let maxDateField = $('#fechaMax');

    initEventDateFilterFields(minDateField, maxDateField);

    let table = initEventosTable(userIsAdmin);

    initEventDateFilterOnChange(minDateField, maxDateField, table);
});

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