/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

/**
 *
 * @author Jonathan
 */
public class player01_excel_removecol {
    
    
        public static void main(String[] args) throws Exception {
                //Instantiating a Workbook object
Workbook workbook = new Workbook("C:\\Users\\Jonathan\\Desktop\\Book3.xls");

//Accessing the first worksheet in the Excel file
Worksheet worksheet = workbook.getWorksheets().get(0);
//Deleting a column from the worksheet at 2nd position
worksheet.getCells().deleteColumns(0,1,true);


//Saving the modified Excel file in default (that is Excel 2000) format
workbook.save("C:\\Users\\Jonathan\\Desktop\\Book4.xls");
        }
        
                public static void removecol(String fileIn, int colnum) throws Exception {
                //Instantiating a Workbook object
Workbook workbook = new Workbook(fileIn);

//Accessing the first worksheet in the Excel file
Worksheet worksheet = workbook.getWorksheets().get(0);
//Deleting a column from the worksheet at 2nd position
worksheet.getCells().deleteColumns(colnum,1,true);


//Saving the modified Excel file in default (that is Excel 2000) format
workbook.save(fileIn);
        }


}
