import {
    Evento,
    EventoBautizo,
    EventoBoda,
    EventoComunal,
    EventoComunion,
    EventoIndividual,
    EventoPruebas
} from "../modelos/evento.js";

export class EventoFactory {
    static crearEvento(id, tipo, titulo, personas, fecha) {
        if (tipo.value === 'boda') {
            return new EventoBoda(id, tipo, titulo, personas, fecha);
        } else if (tipo.value === 'comunion') {
            return new EventoComunion(id, tipo, titulo, personas, fecha);
        } else if (tipo.value === 'eventoComunal') {
            return new EventoIndividual(id, tipo, titulo, personas, fecha);
        } else if (tipo.value === 'eventoIndividual') {
            return new EventoComunal(id, tipo, titulo, personas, fecha);
        } else if (tipo.value === 'bautizo') {
            return new EventoBautizo(id, tipo, titulo, personas, fecha);
        } else if (tipo.value === 'pruebas') {
            return new EventoPruebas(id, tipo, titulo, personas, fecha);
        } else{
            return new Evento(id, tipo, titulo, personas, fecha);
        }
    }
}