import {EventoFactory} from "./factories/evento/EventoFactory.js";

window.goToDate = goToDate;

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
                            let evento = EventoFactory.crearEvento(eventEl.id, eventEl.tipo, eventEl.titulo, eventEl.personas, eventEl.fecha, eventEl.descripcion);
                            return {
                                id: evento.id,
                                title: evento.getCalendarioTitulo(),
                                descripcion: evento.descripcion,
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


    const calendarControlBtns = Array.from(
        document.querySelectorAll('.fc-header-toolbar button')
    );

    const handleNewInterval = (e) => {
        const allEvents = Array.from(document.querySelectorAll('.fc-event-main'));

        allEvents.forEach((singleEvent, index) => {
            singleEvent.dataset.toggle = 'tooltip';

            singleEvent.dataset.title = getEventDescription(singleEvent, index);
        });
        $('[data-toggle="tooltip"]').tooltip();
    }
    calendarControlBtns.forEach((singleControlBtn) => {
        singleControlBtn.addEventListener('click', handleNewInterval);
    });
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

function getEventDescription(calendarEvent, index){
    let evento = getFullCalendarEvent(index);

    return evento.extendedProps.descripcion;
}

function getFullCalendarEvent(index){
    return getViewEvents().at(index);
}

function getViewEvents(){
    let firstCalendarDay = calendar.view.activeStart;
    let lastCalendarDay = calendar.view.activeEnd;

    return calendar.getEvents().filter(event => (new Date(event.start) >= firstCalendarDay && new Date(event.start) < lastCalendarDay));
}