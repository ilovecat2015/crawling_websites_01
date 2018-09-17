package main;

import core.LoadFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class player01_excel03 {

    public static void main(String argv[]) {
    	
        ArrayList<String> filmnames = new ArrayList<String>();
        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> mp4Names = new ArrayList<String>();
        ArrayList<String> blockNames = new ArrayList<String>();
        ArrayList<String> videoIdNames = new ArrayList<String>();
        ArrayList<String> durationNames = new ArrayList<String>();
        
        ArrayList<String> titleNames = new ArrayList<String>();
        ArrayList<String> statusNames = new ArrayList<String>();
        ArrayList<String> reasonNames = new ArrayList<String>();
        ArrayList<String> regionNames = new ArrayList<String>();
        ArrayList<String> youtube_linkNames = new ArrayList<String>();

        
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(System.getProperty("user.home")+"\\Desktop\\new\\screen_result1.xml"));

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
                    //System.out.println("Filmname : "+ ((Node) textfilmnameList.item(0)).getNodeValue().trim());
                    filmnames.add(((Node) textfilmnameList.item(0)).getNodeValue().trim());
                    
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("Link");
                    Element firstNameElement = (Element) firstNameList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    //System.out.println("Link : "+ ((Node) textFNList.item(0)).getNodeValue().trim());
                    firstNames.add(((Node) textFNList.item(0)).getNodeValue().trim());
   
                    
                    NodeList mp4List = firstPersonElement.getElementsByTagName("MP4");
                    Element mp4Element = (Element) mp4List.item(0);
                    NodeList textmp4List = mp4Element.getChildNodes();
                    //System.out.println("MP4 Link : "+ ((Node) textmp4List.item(0)).getNodeValue().trim());
                    mp4Names.add(((Node) textmp4List.item(0)).getNodeValue().trim());
                    
                                        
                    NodeList blockList = firstPersonElement.getElementsByTagName("Block");
                    Element blockElement = (Element) blockList.item(0);
                    NodeList textblockList = blockElement.getChildNodes();
                    //System.out.println("Block : "+ ((Node) textblockList.item(0)).getNodeValue().trim());
                    blockNames.add(((Node) textblockList.item(0)).getNodeValue().trim());
                    
                    NodeList videoList = firstPersonElement.getElementsByTagName("VideoID");
                    Element videoElement = (Element) videoList.item(0);
                    NodeList textvideoList = videoElement.getChildNodes();
                    //System.out.println("Video ID : "+ ((Node) textvideoList.item(0)).getNodeValue().trim());
                    videoIdNames.add(((Node) textvideoList.item(0)).getNodeValue().trim());
                    
                    NodeList titleList = firstPersonElement.getElementsByTagName("Title");
                    Element titleElement = (Element) titleList.item(0);
                    NodeList texttitleList = titleElement.getChildNodes();
                    //System.out.println("Title : "+ ((Node) texttitleList.item(0)).getNodeValue().trim());
                    titleNames.add(((Node) texttitleList.item(0)).getNodeValue().trim());
                    
                    NodeList statusList = firstPersonElement.getElementsByTagName("Status");
                    Element statusElement = (Element) statusList.item(0);
                    NodeList textstatusList = statusElement.getChildNodes();
                    //System.out.println("Status : "+ ((Node) textstatusList.item(0)).getNodeValue().trim());
                    statusNames.add(((Node) textstatusList.item(0)).getNodeValue().trim());
                    
                    NodeList reasonList = firstPersonElement.getElementsByTagName("Reason");
                    Element reasonElement = (Element) reasonList.item(0);
                    NodeList textreasonList = reasonElement.getChildNodes();
                    //System.out.println("Reason : "+ ((Node) textreasonList.item(0)).getNodeValue().trim());
                    reasonNames.add(((Node) textreasonList.item(0)).getNodeValue().trim());
                    
                    NodeList regionList = firstPersonElement.getElementsByTagName("Region");
                    Element regionElement = (Element) regionList.item(0);
                    NodeList textregionList = regionElement.getChildNodes();
                    //System.out.println("Region : "+ ((Node) textregionList.item(0)).getNodeValue().trim());
                    regionNames.add(((Node) textregionList.item(0)).getNodeValue().trim());
                    
                    NodeList youtube_linkList = firstPersonElement.getElementsByTagName("Youtube_link");
                    Element youtube_linkElement = (Element) youtube_linkList.item(0);
                    NodeList textyoutube_linkList = youtube_linkElement.getChildNodes();
                    //System.out.println("Youtube Link : "+ ((Node) textyoutube_linkList.item(0)).getNodeValue().trim());
                    youtube_linkNames.add(((Node) textyoutube_linkList.item(0)).getNodeValue().trim());
                    
                    NodeList durationList = firstPersonElement.getElementsByTagName("Duration");
                    Element durationElement = (Element) durationList.item(0);
                    NodeList textdurationList = durationElement.getChildNodes();
                    //System.out.println("Duration : "+ ((Node) textdurationList.item(0)).getNodeValue().trim());
                    durationNames.add(((Node) textdurationList.item(0)).getNodeValue().trim());
                    
                    
                    //System.out.println("");
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
        sheet.setColumnWidth((short) 3, (short) (200 * 25));
        sheet.setColumnWidth((short) 4, (short) (150 * 25));
        
        sheet.setColumnWidth((short) 5, (short) (200 * 25));
        sheet.setColumnWidth((short) 6, (short) (150 * 25));
        sheet.setColumnWidth((short) 7, (short) (150 * 25));
        sheet.setColumnWidth((short) 8, (short) (100 * 25));
        sheet.setColumnWidth((short) 9, (short) (400 * 25));
        sheet.setColumnWidth((short) 10, (short) (150 * 25));      
        
        //HSSFRow row1 = sheet.createRow(0);
        //HSSFCell cell1 = row1.createCell((short) 0);
        //cell1.setCellValue("Filmname");
        //cell1 = row1.createCell((short) 1);
        //cell1.setCellValue("Link");
        //cell1 = row1.createCell((short) 2);

        Map<String, Object[]> data = new HashMap<String, Object[]>();

        for(int i=0;i<filmnames.size();i++)
        {            
            data.put(i+"",new Object[]{filmnames.get(i),firstNames.get(i),mp4Names.get(i),blockNames.get(i),videoIdNames.get(i),titleNames.get(i),statusNames.get(i),reasonNames.get(i),regionNames.get(i),youtube_linkNames.get(i),durationNames.get(i)});
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
            //FileOutputStream out = new FileOutputStream(LoadFileChooser.file);
            //Date date= new Date();
            //SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
            //String removeext=FilenameUtils.removeExtension(LoadFileChooser.file.getName());
            //String string=LoadFileChooser.file.getParent()+"\\"+removeext+"_"+dateFormat.format(date);
            //File filename=new File(string+".xls");
            //System.out.println(filename);
            FileOutputStream out = new FileOutputStream(LoadFileChooser.file);
            System.out.println("Videos have been recorded in the following path: "+LoadFileChooser.file);
            

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