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
            console.log(info)
            alert('Event: ' + info.event.id + '-' + info.event.title);
            info.el.style.borderColor = 'red';
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