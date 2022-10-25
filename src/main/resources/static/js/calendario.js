import {EventoFactory} from "./factories/eventoFactory.js";

let calendar;
let language = document.documentElement.lang === 'ca' ? 'ca' : 'es';

document.addEventListener('DOMContentLoaded', function() {
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
                            let evento = EventoFactory.crearEvento(eventEl.id, eventEl.tipo, eventEl.titulo, eventEl.personas, eventEl.fecha);
                            return {
                                id: evento.id,
                                title: evento.getCalendarioTitulo(),
                                start: new Date(evento.fecha).toISOString().split('T')[0],
                                color: evento.getCalendarioColor(),
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