let canvas;

let deleteIcon = "data:image/svg+xml,%3C%3Fxml version='1.0' encoding='utf-8'%3F%3E%3C!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'%3E%3Csvg version='1.1' id='Ebene_1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' width='595.275px' height='595.275px' viewBox='200 215 230 470' xml:space='preserve'%3E%3Ccircle style='fill:%23F44336;' cx='299.76' cy='439.067' r='218.516'/%3E%3Cg%3E%3Crect x='267.162' y='307.978' transform='matrix(0.7071 -0.7071 0.7071 0.7071 -222.6202 340.6915)' style='fill:white;' width='65.545' height='262.18'/%3E%3Crect x='266.988' y='308.153' transform='matrix(0.7071 0.7071 -0.7071 0.7071 398.3889 -83.3116)' style='fill:white;' width='65.544' height='262.179'/%3E%3C/g%3E%3C/svg%3E";

let img = document.createElement('img');
img.src = deleteIcon;

$(document).ready(function() {
    let columnDefs = [
        {
            data: "id",
            type: "hidden",
            visible: false
        },
        {
            data: "numero",
            orderable: false,
            unique: true

        },
        {
            data: "personas",
            orderable: false
        },
        {
            data: "anyadir",
            orderable: false
        },
    ];

    mesasDt = $('#mesas').DataTable({
        columns: columnDefs,
        order: [1, 'asc'],
        paging: false,
        info: false,
    });

    fabric.Object.prototype.controls.deleteControl = new fabric.Control({
        x: 0.5,
        y: -0.5,
        offsetY: 16,
        cursorStyle: 'pointer',
        mouseUpHandler: deleteObject,
        render: renderIcon,
        cornerSize: 24
    });

    canvas = new fabric.Canvas('canvas');

    loadCanvas();
});

function anyadirClicked(mesaId, numero, personas){
    $.ajax({
        url: "/evento/distribucion/tipoMesaModal?mesaId=" + mesaId + "&numero=" + numero + "&personas=" + personas,
        success: function (data) {
            if (personas > 4 && personas <= 11) {
                $("#distribucionTipoMesaModalHolder").html(data);
                $("#distribucionTipoMesaModal").modal("show");
            }
            else if(personas <= 4){
                addRectangleTable(mesaId, numero, personas);
            }
            else{
                addRectangleTable(mesaId, numero, personas);
            }
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
        success: function (data) {
            $("#confirmModal").modal("show");
        },
        error: function (err) {
            alert(err);
        }
    });
}

function addCircleTable(mesaId, numero, personas) {
    let circle = new fabric.Circle({
        radius: 30,
        fill : 'white',
        stroke: 'black',
        strokeWidth: 1,
        originX: 'center',
        originY: 'center'
    });

    let text = new fabric.Text("T-" + numero + "\n" + personas + "p", {
        fontSize: 12,
        originX: 'center',
        originY: 'center'
    });

    let group = new fabric.Group([ circle, text ], {
        left: 150,
        top: 100,
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

    canvas.add(group);
    canvas.renderAll();

    $("#distribucionTipoMesaModal").modal("hide");
    disableAnyadirButton(mesaId, true);
}

function addRectangleTable(mesaId, numero, personas) {
    let numeroLargas = Math.ceil(personas / 6);
    let personasUltimaMesa = personas % 6;
    let tableLength;
    personasUltimaMesa > 0 && personasUltimaMesa <=2 ? tableLength = ((numeroLargas - 1) * 80) + 20 : tableLength = numeroLargas * 80;
    let numeroDivisiones = numeroLargas - 1;

    let rect = new fabric.Rect({
        width : 50,
        height : tableLength,
        fill : 'white',
        stroke: 'black',
        strokeWidth: 1,
        originX: 'center',
        originY: 'center'
    });

    let text = new fabric.Text("T-" + numero + "\n" + personas + "p", {
        fontSize: 12,
        originX: 'center',
        originY: 'center'
    });

    let group = new fabric.Group([ rect, text ], {
        left: 150,
        top: 100,
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

    let groupTop = Number(group.top);
    let groupLeft = Number(group.left);


    for (let i = 0; i < numeroDivisiones; i++) {
        let line = new fabric.Line([groupTop + 50, groupLeft + 30 + (i * 80), groupTop + 100, groupLeft + 30 + (i * 80)], {
            strokeDashArray: [5, 5],
            stroke: 'grey'
        });

        group.addWithUpdate(line);
    }

    canvas.add(group);
    canvas.renderAll();

    $("#distribucionTipoMesaModal").modal("hide");
    disableAnyadirButton(mesaId, true);
}

function deleteObject(eventData, transform) {
    let target = transform.target;
    let canvas = target.canvas;

    canvas.remove(target);
    canvas.requestRenderAll();

    disableAnyadirButton(target.mesaId, false);
}

function renderIcon(ctx, left, top, styleOverride, fabricObject) {
    let size = this.cornerSize;
    ctx.save();
    ctx.translate(left, top);
    ctx.rotate(fabric.util.degreesToRadians(fabricObject.angle));
    ctx.drawImage(img, -size/2, -size/2, size, size);
    ctx.restore();
}

function loadCanvas(){
    canvas.loadFromJSON(distribucion,canvas.renderAll.bind(canvas));

    let objects = canvas.getObjects();

    objects.forEach(function(object, i) {
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
        disableAnyadirButton(object.mesaId, true);
    });
}

function disableAnyadirButton(mesaId, disabled){
    $("button[mesaId='" + mesaId + "']").prop("disabled", disabled);
}