package MyCustom;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.Vector;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XuLyFileExcel {

    public void xuatExcel(JTable tbl) {
     
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new MyCustom.MyFileChooser("export/");
            chooser.setDialogTitle("Lưu vào");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                // Láº¥y Ä‘Æ°á»�ng dáº«n vá»«a chá»�n + tÃªn tá»‡p
                String path = chooser.getSelectedFile().getPath();
                if (!path.contains(".xlsx")) {
                    path += ".xlsx";
                }

                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Sheet 1");

                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(IndexedColors.WHITE.getIndex());
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);
                headerCellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
                headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerCellStyle.setBorderTop(BorderStyle.THIN);
                headerCellStyle.setBorderBottom(BorderStyle.THIN);
                headerCellStyle.setBorderLeft(BorderStyle.THIN);
                headerCellStyle.setBorderRight(BorderStyle.THIN);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

                Row headerRow = sheet.createRow(0);

                // Tạo header
                for (int i = 0; i < dtm.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(dtm.getColumnName(i));
                    cell.setCellStyle(headerCellStyle);
                }

                Font contentFont = workbook.createFont();
                contentFont.setBold(false);
                contentFont.setFontHeightInPoints((short) 13);
                contentFont.setColor(IndexedColors.BLACK.getIndex());
                CellStyle contentCellStyle = workbook.createCellStyle();
                contentCellStyle.setFont(contentFont);
                contentCellStyle.setBorderTop(BorderStyle.THIN);
                contentCellStyle.setBorderBottom(BorderStyle.THIN);
                contentCellStyle.setBorderLeft(BorderStyle.THIN);
                contentCellStyle.setBorderRight(BorderStyle.THIN);

                for (int i = 0; i < dtm.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < dtm.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(dtm.getValueAt(i, j) + "");
                        cell.setCellStyle(contentCellStyle);
                        sheet.autoSizeColumn(j);
                    }
                }

                // Ghi file
                FileOutputStream fileOut = new FileOutputStream(path);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

                new MyDialog("Xuất file thành công!", MyDialog.SUCCESS_DIALOG);
            }
        } catch (Exception e) {
            new MyDialog("Xuất file thất bại !", MyDialog.ERROR_DIALOG);
        }
    }

    public void nhapExcel(JTable tbl) {
  
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new MyFileChooser("export/");
            chooser.setDialogTitle("Chọn file");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File fileSelected = chooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(fileSelected);
                BufferedInputStream bis = new BufferedInputStream(fis);

                XSSFWorkbook wb = new XSSFWorkbook(bis);
                Sheet sheet = wb.getSheetAt(0);

                DefaultTableModel dtmtbl = (DefaultTableModel) dtm;
                dtmtbl.setRowCount(0);
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Vector vec = new Vector();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        if (dtmtbl.getColumnCount() != row.getLastCellNum()) {
                            new MyDialog("Nhập file thất bại 1!", MyDialog.ERROR_DIALOG);
                            return;
                        }
                        Cell cell = row.getCell(j);
                        vec.add(cell.getStringCellValue());
                    }
                    dtmtbl.addRow(vec);
                }
                new MyDialog("Nhập file thành công!", MyDialog.SUCCESS_DIALOG);
            }
        } catch (Exception e) {
            new MyDialog("Nhập file thất bại 2!", MyDialog.ERROR_DIALOG);
            System.out.println(e);
        }
    }
    public void xuatPDF(JTable tb,String title){
        String path="";
        try {
            JFileChooser chooser = new MyFileChooser("export/");
            chooser.setDialogTitle("Lưu vào");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("PDF Files", "pdf");
            chooser.setFileFilter(fnef);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                // Láº¥y Ä‘Æ°á»�ng dáº«n vá»«a chá»�n + tÃªn tá»‡p
                path = chooser.getSelectedFile().getPath();
                if (!path.contains(".pdf")) {
                    path += ".pdf";
                }
            }
            Document pdfDoc=new Document();
            PdfWriter.getInstance(pdfDoc, new FileOutputStream(path));
            pdfDoc.open();
            pdfDoc.add(new Paragraph(title));
            pdfDoc.add(new Paragraph("Thời gian: "+java.time.LocalDate.now()));
            pdfDoc.add(new Paragraph("-----------------------------------"));
            int numcol=tb.getColumnCount();
            PdfPTable table=new PdfPTable(numcol);
            //PdfPCell cell= new PdfPCell();
//            cell.setColspan(8);
//            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
//            cell.setBackgroundColor(BaseColor.BLUE);

            //table.addCell(cell);
            for(int i=0;i<numcol;i++){
                table.addCell((String)tb.getColumnName(i)+"");
            }
            int numrow=tb.getRowCount();
            for(int i=0;i<numrow;i++){
                for(int j=0;j<numcol;j++)
                    table.addCell(tb.getValueAt(i, j)+"");
            }
            pdfDoc.add(table);
            pdfDoc.close();
            JOptionPane.showMessageDialog(null, "In thành công.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "In không thành công.");
        }
    }

}
