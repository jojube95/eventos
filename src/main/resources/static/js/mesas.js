let pagadoOptions = { "true" : "Sí", "false" : "No" };
let mesasDt;
let canvas;

let deleteIcon = "data:image/svg+xml,%3C%3Fxml version='1.0' encoding='utf-8'%3F%3E%3C!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'%3E%3Csvg version='1.1' id='Ebene_1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='595.275px' height='595.275px' viewBox='200 215 230 470' xml:space='preserve'%3E%3Ccircle style='fill:%23F44336;' cx='299.76' cy='439.067' r='218.516'/%3E%3Cg%3E%3Crect x='267.162' y='307.978' transform='matrix(0.7071 -0.7071 0.7071 0.7071 -222.6202 340.6915)' style='fill:white;' width='65.545' height='262.18'/%3E%3Crect x='266.988' y='308.153' transform='matrix(0.7071 0.7071 -0.7071 0.7071 398.3889 -83.3116)' style='fill:white;' width='65.544' height='262.179'/%3E%3C/g%3E%3C/svg%3E";
let reverseIcon = "data:image/png;base64,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";

let imgDelete = document.createElement('img');
let imgReverse = document.createElement('img');

imgDelete.src = deleteIcon;
imgReverse.src = reverseIcon;


// 40px - 1m
let alturaLarga = 80;
let anchuraLarga = 40;
let alturaApoyo = 26;
let radioRedonda = 36;

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
        },
        {
            data: "anyadir",
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

    fabric.Object.prototype.controls.deleteControl = new fabric.Control({
        x: 0.5,
        y: -0.5,
        offsetY: 15,
        cursorStyle: 'pointer',
        mouseUpHandler: clickDeleteIcon,
        render: renderIconDelete,
        cornerSize: 24
    });

    fabric.Object.prototype.controls.reverseControl = new fabric.Control({
        x: 0.5,
        y: -0.5,
        offsetY: 45,
        cursorStyle: 'pointer',
        mouseUpHandler: clickReverseIcon,
        render: renderIconReverse,
        cornerSize: 24
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

        if (target !== undefined){
            let index = $('tr[mesaId="' + target.mesaId + '"]').index();

            mesasDt.row(':eq(' + index + ')').select();
        }
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
    toggleLoadingSpinner($("#addRowBtn"));

    rowdata.idEvento = idEvento;
    delete rowdata.id;

    addMesaAjax(rowdata, success, error);
}

function addMesaAjax(mesaRowData, success, error){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/evento/mesas/add?idEvento=" + idEvento,
        data: JSON.stringify(mesaRowData),
        dataType: 'json',
        success: function (mesa) {
            success(mesa);
            $.ajax({
                url: "/evento/distribucion/tipoMesaModal?mesaId=" + mesa.id + "&numero=" + mesa.numero + "&personas=" + mesa.personas + "&ninyos=" + mesa.ninyos,
                success: function (data) {
                    anyadirMesaToCanvas(mesa.id, mesa.numero, mesa.personas, mesa.ninyos, 150, 100, data);
                }
            });

        },
        error: error
    });
}

function anyadirClicked(mesaId, numero, personas, ninyos, anyadirButton){
    $.ajax({
        url: "/evento/distribucion/tipoMesaModal?mesaId=" + mesaId + "&numero=" + numero + "&personas=" + personas + "&ninyos=" + ninyos,
        success: function (data) {
            anyadirMesaToCanvas(mesaId, numero, personas, ninyos, 150, 100, data);
        }
    });
}

function onDeleteRow(datatable, rowdata, success, error){
    toggleLoadingSpinner($("#deleteRowBtn"));

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
                    deleteObject(object);
                }
            });
        },
        error: error
    });
}

function onEditRow(datatable, rowdata, success, error){
    toggleLoadingSpinner($("#editRowBtn"));

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

    let totalNinyos = api
        .column(5, {search:'applied'})
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
    $(api.column(5).footer()).html(totalNinyos);

    if (isEventoIndividual){
        $(api.column(6).footer()).html(((totalPagados / rows) * 100).toFixed(2) + "%");
    }

}

function updateMesaOnCanvas(mesa){
    canvas.getObjects().forEach(function(object) {
        if (object.mesaId === mesa.id){
            let mesaId = object.mesaId;
            let numero = mesa.numero;
            let personas = mesa.personas;
            let ninyos = mesa.ninyos;
            let top = object.top;
            let left = object.left;
            let tipo = object._objects[0].type;

            if(tipo === 'rect'){
                canvas.remove(object);
                addRectangleTable(mesaId, numero, personas, ninyos, top, left);
            }
            else{
                if(personas > 4 && personas <= 11) {
                    canvas.remove(object);
                    addCircleTable(mesaId, numero, personas, ninyos, top, left);
                }
                else{
                    canvas.remove(object);
                    addRectangleTable(mesaId, numero, personas, ninyos, top, left);
                }
            }
        }
    });
}

function anyadirMesaToCanvas(mesaId, numero, personas, ninyos, top, left, htmlModal){
    let tipoMesaModal = "#distribucionTipoMesaModal";

    if (personas > 4 && personas <= 11) {
        $("#distribucionTipoMesaModalHolder").html(htmlModal);
        $(tipoMesaModal).modal("show");
    }
    else{
        addRectangleTable(mesaId, numero, personas, ninyos, top, left);
    }
    $(tipoMesaModal).modal("hide");
}

function cerrarInvitadosClicked(invitadosMayores, invitadosNinyos){
    let mesaSeleccionada = mesasDt.rows({ selected: true }).data()[0];
    mesaSeleccionada.personas = invitadosMayores.toString();
    mesaSeleccionada.ninyos = invitadosNinyos.toString();

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
    let json = canvas.toJSON(['mesaId', 'numero', 'personas', 'ninyos']);

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
    let guardarButton = $("#guardarButton");
    toggleLoadingSpinner(guardarButton);

    let json = canvas.toJSON(['mesaId', 'numero', 'personas', 'ninyos']);

    $.ajax({
        url: "/evento/distribucion/guardar?idEvento=" + idEvento,
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json; charset=utf-8",
        success: function () {
            toggleLoadingSpinner(guardarButton);
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

function addCircleTable(mesaId, numero, personas, ninyos, top, left) {
    let circle = new fabric.Circle({
        radius: radioRedonda,
        fill : 'white',
        stroke: 'black',
        strokeWidth: 1,
        originX: 'center',
        originY: 'center'
    });

    insertTextToObject(mesaId, numero, personas, ninyos, top, left, circle);
}

function addRectangleTable(mesaId, numero, personas, ninyos, top, left) {
    let tableLength = calcularLongitudMesaLarga(personas);

    let rect = new fabric.Rect({
        width : anchuraLarga,
        height : tableLength,
        fill : 'white',
        stroke: 'black',
        strokeWidth: 1,
        originX: 'center',
        originY: 'center'
    });

    insertTextToObject(mesaId, numero, personas, ninyos, top, left, rect);
}

function insertTextToObject(mesaId, numero, personas, ninyos, top, left, objectToInsert){
    let text;

    if (ninyos > 0) {
        text = new fabric.Text("T-" + numero + "\n" + personas + "p" + "\n" + ninyos + "x", {
            fontSize: 12,
            originX: 'center',
            originY: 'center'
        });
    }
    else{
        text = new fabric.Text("T-" + numero + "\n" + personas + "p", {
            fontSize: 12,
            originX: 'center',
            originY: 'center'
        });
    }


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
    group['ninyos'] = ninyos;

    addObjectToCanvas(group);
}

function calcularLongitudMesaLarga(personas){
    let mesas = calcularLargasApoyos(personas);
    return (mesas.largas * alturaLarga) + (mesas.apoyos * alturaApoyo)
}

function calcularLargasApoyos(personas){
    let personasUltimaMesa = personas % 6;

    if (personas <= 2){
        return {'largas' : 0, 'apoyos': 2}
    }
    else if (personas > 2 && personas <= 6){
        return {'largas' : 1, 'apoyos': 0}
    }
    else if (personasUltimaMesa > 0 && personasUltimaMesa <= 2){
        return {'largas' : Math.ceil(personas / 6) - 1, 'apoyos': 1}
    }
    else{
        return {'largas' : Math.ceil(personas / 6), 'apoyos': 0}
    }
}

function addObjectToCanvas(object){
    canvas.add(object);
    canvas.renderAll();
    guardarDistribucion();
    disableAnyadirButton(object.mesaId, true);
}

function changeTableType(table){
    let tableType = table._objects[0].type;
    let mesaId = table.mesaId;
    let numero = table.numero;
    let personas = table.personas;
    let ninyos = table.ninyos;
    let top = table.top;
    let left = table.left;

    canvas.remove(table);

    if(tableType === 'rect'){
        addCircleTable(mesaId, numero, personas, ninyos, top, left);
    }
    else{
        addRectangleTable(mesaId, numero, personas, ninyos, top, left);
    }
}

function loadCanvas(){
    canvas.loadFromJSON(distribucion,function(){
        canvas.renderAll.bind(canvas);

        let objects = canvas.getObjects();

        objects.forEach(function(object) {

            disableAnyadirButton(object.mesaId, true);

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
    });
    loadBackgroundImage();
}

function clickDeleteIcon(eventData, transform) {
    let object = transform.target;

    deleteObject(object);
}

function clickReverseIcon(eventData, transform){
    let object = transform.target;

    let height = object.height;
    let width = object.width;
    let height1 = object._objects[0].height;
    let width1 = object._objects[0].width;
    let tipo = object._objects[0].type;

    console.log(object);

    if(tipo === 'rect'){
        object._objects[0].width = height1;
        object._objects[0].height = width1;
        object.width = height;
        object.height = width;

        console.log(object)
        canvas.renderAll();
    }
}

function deleteObject(object){
    canvas.remove(object);
    canvas.renderAll();
    guardarDistribucion();
    disableAnyadirButton(object.mesaId, false);
}

function renderIconDelete(ctx, left, top, styleOverride, fabricObject) {
    let size = this.cornerSize;
    ctx.save();
    ctx.translate(left, top);
    ctx.rotate(fabric.util.degreesToRadians(fabricObject.angle));
    ctx.drawImage(imgDelete, -size/2, -size/2, size, size);
    ctx.restore();
}

function renderIconReverse(ctx, left, top, styleOverride, fabricObject) {
    let size = this.cornerSize;
    ctx.save();
    ctx.translate(left, top);
    ctx.rotate(fabric.util.degreesToRadians(fabricObject.angle));
    ctx.drawImage(imgReverse, -size/2, -size/2, size, size);
    ctx.restore();
}

function loadBackgroundImage(){
    canvas.setBackgroundImage(null, function(){
        fabric.Image.fromURL("./../images/" + sala + "Background.png", (img) => {
            canvas.setBackgroundImage(img);
            canvas.renderAll();
        });
    });
}

function disableAnyadirButton(mesaId, disable){
    $("table > tbody > tr > td:nth-child(5) > button[mesaId='" + mesaId + "']").prop("disabled", disable);
}