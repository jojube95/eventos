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
                    $("#eventModalHolder").html(data);
                    $("#eventModal").modal("show");
                }
            })
        },
        eventDrop: function(info) {
            console.log(info)
            alert(info.event.title + " was dropped on " + info.event.start.toISOString());

            if (!confirm("Are you sure about this change?")) {
                info.revert();
            }
        }
    });
    calendar.render();
});

function modificarClicked(eventoId){
    location.href = "/updateEvento?eventoId=" + eventoId;
}