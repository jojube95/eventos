document.addEventListener('DOMContentLoaded', function() {
    let calendarEl = document.getElementById('calendar');
    let calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        editable: true,
        selectable: true,
        locale: 'es',
        eventSources: [
            {
                events: function(fetchInfo, successCallback, failureCallback) {
                    successCallback(
                        eventos.map(function(eventEl) {
                            return {
                                id: eventEl['id'],
                                title: eventEl['tipo'] + '-' + eventEl['horario'],
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
                success: function (data) {
                    $("#confirmModal").modal("show");
                }
            });
        }
    });
    calendar.render();
});

function modificarClicked(eventoId){
    location.href = "/updateEvento?eventoId=" + eventoId;
}