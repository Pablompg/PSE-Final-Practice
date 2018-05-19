package com.pablo.pse5.batch;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class BatchWriter extends AbstractItemWriter {

    Document document;
    PdfPTable table;
    PdfPCell cell;
    Date fecha = new Date();
    float columnWidth[] = new float[]{50,50,50,50,50};
    SimpleDateFormat formatoDia = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatoHorayMinuto = new SimpleDateFormat("HH:mm");
    SimpleDateFormat formatoHorayMinuto2 = new SimpleDateFormat("HH-mm");

    @Override
    public void writeItems(List list) throws Exception {
        for (SuscripcionesOfertaBatch g : (List<SuscripcionesOfertaBatch>) list) {
            cell = new PdfPCell(new Phrase(g.getNombreEmpresa(),new Font(FontFamily.HELVETICA, 10)));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(g.getNombreOferta(),new Font(FontFamily.HELVETICA, 10)));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(g.getEmailEmpresa(),new Font(FontFamily.HELVETICA, 10)));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(g.getNumSuscripciones()),new Font(FontFamily.HELVETICA, 10)));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(g.getIdOferta()),new Font(FontFamily.HELVETICA, 10)));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);

        }
    }

    @Override
    public void open(Serializable checkpoint) throws DocumentException, IOException {
        //Imprimimos por pantalla la ruta actual donde vamos a trabajar
        String DEST = System.getProperty("user.dir") + File.separator + "InformeDia"
                + formatoDia.format(fecha) + "Hora" + formatoHorayMinuto2.format(fecha) + ".pdf";
        System.out.println(DEST);
        //Generamos un nuevo documento PDF y lo abrimos para escribir en él
        document = new Document();
        PdfWriter.getInstance(
                document, new FileOutputStream(DEST));
        document.open();
        //Título del PDF
        Paragraph linea = new Paragraph("Informe", new Font(FontFamily.HELVETICA, 30, Font.BOLD));
        linea.setAlignment(Element.ALIGN_CENTER);
        document.add(linea);
        //Línea en blanco
        linea = new Paragraph(" ");
        document.add(linea);
        //Fecha del informe
        linea = new Paragraph(formatoDia.format(fecha) + " " + formatoHorayMinuto.format(fecha),
                new Font(FontFamily.HELVETICA, 11, Font.ITALIC));
        linea.setAlignment(Element.ALIGN_CENTER);
        document.add(linea);
        //Línea en blanco
        linea = new Paragraph(" ");
        document.add(linea);
        //Línea en blanco
        linea = new Paragraph(" ");
        document.add(linea);
        //Creamos una tabla de 5 columnas
        table = new PdfPTable(5);
        table.setWidthPercentage(document.getPageSize().getWidth()/5-15);
        //Creamos la primera fila
        cell = new PdfPCell(new Phrase("Nombre Empresa"));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new BaseColor(226, 226, 226));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Título Oferta"));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new BaseColor(226, 226, 226));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Email Empresa"));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new BaseColor(226, 226, 226));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nº de Suscripciones"));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new BaseColor(226, 226, 226));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Id Oferta"));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new BaseColor(226, 226, 226));
        cell.setPadding(10);
        table.addCell(cell);

    }

    @Override
    public void close() throws DocumentException {
        document.add(table);
        document.close();
    }

}
