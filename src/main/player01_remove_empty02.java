package main;

import core.LoadFileChooser;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

 public class player01_remove_empty02
 {
     public static void main(String[] args)
     {
         try
         {
             //System.out.println(LoadFileChooser.file);
             FileInputStream is=new FileInputStream(LoadFileChooser.file);
             HSSFWorkbook wb = new HSSFWorkbook(is);
             HSSFSheet sheet = wb.getSheetAt(0);
             boolean stop = false;
             boolean nonBlankRowFound;
             short c;
             HSSFRow lastRow = null;
             HSSFCell cell1 = null;
             while (stop == false) {
                 nonBlankRowFound = false;
                 lastRow =sheet.getRow(sheet.getLastRowNum());	
                 
                 for (c = lastRow.getFirstCellNum(); c <= lastRow.getLastCellNum(); c++) {
                     cell1 = lastRow.getCell(c);
                     
                     if (cell1 != null && lastRow.getCell(c).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
                         nonBlankRowFound = true;
                     }
                 }
                 if (nonBlankRowFound == true) 
                 {
                     stop = true;
                     System.out.println("all empty rows have been deleted");
                     //System.out.println("Physical Row Number: "+sheet.getPhysicalNumberOfRows());
                     //System.out.println("");
                 }
                 else
                 {
                     sheet.removeRow(lastRow);
                     System.out.println("Deleted Physical Row Number: "+sheet.getPhysicalNumberOfRows());
                 }
             }
             FileOutputStream fileOut = new FileOutputStream(LoadFileChooser.file);
             wb.write(fileOut);
             fileOut.close();
        
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
 }