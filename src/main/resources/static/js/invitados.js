import {MesaFactory} from "./factories/mesa/MesaFactory.js";
import {updateMesaOnCanvas} from "./distribucion.js";
import {mesasDt} from "./mesas.js";

export function initInvitadosModal(dataTableApi) {
    dataTableApi.button('invitados:name').action(function (e, dt) {
        let mesaSeleccionada = dt.rows({ selected: true }).data()[0];

        $.ajax({
            url: "/evento/mesas/invitados?eventoId=" + evento.id + "&mesaId=" + mesaSeleccionada.id,
            success: function (data) {
                $("#invitadosModalHolder").html(data);
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

                let invitados = $('#invitados').DataTable({
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
                        toggleLoadingSpinner($('#addRowBtn'));
                        delete rowdata.id;
                        rowdata.eventoId = $('#eventoId').val();
                        rowdata.mesaId = $('#mesaId').val();
                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/evento/mesas/invitados/addUpdate",
                            data: JSON.stringify(rowdata),
                            dataType: 'json',
                            success: success,
                            error: error
                        });
                    },
                    onDeleteRow: function(datatable, rowdata, success, error) {
                        toggleLoadingSpinner($('#deleteRowBtn'));
                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/evento/mesas/invitados/delete",
                            data: JSON.stringify(rowdata[0]),
                            dataType: 'json',
                            success: success,
                            error: error
                        });
                    },
                    onEditRow: function(datatable, rowdata, success, error) {
                        toggleLoadingSpinner($('#editRowBtn'));
                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/evento/mesas/invitados/addUpdate",
                            data: JSON.stringify(rowdata),
                            dataType: 'json',
                            success: success,
                            error: error
                        });
                    },
                    footerCallback: function () {
                        let api = this.api();
                        let rows = api.rows({search:'applied'}).count();

                        // Update footer
                        $(api.column().footer()).html("Personas: " + rows);
                    }
                });
                $("#cerrarInvitados").click(function() {
                    let invitadosMayores = invitados.column(4).data().filter(function(value){
                        return value === 'Mayor';
                    }).count();

                    let invitadosNinyos = invitados.column(4).data().filter(function(value){
                        return value === 'Niño';
                    }).count();

                    cerrarInvitadosClicked(invitadosMayores, invitadosNinyos);
                });

                $("#invitadosDetailModal").modal("show");

            }
        })
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