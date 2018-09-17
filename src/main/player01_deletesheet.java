package main;

import core.LoadFileChooser;
import core.config.RecordConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class player01_deletesheet {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        FileInputStream fileStream = new FileInputStream(LoadFileChooser.file);
        POIFSFileSystem fsPoi = new POIFSFileSystem(fileStream);
        
        HSSFWorkbook workbook = new HSSFWorkbook(fsPoi);
        int index = 0;
        HSSFSheet sheet = workbook.getSheet("Evaluation Warning");
        if(sheet != null)
        {
            index = workbook.getSheetIndex(sheet);
            workbook.removeSheetAt(index);
        }
        FileOutputStream output = new FileOutputStream(LoadFileChooser.file);
        workbook.write(output);
        output.close();
        System.out.println("sheet has been deleted");
    }
          private RecordConfig recConfig = null;
       public void main1(RecordConfig rc) throws FileNotFoundException, IOException {
                   recConfig = rc;
        // TODO Auto-generated method stub
             File file= recConfig.getLoadFile();
        FileInputStream fileStream = new FileInputStream(file);
        POIFSFileSystem fsPoi = new POIFSFileSystem(fileStream);
        
        HSSFWorkbook workbook = new HSSFWorkbook(fsPoi);
        int index = 0;
        HSSFSheet sheet = workbook.getSheet("Evaluation Warning");
        if(sheet != null)
        {
            index = workbook.getSheetIndex(sheet);
            workbook.removeSheetAt(index);
        }
        FileOutputStream output = new FileOutputStream(file);
        workbook.write(output);
        output.close();
        System.out.println("sheet has been deleted");
    }
       
       
           public void removesheet(String fileIn) throws IOException {
        // TODO Auto-generated method stub
        FileInputStream fileStream = new FileInputStream(new File(fileIn));
        POIFSFileSystem fsPoi = new POIFSFileSystem(fileStream);
        
        HSSFWorkbook workbook = new HSSFWorkbook(fsPoi);
        int index = 0;
        HSSFSheet sheet = workbook.getSheet("Evaluation Warning (1)");
        if(sheet != null)
        {
            index = workbook.getSheetIndex(sheet);
            workbook.removeSheetAt(index);
        }
       
        HSSFSheet sheet1 = workbook.getSheet("Evaluation Warning");
        if(sheet1 != null)
        {
            index = workbook.getSheetIndex(sheet1);
            workbook.removeSheetAt(index);
        }
        FileOutputStream output = new FileOutputStream(new File(fileIn));
        workbook.write(output);
        output.close();
        System.out.println("sheet has been deleted");
    }


}
