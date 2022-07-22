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
        function( settings, data, dataIndex ) {
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

    let table = $('#eventos').DataTable({
        order: [0, 'asc'],
        columnDefs: [
            { orderable: false, targets: [1, 2, 3, 8] }
        ],
        lengthMenu: [
            [10, 25, 50, -1],
            [10, 25, 50, 'All'],
        ],
        initComplete: function () {
            this.api()
                .columns([1, 2 , 3])
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
                        .each(function (d, j) {
                            select.append('<option value="' + d + '">' + d + '</option>');
                        });
                });


            let column = this.api().column([8])
            let select = $('<select class="filtro-select"><option value=""></option></select>')
                .appendTo($(column.header()))
                .on('change', function () {
                    let val = $.fn.dataTable.util.escapeRegex($(this).val());

                    column.search(val, false, false).draw();
                });

            select.append('<option value="si">Sí</option>');
            select.append('<option value="no">No</option>');
        },
        footerCallback: function (row, data, start, end, display) {
            let api = this.api();
            let rows = api.rows({search:'applied'}).count();

            // Total over all pages
            let totalPersonas = api
                .column(4, {search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return Number(a) + Number(b);
                }, 0);
            let totalNinyos = api
                .column(6, {search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return Number(a) + Number(b);
                }, 0);
            // Promedio
            let promedioPrecioPersonas = api
                .column(5, {search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return Number(a) + Number(b);
                }, 0);
            let promedioPrecioNinyos = api
                .column(7, {search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return Number(a) + Number(b);
                }, 0);
            // Total
            let total = api
                .rows({search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return a + (Number(b[4]) * Number(b[5])) + (Number(b[6]) * Number(b[7]))
                }, 0);
            // Update footer
            $(api.column(4).footer()).html(totalPersonas);
            $(api.column(5).footer()).html((promedioPrecioPersonas / rows).toFixed(2));
            $(api.column(6).footer()).html(totalNinyos);
            $(api.column(7).footer()).html((promedioPrecioNinyos / rows).toFixed(2));
            $(api.column(8).footer()).html(total + '€');
        }
    });

    // Refilter the table
    $('#fechaMin, #fechaMax').on('change', function () {
        table.draw();
    });
});