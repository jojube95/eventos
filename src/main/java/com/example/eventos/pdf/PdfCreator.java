package com.example.eventos.pdf;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.mesa.Mesa;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfCreator {
    private static final Font catFont = new Font(Font.FontFamily.HELVETICA, 18,
            Font.BOLD);
    private static final Font redFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.NORMAL, BaseColor.RED);
    private static final Font subFont = new Font(Font.FontFamily.HELVETICA, 16,
            Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.HELVETICA, 12,
            Font.BOLD);


    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    public static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    public static void addTitle(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();

        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Listado", catFont));

        addEmptyLine(preface, 2);

        document.add(preface);
    }

    public static void addContent(Document document, Mesa mesa) throws DocumentException {
        Paragraph preface = new Paragraph();

        preface.add(new Paragraph("Mesa: " + mesa.getNumero()));

        addEmptyLine(preface, 1);

        document.add(preface);
    }

    public static void addTable(Document document, java.util.List<Invitado> invitados) throws DocumentException {
        Paragraph preface = new Paragraph();

        PdfPTable table = new PdfPTable(2);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Invitado"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Descripción"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (Invitado invitado : invitados) {
            table.addCell(invitado.getNombre());
            table.addCell(invitado.getDescripcion());
        }

        preface.add(table);

        document.add(preface);
    }

    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
