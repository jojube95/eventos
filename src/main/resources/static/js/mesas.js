let pagadoOptions = { "true" : "Sí", "false" : "No" };
let mesasDt;
let canvas;

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

    canvas = new fabric.Canvas('canvas');

    onAddClicked();

    onCanvasObjectClick();

    onCanvasObjectDoubleClick();

    loadCanvas();
});

function onCanvasObjectClick(){
    fabric.util.addListener(canvas.upperCanvasEl, 'click', function (e) {
        let target = canvas.findTarget(e);

        let index = $('tr[mesaId="' + target.mesaId + '"]').index();

        mesasDt.row(':eq(' + index + ')').select();
    });
}

function onCanvasObjectDoubleClick(){
    fabric.util.addListener(canvas.upperCanvasEl, 'dblclick', function (e) {
        let target = canvas.findTarget(e);
        changeTableType(target);
    });
}

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
    rowdata.idEvento = idEvento;
    delete rowdata.id;

    addMesaAjax(rowdata, success, error);
}

function addMesaAjax(mesaRowData, success, error){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/evento/mesas/add",
        data: JSON.stringify(mesaRowData),
        dataType: 'json',
        success: function (mesa) {
            success(mesa);
            $.ajax({
                url: "/evento/distribucion/tipoMesaModal?mesaId=" + mesa.id + "&numero=" + mesa.numero + "&personas=" + mesa.personas,
                success: function (data) {
                    anyadirMesaToCanvas(mesa.id, mesa.numero, mesa.personas, 150, 100, data);
                }
            });

        },
        error: error
    });
}

function onDeleteRow(datatable, rowdata, success, error){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/evento/mesas/delete",
        data: JSON.stringify(rowdata[0]),
        dataType: 'json',
        success: function (mesa){
            success(mesa);

            canvas.getObjects().forEach(function(object) {
                if (object.mesaId === mesa.id){
                    canvas.remove(object);
                    canvas.renderAll();
                    guardarDistribucion();
                }
            });
        },
        error: error
    });
}

function onEditRow(datatable, rowdata, success, error){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/evento/mesas/update",
        data: JSON.stringify(rowdata),
        dataType: 'json',
        success: function (mesa){
            success(mesa);
            updateMesaOnCanvas(mesa);
        },
        error: error
    });
}

function footerCallback(dataTableApi){
    let api = dataTableApi.api();
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

function updateMesaOnCanvas(mesa){
    canvas.getObjects().forEach(function(object) {
        if (object.mesaId === mesa.id){
            let mesaId = object.mesaId;
            let numero = mesa.numero;
            let personas = mesa.personas;
            let top = object.top;
            let left = object.left;

            if(personas > 11){
                canvas.remove(object);
                addRectangleTable(mesaId, numero, personas, top, left);
            }
            else if(personas <= 4){
                canvas.remove(object);
                addRectangleTable(mesaId, numero, personas, top, left);
            }
            else{
                object.set({ numero: numero, personas: personas});
                object._objects[1].set({ text: "T-" + numero + "\n" + personas + "p"});
            }

            canvas.renderAll();

            guardarDistribucion();
        }
    });
}

function anyadirMesaToCanvas(mesaId, numero, personas, top, left, htmlModal){
    let tipoMesaModal = "#distribucionTipoMesaModal";

    // 50px - 1m
    if (personas > 4 && personas <= 11) {
        $("#distribucionTipoMesaModalHolder").html(htmlModal);
        $(tipoMesaModal).modal("show");
    }
    else if(personas <= 4){
        addRectangleTable(mesaId, numero, personas, top, left);
    }
    else{
        addRectangleTable(mesaId, numero, personas, top, left);
    }
    $(tipoMesaModal).modal("hide");
    guardarDistribucion();
}

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

            updateMesaOnCanvas(mesaSeleccionada);
        },
    });
}

function guardarDistribucion(){
    let json = canvas.toJSON(['mesaId', 'numero', 'personas']);

    $.ajax({
        url: "/evento/distribucion/guardar?idEvento=" + idEvento,
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json; charset=utf-8",
        error: function (err) {
            alert(err);
        }
    });
}

function guardarClicked(){
    let json = canvas.toJSON(['mesaId', 'numero', 'personas']);

    $.ajax({
        url: "/evento/distribucion/guardar?idEvento=" + idEvento,
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json; charset=utf-8",
        success: function () {
            $("#confirmModal").modal("show");
        },
        error: function (err) {
            alert(err);
        }
    });
}

function exportarDistribucionClicked(){
    // only jpeg is supported by jsPDF
    let imgData = canvas.toDataURL("image/jpeg", 1.0);
    let pdf = new jsPDF('landscape');

    pdf.addImage(imgData, 'JPEG', 0, 0);
    pdf.save("distribució.pdf");
}

function exportarListadoClicked(){
    window.location = "/evento/mesas/generarListado?eventoId=" + idEvento;
}

function addCircleTable(mesaId, numero, personas, top, left) {
    let circle = new fabric.Circle({
        radius: 40,
        fill : 'white',
        stroke: 'black',
        strokeWidth: 1,
        originX: 'center',
        originY: 'center'
    });

    insertTextToObject(mesaId, numero, personas, top, left, circle);
}

function addRectangleTable(mesaId, numero, personas, top, left) {
    let numeroLargas = Math.ceil(personas / 6);
    let personasUltimaMesa = personas % 6;
    let tableLength;
    personasUltimaMesa > 0 && personasUltimaMesa <= 2 ? tableLength = ((numeroLargas - 1) * 100) + 32.5 : tableLength = numeroLargas * 100;

    let rect = new fabric.Rect({
        width : 50,
        height : tableLength,
        fill : 'white',
        stroke: 'black',
        strokeWidth: 1,
        originX: 'center',
        originY: 'center'
    });

    insertTextToObject(mesaId, numero, personas, top, left, rect);
}

function insertTextToObject(mesaId, numero, personas, top, left, objectToInsert){
    let text = new fabric.Text("T-" + numero + "\n" + personas + "p", {
        fontSize: 12,
        originX: 'center',
        originY: 'center'
    });

    let group = new fabric.Group([ objectToInsert, text ], {
        left: left,
        top: top,
        hasRotatingPoint: false
    });

    group.setControlsVisibility({
        tl: false,
        tr: false,
        br: false,
        bl: false,
        ml: false,
        mt: false,
        mr: false,
        mb: false,
        mtr: false
    });

    group['mesaId'] = mesaId;
    group['numero'] = numero;
    group['personas'] = personas;

    addObjectToCanvas(group);
}

function addObjectToCanvas(object){
    canvas.add(object);
    canvas.renderAll();
}

function changeTableType(table){
    let tableType = table._objects[0].type;
    let mesaId = table.mesaId;
    let numero = table.numero;
    let personas = table.personas;
    let top = table.top;
    let left = table.left;

    canvas.remove(table);

    if(tableType === 'rect'){
        addCircleTable(mesaId, numero, personas, top, left);
    }
    else{
        addRectangleTable(mesaId, numero, personas, top, left);
    }

    canvas.renderAll();
    guardarDistribucion();
}

function loadCanvas(){
    canvas.loadFromJSON(distribucion,canvas.renderAll.bind(canvas));

    let objects = canvas.getObjects();

    objects.forEach(function(object) {
        object.setControlsVisibility({
            tl: false,
            tr: false,
            br: false,
            bl: false,
            ml: false,
            mt: false,
            mr: false,
            mb: false,
            mtr: false
        });
    });
}

function waitForElm(selector) {
    return new Promise(resolve => {
        if (document.querySelector(selector)) {
            return resolve(document.querySelector(selector));
        }

        const observer = new MutationObserver(() => {
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

