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
                            return {
                                id: eventEl['id'],
                                title: eventEl['titulo'],
                                start: new Date(eventEl['fecha']).toISOString().split('T')[0]
                            }
                        })
                    )
                },
                color: 'blue',
                textColor: 'white'
            }
        ],
        eventClick: function(info) {
            let eventoId = info.event.id;

            $.ajax({
                url: "/evento/" + eventoId,
                success: function (data) {
                    $("#eventDetailModalHolder").html(data);
                    $("#eventDetailModal").modal("show");
                }
            })
        },
        eventDrop: function(info) {
            let eventoId = info.event.id;
            let nuevaFecha = info.event.start.toDateString();

            $.ajax({
                url: "/evento/updateFecha?id=" + eventoId + "&fecha=" + nuevaFecha,
                success: function () {
                    $("#confirmModal").modal("show");
                }
            });
        }
    });
    calendar.render();
});

function verClicked(eventoId){
    location.href = "/verEvento?eventoId=" + eventoId;
}

function modificarClicked(eventoId){
    location.href = "/updateEvento?eventoId=" + eventoId;
}

function goToDate(date){
    calendar.gotoDate(date);
}