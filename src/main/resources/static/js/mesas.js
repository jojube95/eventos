let pagadoOptions = { "true" : "Sí", "false" : "No" };
let mesasDt;

let rowColorAdded = 'greenyellow';
let rowColorNotAdded = 'LightYellow';

$(document).ready(function() {
    let columnDefs = [
        {
            data: "id",
            type: "hidden",
            visible: false
        },
        {
            data: "eventoId",
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
            data: "mayores",
            orderable: false
        },
        {
            data: "ninyos",
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
        },
        {
            data: "descripcion",
            orderable: false
        }
        ];

    mesasDt = $('#mesas').DataTable({
        "sPaginationType": "full_numbers",
        columns: columnDefs,
        order: [1, 'asc'],
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
            onAddRow(datatable, rowdata, success, error);
        },
        onDeleteRow: function(datatable, rowdata, success, error) {
            onDeleteRow(datatable, rowdata, success, error);
        },
        onEditRow: function(datatable, rowdata, success, error) {
            onEditRow(datatable, rowdata, success, error);
        },
        footerCallback: function () {
            footerCallback(this);
        }
    });

    onAddClicked();

    let mesasTbody = $('#mesas tbody');

    mesasTbody.on('dblclick', 'tr', function () {
        let mesa = mesasDt.row( this ).data();
        if(getObjectFromCanvas(mesa) === undefined) {
            anyadirClicked(mesa.id, mesa.numero, mesa.mayores, mesa.ninyos, this);
        }
    } );

    mesasTbody.on('click', 'tr', function () {
        let mesa = mesasDt.row( this ).data();
        setMesaActiveObject(mesa);
    } );



    $("#file-upload-form").on("submit", function (e) {
        toggleLoadingSpinner($("#importarDistribucionButton"));

        e.preventDefault();

        $.ajax({
            url: "/evento/mesas/uploadExcel?eventoId=" + eventoId,
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                toggleLoadingSpinner($("#importarDistribucionButton"));
                location.reload();
            },
            error: function (err) {
                console.error(err);
            }
        });
    });
});

function onAddClicked(){
    $('.dt-buttons > button:first-child').on( "click", function() {
        waitForElm('#numero').then(() => {
            let tableNumbers = $('#mesas > tbody > tr > td:first-child').get().map((element) => {
                let number = Number($(element).text());
                if(isNaN(number)) {
                    return 0
                }
                else{
                    return number;
                }
            });

            let nextTableNumber = Math.max.apply(Math, tableNumbers) + 1;

            $('#numero').val(nextTableNumber);
        });
    });
}

function onAddRow(datatable, rowdata, success, error){
    toggleLoadingSpinner($("#addRowBtn"));

    rowdata.eventoId = eventoId;
    delete rowdata.id;

    addMesaAjax(rowdata, success, error);
}

function addMesaAjax(mesaRowData, success){
    ajaxCall("POST", "/evento/mesas/add", {eventoId: eventoId}, JSON.stringify(insertPersonas(mesaRowData)), function (mesa) {
        success(extractPersonas(mesa));
        let params = {mesaId: mesa.id, numero: mesa.numero, mayores: mesa.mayores, ninyos: mesa.ninyos};
        ajaxCall("GET", "/evento/distribucion/tipoMesaModal", params, {}, function (data) {
            anyadirMesaToCanvas(mesa.id, mesa.numero, mesa.mayores, mesa.ninyos, 150, 100, data);
        });
    });
}

function anyadirClicked(mesaId, numero, mayores, ninyos){
    let params = {mesaId: mesaId, numero: numero, mayores: mayores, ninyos: ninyos};
    ajaxCall("GET", "/evento/distribucion/tipoMesaModal", params, {}, function (data) {
        anyadirMesaToCanvas(mesaId, numero, mayores, ninyos, 150, 100, data);
    });
}

function changeRowColor(mesaId, color){
    $('tr[mesaId="' + mesaId + '"]').css('background-color', color);
}

function onDeleteRow(datatable, rowdata, success){
    toggleLoadingSpinner($("#deleteRowBtn"));

    ajaxCall("POST", "/evento/mesas/delete", {}, JSON.stringify(insertPersonas(rowdata[0])), function (mesa) {
        success(extractPersonas(mesa));
        deleteObject(getObjectFromCanvas(mesa));
    });
}

function onEditRow(datatable, rowdata, success){
    toggleLoadingSpinner($("#editRowBtn"));

    ajaxCall("POST", "/evento/mesas/update", {}, JSON.stringify(insertPersonas(rowdata)), function (mesa) {
        success(extractPersonas(mesa));
        updateMesaOnCanvas(mesa);
    });
}

function footerCallback(dataTableApi){
    let api = dataTableApi.api();
    let rows = api.rows({search:'applied'}).count();

    let totalMayores = api
        .column(4, {search:'applied'})
        .data()
        .reduce(function (a, b) {
            return Number(a) + Number(b);
        }, 0);

    let totalNinyos = api
        .column(5, {search:'applied'})
        .data()
        .reduce(function (a, b) {
            return Number(a) + Number(b);
        }, 0);

    // Promedio
    let totalPagados  = api
        .column(6, {search:'applied'})
        .data()
        .reduce(function (a, b) {
            return Number(a) + (b === 'true' ? 1: 0);
        }, 0);
    // Update footer
    $(api.column(4).footer()).html(totalMayores);
    $(api.column(5).footer()).html(totalNinyos);

    if (isEventoIndividual){
        $(api.column(6).footer()).html(((totalPagados / rows) * 100).toFixed(2) + "%");
    }

}

function cerrarInvitadosClicked(invitadosMayores, invitadosNinyos){
    let mesaSeleccionada = mesasDt.rows({ selected: true }).data()[0];
    mesaSeleccionada.mayores = invitadosMayores.toString();
    mesaSeleccionada.ninyos = invitadosNinyos.toString();

    ajaxCall("POST", "/evento/mesas/update", {}, JSON.stringify(insertPersonas(mesaSeleccionada)), function () {
        mesasDt.row({ selected: true }).data(extractPersonas(mesaSeleccionada));
        mesasDt.draw();

        updateMesaOnCanvas(mesaSeleccionada);
    });
}

function exportarListadoClicked(){
    window.location = "/evento/mesas/generarListado?eventoId=" + eventoId;
}

function extractPersonas(mesa) {
    mesa.mayores = mesa.personas.mayores;
    mesa.ninyos = mesa.personas.ninyos;
    delete mesa.personas;

    return mesa;
}

function insertPersonas(mesa) {
    let personas = {'mayores': mesa.mayores, 'ninyos': mesa.ninyos}
    delete mesa.personas;
    delete mesa.ninyos;
    mesa.personas = personas;

    return mesa;
}