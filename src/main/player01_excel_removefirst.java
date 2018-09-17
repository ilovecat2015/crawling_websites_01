/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

    

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class player01_excel_removefirst {

	public static void main(String[] args) throws IOException {
		
        FileInputStream file = new FileInputStream(new File("C:\\Users\\Jonathan\\Desktop\\Book3.xls"));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int rowIndex = 0;
	    int lastRowNum=sheet.getLastRowNum();
	    if(rowIndex>=0&&rowIndex<lastRowNum){
	        sheet.shiftRows(rowIndex+1,lastRowNum, -1);
	    }
	    if(rowIndex==lastRowNum){
	        HSSFRow removingRow=sheet.getRow(rowIndex);
	        if(removingRow!=null){
	            sheet.removeRow(removingRow);
	        }
	    }
	    
  
	    OutputStream out = new FileOutputStream(new File("C:\\Users\\Jonathan\\Desktop\\Book4.xls"));
	    workbook.write(out);
	    out.flush();
	    out.close();
	   
	
}
	
	public static void main(String fileIn) throws IOException {
		
        FileInputStream file = new FileInputStream(new File(fileIn));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int rowIndex = 0;
	    int lastRowNum=sheet.getLastRowNum();
	    if(rowIndex>=0&&rowIndex<lastRowNum){
	        sheet.shiftRows(rowIndex+1,lastRowNum, -1);
	    }
	    if(rowIndex==lastRowNum){
	        HSSFRow removingRow=sheet.getRow(rowIndex);
	        if(removingRow!=null){
	            sheet.removeRow(removingRow);
	        }
	    }
	    
  
	    OutputStream out = new FileOutputStream(new File(fileIn));
	    workbook.write(out);
	    out.flush();
	    out.close();
	   
	
}
	
}