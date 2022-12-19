package com.example.eventos.pdf;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.mesa.Mesa;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.List;

public class PdfCreator {
    private static final Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);

    public static void addMetaData(Document document) {
        document.addTitle("Listado");
        document.addSubject("Listado invitados");
        document.addAuthor("Serratella");
        document.addCreator("Serratella");
    }

    public static void addTitle(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();

        preface.add(new Paragraph("Listado", titleFont));

        document.add(preface);
    }

    public static void addTable(Document document, Mesa mesa, List<Invitado> invitados) throws DocumentException {
        Paragraph preface = new Paragraph();

        addEmptyLine(preface, 1);

        PdfPTable table = new PdfPTable(1);
        table.setKeepTogether(true);

        PdfPCell c1 = new PdfPCell(mesa.generatePhrase());
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(Rectangle.NO_BORDER);

        table.addCell(c1);

        table.setHeaderRows(1);

        mesa.addTableCells(table, invitados);

        preface.add(table);

        document.add(preface);
    }

    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
