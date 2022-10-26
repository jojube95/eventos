import {EventoBoda} from "../../modelos/evento/EventoBoda.js";
import {EventoComunion} from "../../modelos/evento/EventoComunion.js";
import {EventoIndividual} from "../../modelos/evento/EventoIndividual.js";
import {EventoComunal} from "../../modelos/evento/EventoComunal.js";
import {EventoBautizo} from "../../modelos/evento/EventoBautizo.js";
import {EventoPruebas} from "../../modelos/evento/EventoPruebas.js";
import {Evento} from "../../modelos/evento/Evento.js";

export class EventoFactory {
    static crearEvento(id, tipo, titulo, personas, fecha) {
        if (tipo.value === 'boda') {
            return new EventoBoda(id, tipo, titulo, personas, fecha);
        } else if (tipo.value === 'comunion') {
            return new EventoComunion(id, tipo, titulo, personas, fecha);
        } else if (tipo.value === 'eventoIndividual') {
            return new EventoIndividual(id, tipo, titulo, personas, fecha);
        } else if (tipo.value === 'eventoComunal') {
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