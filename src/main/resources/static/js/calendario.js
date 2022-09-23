let calendar;
let language = document.documentElement.lang === 'ca' ? 'ca' : 'es';

document.addEventListener('DOMContentLoaded', function() {
    const tipoColor = new Map([
        ['Boda', 'FireBrick'],
        ['Comunion', 'DeepSkyBlue'],
        ['Evento comunal', 'DarkSeaGreen'],
        ['Evento individual', 'DarkGreen'],
        ['Bautizo', 'DarkKhaki'],
        ['Pruebas', 'DarkGrey']
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
                                id: eventEl['id'],
                                title: eventEl['titulo'] + " " + eventEl['personas'] + "p",
                                start: new Date(eventEl['fecha']).toISOString().split('T')[0],
                                color: tipoColor.get(eventEl.tipo),
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

            $.ajax({
                url: "/evento/updateFecha?eventoId=" + eventoId + "&fecha=" + nuevaFecha,
                success: function () {
                    $("#confirmModal").modal("show");
                }
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