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