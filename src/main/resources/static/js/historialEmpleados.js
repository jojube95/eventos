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
            let min = minDate.val();
            let max = maxDate.val();

            if (minDate.val()){
                min = new Date(minDate.val());
            }
            if (maxDate.val()){
                max = new Date(maxDate.val());
            }
            let date = new Date( data[0] );

            return (min === '' && max === '') ||
                (min === '' && date <= max) ||
                (min <= date && max === '') ||
                (min <= date && date <= max);

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
            this.api()
                .columns([2, 3, 4, 5])
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