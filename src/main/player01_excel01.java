package main;

import core.LoadFileChooser;
import static core.LoadFileChooser.file;
import core.config.RecordConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class player01_excel01 {

    public static void main(String argv[]) {
    	
        ArrayList<String> filmnames = new ArrayList<String>();
        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> mp4Names = new ArrayList<String>();

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(System.getProperty("user.home")+"\\Desktop\\new\\result.xml"));

            // normalize text representation
            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is :\" "+ doc.getDocumentElement().getNodeName() + "\"");
            NodeList itemlist = doc.getElementsByTagName("Test");
            int totalitems = itemlist.getLength();
            System.out.println("Total no of items : " + totalitems);

            for (int s = 0; s < itemlist.getLength() && (Element) ((Element) itemlist.item(s)).getElementsByTagName("Filmname").item(0)!=null ; s++) 
            {
            	
                Node firstitemNode = itemlist.item(s);
                if (firstitemNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element firstPersonElement = (Element) firstitemNode;
                    
                    NodeList filmnameList = firstPersonElement.getElementsByTagName("Filmname");
                    Element filmnameElement = (Element) filmnameList.item(0);
                    NodeList textfilmnameList = filmnameElement.getChildNodes();
                    System.out.println("Filmname : "+ ((Node) textfilmnameList.item(0)).getNodeValue().trim());
                    filmnames.add(((Node) textfilmnameList.item(0)).getNodeValue().trim());
                    
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("Link");
                    Element firstNameElement = (Element) firstNameList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("Link : "+ ((Node) textFNList.item(0)).getNodeValue().trim());
                    firstNames.add(((Node) textFNList.item(0)).getNodeValue().trim());
   
                    
                    NodeList mp4List = firstPersonElement.getElementsByTagName("MP4");
                    Element mp4Element = (Element) mp4List.item(0);
                    NodeList textmp4List = mp4Element.getChildNodes();
                    System.out.println("MP4 Link : "+ ((Node) textmp4List.item(0)).getNodeValue().trim());
                    mp4Names.add(((Node) textmp4List.item(0)).getNodeValue().trim());
                    System.out.println("");
                }// end of if clause
            }// end of for loop with s var
        } 
        catch (SAXParseException err) 
        {
            System.out.println("** Parsing error" + ", line "+ err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
        } 
        catch (SAXException e) 
        {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } 
        catch (Throwable t) 
        {
            t.printStackTrace();
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");

        sheet.setColumnWidth((short) 0, (short) (550 * 25));
        sheet.setColumnWidth((short) 1, (short) (800 * 25));
        sheet.setColumnWidth((short) 2, (short) (450 * 25));

        //HSSFRow row1 = sheet.createRow(0);
        //HSSFCell cell1 = row1.createCell((short) 0);
        //cell1.setCellValue("Filmname");
        //cell1 = row1.createCell((short) 1);
        //cell1.setCellValue("Link");
        //cell1 = row1.createCell((short) 2);

        Map<String, Object[]> data = new HashMap<String, Object[]>();

        for(int i=0;i<filmnames.size();i++)
        {
            data.put(i+"",new Object[]{filmnames.get(i),firstNames.get(i),mp4Names.get(i)});
        }
        Set<String> keyset = data.keySet();
        
        int rownum = 0;
         //int rownum = 1;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);

            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try {
            Date date= new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
            String removeext=FilenameUtils.removeExtension(LoadFileChooser.file.getName());
            String string=LoadFileChooser.file.getParent()+"\\"+removeext+"_"+dateFormat.format(date);
            File filename=new File(string+".xls");
            //System.out.println(filename);
           
            FileOutputStream out = new FileOutputStream(filename);
            //FileOutputStream out = new FileOutputStream(LoadFileChooser.file);
            //System.out.println(LoadFileChooser.file);
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// end of main
    
      private RecordConfig recConfig = null;
    public void main(RecordConfig rc) {
        recConfig = rc;
    	
        ArrayList<String> filmnames = new ArrayList<String>();
        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> mp4Names = new ArrayList<String>();

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(System.getProperty("user.home")+"\\Desktop\\new\\result.xml"));

            // normalize text representation
            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is :\" "+ doc.getDocumentElement().getNodeName() + "\"");
            NodeList itemlist = doc.getElementsByTagName("Test");
            int totalitems = itemlist.getLength();
            System.out.println("Total no of items : " + totalitems);

            for (int s = 0; s < itemlist.getLength() && (Element) ((Element) itemlist.item(s)).getElementsByTagName("Filmname").item(0)!=null ; s++) 
            {
            	
                Node firstitemNode = itemlist.item(s);
                if (firstitemNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element firstPersonElement = (Element) firstitemNode;
                    
                    NodeList filmnameList = firstPersonElement.getElementsByTagName("Filmname");
                    Element filmnameElement = (Element) filmnameList.item(0);
                    NodeList textfilmnameList = filmnameElement.getChildNodes();
                    System.out.println("Filmname : "+ ((Node) textfilmnameList.item(0)).getNodeValue().trim());
                    filmnames.add(((Node) textfilmnameList.item(0)).getNodeValue().trim());
                    
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("Link");
                    Element firstNameElement = (Element) firstNameList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("Link : "+ ((Node) textFNList.item(0)).getNodeValue().trim());
                    firstNames.add(((Node) textFNList.item(0)).getNodeValue().trim());
   
                    
                    NodeList mp4List = firstPersonElement.getElementsByTagName("MP4");
                    Element mp4Element = (Element) mp4List.item(0);
                    NodeList textmp4List = mp4Element.getChildNodes();
                    System.out.println("MP4 Link : "+ ((Node) textmp4List.item(0)).getNodeValue().trim());
                    mp4Names.add(((Node) textmp4List.item(0)).getNodeValue().trim());
                    System.out.println("");
                }// end of if clause
            }// end of for loop with s var
        } 
        catch (SAXParseException err) 
        {
            System.out.println("** Parsing error" + ", line "+ err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
        } 
        catch (SAXException e) 
        {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } 
        catch (Throwable t) 
        {
            t.printStackTrace();
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");

        sheet.setColumnWidth((short) 0, (short) (550 * 25));
        sheet.setColumnWidth((short) 1, (short) (800 * 25));
        sheet.setColumnWidth((short) 2, (short) (450 * 25));

        //HSSFRow row1 = sheet.createRow(0);
        //HSSFCell cell1 = row1.createCell((short) 0);
        //cell1.setCellValue("Filmname");
        //cell1 = row1.createCell((short) 1);
        //cell1.setCellValue("Link");
        //cell1 = row1.createCell((short) 2);

        Map<String, Object[]> data = new HashMap<String, Object[]>();

        for(int i=0;i<filmnames.size();i++)
        {
            data.put(i+"",new Object[]{filmnames.get(i),firstNames.get(i),mp4Names.get(i)});
        }
        Set<String> keyset = data.keySet();
        
        int rownum = 0;
         //int rownum = 1;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);

            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try {
            Date date= new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
            String removeext=FilenameUtils.removeExtension(LoadFileChooser.file.getName());
            String string=LoadFileChooser.file.getParent()+"\\"+removeext+"_"+dateFormat.format(date);
            File filename=new File(string+".xls");
            //System.out.println(filename);
                recConfig.setLoadFile(filename);
            FileOutputStream out = new FileOutputStream(filename);
            //FileOutputStream out = new FileOutputStream(LoadFileChooser.file);
            //System.out.println(LoadFileChooser.file);
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// end of main
}