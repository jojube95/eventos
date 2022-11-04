import {MesaFactory} from "./factories/mesa/MesaFactory.js";
import {updateMesaOnCanvas} from "./distribucion.js";
import {mesasDt} from "./mesas.js";

let invitadosDt;

export function initInvitadosModal(dataTableApi) {
    dataTableApi.button('invitados:name').action(function (e, dt) {
        let mesaSeleccionada = dt.rows({ selected: true }).data()[0];

        ajaxCall("GET", "/evento/mesas/invitados", {eventoId: evento.id, mesaId: mesaSeleccionada.id}, {}, function (invitadosModal) {
            $("#invitadosModalHolder").html(invitadosModal);

            invitadosDt = initInvitadosTable($('#invitados'), onAddRow, onEditRow, onDeleteRow);

            onCerrarInvitadosModal();

            $("#invitadosDetailModal").modal("show");
        });
    });
}

function initInvitadosTable(table, onAddRow, onEditRow, onDeleteRow) {
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
            data: "mesaId",
            type: "hidden",
            visible: false
        },
        {
            data: "nombre"

        },
        {
            data: "tipo",
            type: "select",
            options: {"Mayor":"Mayor", "Niño":"Niño"}

        },
        {
            data: "descripcion"

        }
    ];

    return table.DataTable({
        "sPaginationType": "full_numbers",
        columns: columnDefs,
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
            }
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
            let api = this.api();
            let rows = api.rows({search:'applied'}).count();

            // Update footer
            $(api.column().footer()).html("Personas: " + rows);
        }
    });
}

function onAddRow(datatable, rowdata, success){
    toggleLoadingSpinner($('#addRowBtn'));
    delete rowdata.id;
    rowdata.eventoId = $('#eventoId').val();
    rowdata.mesaId = $('#mesaId').val();

    ajaxCall("POST", "/evento/mesas/invitados/addUpdate", {}, JSON.stringify(rowdata), success);
}

function onEditRow(datatable, rowdata, success){
    toggleLoadingSpinner($('#editRowBtn'));
    ajaxCall("POST", "/evento/mesas/invitados/addUpdate", {}, JSON.stringify(rowdata), success);
}

function onDeleteRow(datatable, rowdata, success){
    toggleLoadingSpinner($('#deleteRowBtn'));
    ajaxCall("POST", "/evento/mesas/invitados/delete", {}, JSON.stringify(rowdata[0]), success);
}

function onCerrarInvitadosModal(){
    $("#cerrarInvitados").click(function() {
        let invitadosMayores = invitadosDt.column(4).data().filter(function(value){
            return value === 'Mayor';
        }).count();

        let invitadosNinyos = invitadosDt.column(4).data().filter(function(value){
            return value === 'Niño';
        }).count();

        cerrarInvitadosClicked(invitadosMayores, invitadosNinyos);
    });
}

function cerrarInvitadosClicked(invitadosMayores, invitadosNinyos){
    let mesaSeleccionada = mesasDt.rows({ selected: true }).data()[0];

    let mesaObject = MesaFactory.crearMesa(mesaSeleccionada.id, mesaSeleccionada.eventoId, mesaSeleccionada.numero, {mayores: invitadosMayores, ninyos: invitadosNinyos}, mesaSeleccionada.descripcion);

    ajaxCall("POST", "/evento/mesas/update", {}, JSON.stringify(mesaObject), function () {
        mesasDt.row({ selected: true }).data(mesaObject.getDataTableRowData());
        mesasDt.draw();

        updateMesaOnCanvas(mesaObject);
    });
}