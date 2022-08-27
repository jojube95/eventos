$( document ).ready(function() {
    let userIsAdmin = $('#userRole').text() === "[ROLE_ADMIN]";
    let minInitDate = new Date();
    let maxInitDate = new Date();
    minInitDate.setMonth(minInitDate.getMonth() - 1);
    maxInitDate.setMonth(maxInitDate.getMonth() + 1);

    let minDate = $('#fechaMin').datepicker({
        format: "yyyy-mm-dd",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    }).datepicker("setDate", minInitDate);

    let maxDate = $('#fechaMax').datepicker({
        format: "yyyy-mm-dd",
        language: "es",
        orientation: "bottom auto",
        autoclose: true
    }).datepicker("setDate", maxInitDate);

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

    let table = $('#eventos').DataTable({
        order: [0, 'asc'],
        columnDefs: [
            { orderable: false, targets: (userIsAdmin? [1, 2, 3, 4, 5, 10] : [1, 2, 3, 4, 5, 8]) }
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


            let column = userIsAdmin ? this.api().column([10]) : this.api().column([8]);
            let select = $('<select class="filtro-select"><option value=""></option></select>')
                .appendTo($(column.header()))
                .on('change', function () {
                    let val = $.fn.dataTable.util.escapeRegex($(this).val());

                    column.search(val, false, false).draw();
                });

            select.append('<option value="si">Sí</option>');
            select.append('<option value="no">No</option>');
        },
        footerCallback: function () {
            let api = this.api();
            let rows = api.rows({search:'applied'}).count();

            // Total over all pages
            let totalPersonas = api
                .column(6, {search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return Number(a) + Number(b);
                }, 0);

            let totalNinyos;
            if (userIsAdmin) {
                totalNinyos = api
                    .column(8, {search:'applied'})
                    .data()
                    .reduce(function (a, b) {
                        return Number(a) + Number(b);
                    }, 0);
            }
            else{
                totalNinyos = api
                    .column(7, {search:'applied'})
                    .data()
                    .reduce(function (a, b) {
                        return Number(a) + Number(b);
                    }, 0);
            }

            let promedioPrecioPersonas;
            let promedioPrecioNinyos;
            if (userIsAdmin) {
                // Promedio
                promedioPrecioPersonas = api
                    .column(7, {search:'applied'})
                    .data()
                    .reduce(function (a, b) {
                        return Number(a) + Number(b);
                    }, 0);
                promedioPrecioNinyos = api
                    .column(9, {search:'applied'})
                    .data()
                    .reduce(function (a, b) {
                        return Number(a) + Number(b);
                    }, 0);
            }

            // Total
            let total = api
                .rows({search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return a + (Number(b[6]) * Number(b[7])) + (Number(b[8]) * Number(b[9]))
                }, 0);

            // Update footer
            if (userIsAdmin) {
                $(api.column(6).footer()).html(totalPersonas);
                $(api.column(7).footer()).html((promedioPrecioPersonas / rows).toFixed(2));
                $(api.column(9).footer()).html((promedioPrecioNinyos / rows).toFixed(2));
                $(api.column(8).footer()).html(totalNinyos);
                $(api.column(10).footer()).html(total + '€');
            }
            else {
                $(api.column(6).footer()).html(totalPersonas);
                $(api.column(7).footer()).html(totalNinyos);
            }
        }
    });

    // Refilter the table
    $('#fechaMin, #fechaMax').on('change', function () {
        table.draw();
    });
});