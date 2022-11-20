import {MesaFactory} from "../../factories/mesa/MesaFactory.js";
import {updateMesaOnCanvas} from "../../distribucion.js";

export class Evento {
    constructor(id, tipo, titulo, personas, fecha) {
        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.personas = personas;
        this.fecha = fecha;
    }

    getCalendarioTitulo() {
        return this.titulo + " " + this.personas.mayores + "p";
    }

    getCalendarioColor() {
        throw new Error('Method "getCalendarioColor()" must be implemented.');
    }

    initMesasTable(table, onAddRow, onDeleteRow) {
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
                data: "mayores",
                orderable: false
            },
            {
                data: "ninyos",
                orderable: false
            },
            {
                data: "descripcion",
                orderable: false
            }
        ];

        return table.DataTable({
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
                {
                    extend: 'selected',
                    text: 'Invitados',
                    name: 'invitados'
                }
            ],
            onAddRow: function(datatable, rowdata, success, error) {
                onAddRow(datatable, rowdata, success, error);
            },
            onDeleteRow: function(datatable, rowdata, success, error) {
                onDeleteRow(datatable, rowdata, success, error);
            },
            onEditRow: (datatable, rowdata, success, error) =>
                this.onEditMesaRow(datatable, rowdata, success, error)
            ,
            footerCallback: function () {
                let api = this.api();

                let totalMayores = api
                    .column(3, {search:'applied'})
                    .data()
                    .reduce(function (a, b) {
                        return Number(a) + Number(b);
                    }, 0);

                let totalNinyos = api
                    .column(4, {search:'applied'})
                    .data()
                    .reduce(function (a, b) {
                        return Number(a) + Number(b);
                    }, 0);

                // Update footer
                $(api.column(3).footer()).html(totalMayores);
                $(api.column(4).footer()).html(totalNinyos);
            }
        });
    }

    onEditMesaRow(datatable, rowdata, success, error){
        toggleLoadingSpinner($("#editRowBtn"));

        let mesaObject = MesaFactory.crearMesa(rowdata.id, rowdata.eventoId, rowdata.numero, {mayores: rowdata.mayores, ninyos: rowdata.ninyos}, rowdata.descripcion);

        ajaxCall("POST", "/evento/mesas/update", {}, JSON.stringify(mesaObject), function (mesa) {
            success(mesaObject.getDataTableRowData());
            updateMesaOnCanvas(mesa);
        });
    }

    generarTitulo(tituloInput, selectedTipo, selectedHorario) {
        tituloInput.val(selectedTipo + '-' + selectedHorario);
        tituloInput.prop('readonly', true);
    }
}