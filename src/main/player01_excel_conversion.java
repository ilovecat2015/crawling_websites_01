
package main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class player01_excel_conversion {
    private String outFn;
    private File inpFn;

    public player01_excel_conversion(File inpFn){
        this.outFn = inpFn + ".xls";
        this.inpFn = inpFn;
    }

    
	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\Jonathan\\Desktop\\sources4.xlsx");
        File outF;
        try {
            XSSFWorkbook wbIn = new XSSFWorkbook(in);
            outF = new File("C:\\Users\\Jonathan\\Desktop\\Book3.xls");
            if (outF.exists()) {
                outF.delete();
            }

            Workbook wbOut = new HSSFWorkbook();
            int sheetCnt = wbIn.getNumberOfSheets();
            for (int i = 0; i < sheetCnt; i++) {
                Sheet sIn = wbIn.getSheetAt(0);
                Sheet sOut = wbOut.createSheet(sIn.getSheetName());
                Iterator<Row> rowIt = sIn.rowIterator();
                while (rowIt.hasNext()) {
                    Row rowIn = rowIt.next();
                    Row rowOut = sOut.createRow(rowIn.getRowNum());

                    Iterator<Cell> cellIt = rowIn.cellIterator();
                    while (cellIt.hasNext()) {
                        Cell cellIn = cellIt.next();
                        Cell cellOut = rowOut.createCell(cellIn.getColumnIndex(), cellIn.getCellType());

                        switch (cellIn.getCellType()) {
                        case Cell.CELL_TYPE_BLANK: break;

                        case Cell.CELL_TYPE_BOOLEAN:
                            cellOut.setCellValue(cellIn.getBooleanCellValue());
                            break;

                        case Cell.CELL_TYPE_ERROR:
                            cellOut.setCellValue(cellIn.getErrorCellValue());
                            break;

                        case Cell.CELL_TYPE_FORMULA:
                            cellOut.setCellFormula(cellIn.getCellFormula());
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            cellOut.setCellValue(cellIn.getNumericCellValue());
                            break;

                        case Cell.CELL_TYPE_STRING:
                            cellOut.setCellValue(cellIn.getStringCellValue());
                            break;
                        }

                        {
                            CellStyle styleIn = cellIn.getCellStyle();
                            CellStyle styleOut = cellOut.getCellStyle();
                            styleOut.setDataFormat(styleIn.getDataFormat());
                        }cellOut.setCellComment(cellIn.getCellComment());

                        }
                }
            }
            OutputStream out = new BufferedOutputStream(new FileOutputStream(outF));
            try {
                wbOut.write(out);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
	
	
	
        
    
	public static String main(String fileIn, String fileOut) throws IOException {
        InputStream in = new FileInputStream(fileIn);
        File outF;
        try {
            XSSFWorkbook wbIn = new XSSFWorkbook(in);
            outF = new File(fileOut);
            if (outF.exists()) {
                outF.delete();
            }

            Workbook wbOut = new HSSFWorkbook();
            int sheetCnt = wbIn.getNumberOfSheets();
            for (int i = 0; i < sheetCnt; i++) {
                Sheet sIn = wbIn.getSheetAt(0);
                Sheet sOut = wbOut.createSheet(sIn.getSheetName());
                Iterator<Row> rowIt = sIn.rowIterator();
                while (rowIt.hasNext()) {
                    Row rowIn = rowIt.next();
                    Row rowOut = sOut.createRow(rowIn.getRowNum());

                    Iterator<Cell> cellIt = rowIn.cellIterator();
                    while (cellIt.hasNext()) {
                        Cell cellIn = cellIt.next();
                        Cell cellOut = rowOut.createCell(cellIn.getColumnIndex(), cellIn.getCellType());

                        switch (cellIn.getCellType()) {
                        case Cell.CELL_TYPE_BLANK: break;

                        case Cell.CELL_TYPE_BOOLEAN:
                            cellOut.setCellValue(cellIn.getBooleanCellValue());
                            break;

                        case Cell.CELL_TYPE_ERROR:
                            cellOut.setCellValue(cellIn.getErrorCellValue());
                            break;

                        case Cell.CELL_TYPE_FORMULA:
                            cellOut.setCellFormula(cellIn.getCellFormula());
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            cellOut.setCellValue(cellIn.getNumericCellValue());
                            break;

                        case Cell.CELL_TYPE_STRING:
                            cellOut.setCellValue(cellIn.getStringCellValue());
                            break;
                        }

                        {
                            CellStyle styleIn = cellIn.getCellStyle();
                            CellStyle styleOut = cellOut.getCellStyle();
                            styleOut.setDataFormat(styleIn.getDataFormat());
                        }cellOut.setCellComment(cellIn.getCellComment());

                        }
                }
            }
            OutputStream out = new BufferedOutputStream(new FileOutputStream(outF));
            try {
                wbOut.write(out);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
        return fileOut;
    }
	
        
	
}