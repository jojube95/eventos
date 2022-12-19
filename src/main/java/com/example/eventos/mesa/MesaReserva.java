package com.example.eventos.mesa;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.personas.Personas;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("mesa")
@TypeAlias("MesaReserva")
public class MesaReserva extends Mesa{
    private String representante;
    private boolean pagado;

    public MesaReserva(String id, String eventoId, Personas personas, int numero, String descripcion, String representante, boolean pagado) {
        super(id, eventoId, personas, numero, descripcion);
        this.representante = representante;
        this.pagado = pagado;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    @Override
    public void addTableCells(PdfPTable table, List<Invitado> invitados) {
        table.addCell(new PdfPCell(new Phrase(""))).setBorder(Rectangle.NO_BORDER);
    }

    @Override
    public String toString() {
        String res = "Mesa " + numero + ". "  + representante + ". " + personas.toString();

        if (descripcion != null && !descripcion.isEmpty()) {
            res += ". Descripción: " + descripcion;
        }

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MesaReserva mesa = (MesaReserva) o;
        return Objects.equals(id, mesa.id) && personas.getMayores() == mesa.personas.getMayores() && personas.getNinyos() == mesa.personas.getNinyos()
                && numero == mesa.numero && pagado == mesa.pagado && Objects.equals(eventoId, mesa.eventoId)
                && Objects.equals(representante, mesa.representante) && Objects.equals(descripcion, mesa.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventoId, representante, personas, numero, pagado, descripcion);
    }
}
