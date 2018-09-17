package main;

import com.aspose.cells.CellArea;
import com.aspose.cells.DataSorter;
import com.aspose.cells.SortOrder;
import com.aspose.cells.Workbook;
import core.LoadFileChooser;
import core.config.RecordConfig;
import java.io.File;

public class player01_sorting {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        // The path to the documents directory.
        //Instantiate a new Workbook object.
        
        
        File file= LoadFileChooser.file;
        //String str=file.getAbsolutePath();
        System.out.println(file);
        //File file=new File("C:\\Users\\Jonathan\\Desktop\\sources1_04-10-59.xls");
        String str=file.getAbsolutePath();
        Workbook workbook = new Workbook(str);
        //Get the workbook datasorter object.
        DataSorter sorter = workbook.getDataSorter();

        //Set the first order for datasorter object.
        sorter.setOrder1(SortOrder.ASCENDING);
        //Define the first key.
        sorter.setKey1(2);
        
        //Set the second order for datasorter object.
        //sorter.setOrder2(SortOrder.DESCENDING);
        //Define the second key.
        //sorter.setKey2(1);
        
        //Set the second order for datasorter object.
        //sorter.setOrder2(SortOrder.DESCENDING);
        //Define the second key.
        //sorter.setKey2(2);
       

        //Sort data in the specified data range (CellArea range: A1:B14)
        CellArea cellArea = new CellArea();
        cellArea.StartRow = 0;
        cellArea.StartColumn = 0;
        cellArea.EndRow = 1000;
        cellArea.EndColumn = 10;
        sorter.sort(workbook.getWorksheets().get(0).getCells(), cellArea);

        //Save the excel file.
        workbook.save(str);
        
        // Print message
        System.out.println("Sorting Done Successfully");	
    }
          private RecordConfig recConfig = null;
       public void main1(RecordConfig rc) throws Exception {
        // TODO Auto-generated method stub
        // The path to the documents directory.
        //Instantiate a new Workbook object.
          recConfig = rc;
        
        File file= recConfig.getLoadFile();
        //String str=file.getAbsolutePath();
        //System.out.println(file);
        //File file=new File("C:\\Users\\Jonathan\\Desktop\\sources1_04-10-59.xls");
        String str=file.getAbsolutePath();
        Workbook workbook = new Workbook(str);
        //Get the workbook datasorter object.
        DataSorter sorter = workbook.getDataSorter();

        //Set the first order for datasorter object.
        sorter.setOrder1(SortOrder.ASCENDING);
        //Define the first key.
        sorter.setKey1(2);
        
        //Set the second order for datasorter object.
        //sorter.setOrder2(SortOrder.DESCENDING);
        //Define the second key.
        //sorter.setKey2(1);
        
        //Set the second order for datasorter object.
        //sorter.setOrder2(SortOrder.DESCENDING);
        //Define the second key.
        //sorter.setKey2(2);
       

        
        //Sort data in the specified data range (CellArea range: A1:B14)
        CellArea cellArea = new CellArea();
        cellArea.StartRow = 0;
        cellArea.StartColumn = 0;
        cellArea.EndRow = 5000;
        cellArea.EndColumn = 6;
        sorter.sort(workbook.getWorksheets().get(0).getCells(), cellArea);

        //Save the excel file.
        workbook.save(str);
        
        // Print message
        System.out.println("Sorting Done Successfully");	
    }
    
}