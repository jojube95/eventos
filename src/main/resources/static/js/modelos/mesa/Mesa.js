export class Mesa {
    constructor(id, eventoId, numero, personas, descripcion) {
        this.id = id;
        this.eventoId = eventoId;
        this.numero = numero;
        this.personas = personas;
        this.descripcion = descripcion;
    }

    getDataTableRowData() {
        return {id: this.id, eventoId: this.eventoId, numero: this.numero, mayores: this.personas.mayores, ninyos: this.personas.ninyos, descripcion: this.descripcion}
    }

    personasCabenEnRedonda() {
        return (this.getTotalPersonas() > 4 && this.getTotalPersonas() <= 11)
    }

    getTotalPersonas() {
        return Number(this.personas.mayores) + Number(this.personas.ninyos);
    }
}