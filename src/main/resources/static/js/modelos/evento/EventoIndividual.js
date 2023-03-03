import {Evento} from "./Evento.js";
import {MesaFactory} from "../../factories/mesa/MesaFactory.js";
import {updateMesaOnCanvas} from "../../distribucion.js";

export class EventoIndividual extends Evento {
    constructor(id, tipo, titulo, personas, fecha, descripcion) {
        super(id, tipo, titulo, personas, fecha, descripcion);
    }

    getCalendarioColor() {
        return 'DarkGreen';
    }

    initMesasTable(table, onAddRow, onDeleteRow) {
        let pagadoOptions = { "true" : "Sí", "false" : "No" };

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
                type: "select",
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

        return table.DataTable({
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
                }
            ],
            onAddRow: function(datatable, rowdata, success) {
                onAddRow(datatable, rowdata, success);
            },
            onDeleteRow: function(datatable, rowdata, success, error) {
                onDeleteRow(datatable, rowdata, success, error);
            },
            onEditRow: (datatable, rowdata, success, error) =>
                this.onEditMesaRow(datatable, rowdata, success, error)
            ,
            footerCallback: function () {
                let api = this.api();
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

                // Update footer
                $(api.column(4).footer()).html(totalMayores);
                $(api.column(5).footer()).html(totalNinyos);

                // Promedio
                let totalPagados  = api
                    .column(6, {search:'applied'})
                    .data()
                    .reduce(function (a, b) {
                        return Number(a) + (b === 'true' ? 1: 0);
                    }, 0);

                $(api.column(6).footer()).html(((totalPagados / rows) * 100).toFixed(2) + "%");
            }
        });
    }

    onEditMesaRow(datatable, rowdata, success, error){
        toggleLoadingSpinner($("#editRowBtn"));

        let mesaObject = MesaFactory.crearMesa(rowdata.id, rowdata.eventoId, rowdata.numero, {mayores: rowdata.mayores, ninyos: rowdata.ninyos}, rowdata.descripcion, rowdata.representante, rowdata.pagado);

        ajaxCall("POST", "/evento/mesas/update", {}, JSON.stringify(mesaObject), function (mesa) {
            success(mesaObject.getDataTableRowData());
            updateMesaOnCanvas(mesa);
        });
    }

    generarTitulo(tituloInput, selectedTipo, selectedHorario) {
        tituloInput.val('');
        tituloInput.prop('readonly', false);
    }
}