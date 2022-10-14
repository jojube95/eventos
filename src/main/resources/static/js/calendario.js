let calendar;
let language = document.documentElement.lang === 'ca' ? 'ca' : 'es';

document.addEventListener('DOMContentLoaded', function() {
    const tipoColor = new Map([
        ['boda', 'FireBrick'],
        ['comunion', 'DeepSkyBlue'],
        ['eventoComunal', 'DarkSeaGreen'],
        ['eventoIndividual', 'DarkGreen'],
        ['bautizo', 'DarkKhaki'],
        ['pruebas', 'DarkGrey']
    ]);

    let calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        editable: true,
        selectable: true,
        locale: language,
        firstDay: 1,
        eventSources: [
            {
                events: function(fetchInfo, successCallback) {
                    successCallback(
                        eventos.map(function(eventEl) {
                            return {
                                id: eventEl.id,
                                title: eventEl.titulo + " " + eventEl.personas.mayores + "p",
                                start: new Date(eventEl.fecha).toISOString().split('T')[0],
                                color: tipoColor.get(eventEl.tipo.value),
                                eventDisplay: 'block'
                            }
                        })
                    )
                },
                textColor: 'white'
            }
        ],
        eventClick: function(info) {
            let eventoId = info.event.id;
            location.href = "/eventoVer?eventoId=" + eventoId;
        },
        eventDrop: function(info) {
            let eventoId = info.event.id;
            let nuevaFecha = info.event.start.toDateString();

            ajaxCall("GET", "/evento/updateFecha", {eventoId: eventoId, fecha: nuevaFecha}, {}, function () {
                $("#confirmModal").modal("show");
            });
        }
    });
    calendar.render();
});

function verClicked(eventoId){
    location.href = "/eventoVer?eventoId=" + eventoId;
}

function modificarClicked(eventoId){
    location.href = "/eventoUpdate?eventoId=" + eventoId;
}

function goToDate(date){
    calendar.gotoDate(date);
}