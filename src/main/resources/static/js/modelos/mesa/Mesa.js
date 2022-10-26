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

    addToCanvas(canvas) {
        throw new Error('Method "addToCanvas()" must be implemented.');
    }
}