let pagadoOptions = { "true" : "Sí", "false" : "No" };
let mesasDt;

$(document).ready(function() {
    let columnDefs = [
        {
            data: "id",
            type: "hidden",
            visible: false
        },
        {
            data: "idEvento",
            type: "hidden",
            visible: false
        },
        {
            data: "numero",
            orderable: false,
            unique: true

        },
        {
            data: "representante",
            visible: isEventoIndividual,
            type: isEventoIndividual ? "" : "hidden",
            orderable: false
        },
        {
            data: "personas",
            orderable: false
        },
        {
            data: "pagado",
            type: isEventoIndividual ? "select" : "hidden",
            visible: isEventoIndividual,
            options : pagadoOptions,
            orderable: false,
            select2 : { width: "100%"},
            render: function (data) {
                if (data == null || !(data in pagadoOptions)) return null;
                return pagadoOptions[data];
            }
        }
        ];

    mesasDt = $('#mesas').DataTable({
        "sPaginationType": "full_numbers",
        columns: columnDefs,
        order: [2, 'asc'],
        dom: 'Bfrtip',
        select: 'single',
        responsive: true,
        paging: false,
        info: false,
        altEditor: true,
        buttons: [
            {
                text: 'Add',
                name: 'add'        // do not change name
            },
            {
                extend: 'selected', // Bind to Selected row
                text: 'Edit',
                name: 'edit'        // do not change name
            },
            {
                extend: 'selected', // Bind to Selected row
                text: 'Delete',
                name: 'delete'      // do not change name
            },
            ...(isEventoIndividual ? [] : [{
                extend: 'selected',
                text: 'Invitados',
                name: 'invitados'
            }])
        ],
        onAddRow: function(datatable, rowdata, success, error) {
            rowdata.idEvento = idEvento;
            delete rowdata.id;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/evento/mesas/add",
                data: JSON.stringify(rowdata),
                dataType: 'json',
                success: success,
                error: error
            });
        },
        onDeleteRow: function(datatable, rowdata, success, error) {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/evento/mesas/delete",
                data: JSON.stringify(rowdata[0]),
                dataType: 'json',
                success: success,
                error: error
            });
        },
        onEditRow: function(datatable, rowdata, success, error) {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/evento/mesas/update",
                data: JSON.stringify(rowdata),
                dataType: 'json',
                success: success,
                error: error
            });
        },
        footerCallback: function () {
            let api = this.api();
            let rows = api.rows({search:'applied'}).count();

            let totalPersonas = api
                .column(4, {search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return Number(a) + Number(b);
                }, 0);

            // Promedio
            let totalPagados  = api
                .column(5, {search:'applied'})
                .data()
                .reduce(function (a, b) {
                    return Number(a) + (b === 'true' ? 1: 0);
                }, 0);
            // Update footer
            $(api.column(4).footer()).html(totalPersonas);
            $(api.column(5).footer()).html(((totalPagados / rows) * 100).toFixed(2) + "%");
        }
    });

    $('.dt-buttons > button:first-child').on( "click", function() {
        waitForElm('#numero').then(() => {
            let tableNumbers = $('#mesas > tbody > tr > td:first-child').get().map((element) => {
                return Number($(element).text());
            });
            let nextTableNumber = Math.max.apply(Math, tableNumbers) + 1;

            $('#numero').val(nextTableNumber);
        });
    });
});

function cerrarInvitadosClicked(numeroInvitados){
    let mesaSeleccionada = mesasDt.rows({ selected: true }).data()[0];
    mesaSeleccionada.personas = numeroInvitados.toString();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/evento/mesas/update",
        data: JSON.stringify(mesaSeleccionada),
        dataType: 'json',
        error: function (e) {
            alert(e);
        },
        success: function () {
            mesasDt.row({ selected: true }).data(mesaSeleccionada);
            mesasDt.draw();
        },
    });
}

function waitForElm(selector) {
    return new Promise(resolve => {
        if (document.querySelector(selector)) {
            return resolve(document.querySelector(selector));
        }

        const observer = new MutationObserver(mutations => {
            if (document.querySelector(selector)) {
                resolve(document.querySelector(selector));
                observer.disconnect();
            }
        });

        observer.observe(document.body, {
            childList: true,
            subtree: true
        });
    });
}

