package com.pablo.pse5.batch;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class BatchWriter extends AbstractItemWriter {

    Document document;

    @Override
    public void writeItems(List list) throws Exception {
        for (String g : (List<String>) list) {
            System.out.println(g);
            document.add(new Paragraph(g));
        }
    }

    @Override
    public void open(Serializable checkpoint) throws DocumentException, IOException {
        String DEST = System.getProperty("user.dir") + File.separator + "pdfDeMierda.pdf";
        System.out.println(DEST);
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();
        document = new Document();
        PdfWriter.getInstance(
                document, new FileOutputStream(DEST));
        document.open();
    }

    @Override
    public void close() throws DocumentException {
        document.close();
    }

}
