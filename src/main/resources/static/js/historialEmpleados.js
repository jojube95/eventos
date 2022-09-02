$( document ).ready(function() {
    let minDate = $('#fechaMin').datepicker({
        format: "yyyy-mm-dd",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    });

    let maxDate = $('#fechaMax').datepicker({
        format: "yyyy-mm-dd",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    });

    $.fn.dataTable.ext.search.push(
        function( settings, data ) {
            return filterDate(settings, data, minDate, maxDate);
        }
    );

    let table = $('#historialEmpleado').DataTable({
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

            $(api.column(1).footer()).html(rows);
        }
    });

    // Refilter the table
    $('#fechaMin, #fechaMax').on('change', function () {
        table.draw();
    });
});