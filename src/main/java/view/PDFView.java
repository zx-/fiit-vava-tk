/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.pdf.PdfWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import model.entity.Attendance;
import model.entity.Grade;
import model.entity.Subject;
import model.entity.User;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class PDFView extends AbstractView{
    
    private static Font headingsFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD);
    private static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.NORMAL);
    private static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.BOLD);
    
    
    public PDFView() {
        setContentType("application/pdf");
    }
 
    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }
         
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // IE workaround: write into byte array first.
        ByteArrayOutputStream baos = createTemporaryOutputStream();
 
        // Apply preferences and build metadata.
        Document document = newDocument();
        PdfWriter writer = newWriter(document, baos);
        prepareWriter(model, writer, request);
        buildPdfMetadata(model, document, request);
 
        // Build PDF document.
        document.open();
        buildPdfDocument(model, document, writer, request, response);
        document.close();
 
        // Flush to HTTP response.
        writeToResponse(response, baos);
    }
 
    protected Document newDocument() {
        return new Document(PageSize.A4);
    }
     
    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
        return PdfWriter.getInstance(document, os);
    }
     
    protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request)
            throws DocumentException {
 
        writer.setViewerPreferences(getViewerPreferences());
    }
     
    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }
     
    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
    }

    protected void buildPdfDocument(Map model, Document document,
            PdfWriter writer, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

            User s = (User) model.get("student");
            Collection<Grade> grades;
            Paragraph p;
            double a = 0;

            
            document.addTitle(s.getUsername()+" report");
        
            
            p = new Paragraph(s.getUsername()+" report\n",headingsFont);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            
            for(Subject sub:s.getClassRoom().getSubjects()){
                
                p = new Paragraph(sub.getName()+"\n",boldFont);
                document.add(p);

                p = new Paragraph("grades:",normalFont);
                p.setIndentationLeft(30);
                document.add(p);
                
                grades = s.getGradesBySubject(sub);
                
                p = new Paragraph(grades.toString(),normalFont);
                p.setIndentationLeft(30);
                document.add(p);

                for(Grade g:grades){
                
                    a+= g.getValue();
                
                }
                
                a = grades.isEmpty()?0:a/grades.size();
                
                p = new Paragraph("average:",normalFont);
                p.setIndentationLeft(30);
                document.add(p);
                
                p = new Paragraph(Double.toString(a),normalFont);
                p.setIndentationLeft(30);
                document.add(p);
                
                
            }
            
            int b = 0;
            for(Attendance att:s.getAttendances()){
            
                if(att.isWasPresent())
                    b++;
            
            }
            if(s.getAttendances().isEmpty())
                return;
            
            p = new Paragraph(
                    String.format("Student was present on %d lessons from %d. His attendance was %f%%.",
                            b,s.getAttendances().size(),((double)b)/s.getAttendances().size()),
                    normalFont);
            document.add(p);
            
           
            
    }


}