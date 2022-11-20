$( document ).ready(function() {
    let minDateField = $('#fechaMin');
    let maxDateField = $('#fechaMax');

    initEventDateFilterFields(minDateField, maxDateField);

    let table = initEventosEmpleadoTable();

    initEventDateFilterOnChange(minDateField, maxDateField, table);
});

function initEventosEmpleadoTable() {
    return $('#empleadoHistorial').DataTable({
        order: [0, 'asc'],
        columnDefs: [
            { orderable: false, targets: [1, 2, 3, 4, 5] },
            { className: "dt-right", "targets": [6, 7] }
        ],
        lengthMenu: [
            [10, 25, 50, -1],
            [10, 25, 50, 'All'],
        ],
        initComplete: function () {
            addSelectHeaderTo(this, [2, 3, 4, 5]);
        },
        footerCallback: function () {
            let api = this.api();
            let rows = api.rows({search:'applied'}).count();

            updateFooter(1, rows, api);
        }
    });
}