package youtube;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static core.LoadFileChooser.file;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoContentDetails;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.common.collect.Lists;
import core.LoadFileChooser;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.io.FileInputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import main.player01_deletesheet;
import main.player01_excel03;
import main.player01_sorting;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class check_myuploads1 {

  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static YouTube youtube;

  private static Credential authorize(List<String> scopes) throws Exception {
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
        //JSON_FACTORY, check_myuploads1.class.getResourceAsStream("/resources/client_secret.json"));
        JSON_FACTORY, UploadVideo.class.getResourceAsStream("/resources/client_secret.json"));
        
        
    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
      System.out.println(
          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=youtube"
          + "into youtube-cmdline-myuploads-sample/src/main/resources/client_secret.json");
      System.exit(1);
    }

    FileCredentialStore credentialStore = new FileCredentialStore(new File(System.getProperty("user.home"), ".credentials/youtube-api-myuploads.json"),JSON_FACTORY);
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes).setCredentialStore(credentialStore).build();
    LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8080).build();
    return new AuthorizationCodeInstalledApp(flow, localReceiver).authorize("user");
  }

  public static void main(String[] args) {
      

      
      
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));
        
        FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
        //fd.setDirectory("C:\\");
        fd.setDirectory(System.getProperty("user.home")+"\\Desktop\\");
        fd.setFile("*.xls");
        fd.setVisible(true);
        String filename1 = fd.getFile();
        
        file = new File (fd.getDirectory ()+ fd.getFile ());
        
        if (filename1 == null){
            System.out.println("You cancelled the choice");
        }
        else
        {
            File file1=LoadFileChooser.file;
            
            try
            {
            FileInputStream file = new FileInputStream(file1);

                        
              //export to xml
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document doc=documentBuilder.newDocument();
            Element orders=doc.createElement("Orders");
            doc.appendChild(orders);
            Element order=doc.createElement("Order");
            orders.appendChild(order);
            
            //Create Workbook instance holding reference to .xls file
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                
                Element test=doc.createElement("Test");
                order.appendChild(test);
                
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            break;
                        case Cell.CELL_TYPE_STRING:
                            String st=null;	 
                            st=cell.getStringCellValue();
                            //check the string starts with http
            
   
                            if (st.startsWith("http")==true) {
                                System.out.println("Link: "+st);
                                Element link=doc.createElement("Link");
                                link.appendChild(doc.createTextNode(st));
                                test.appendChild(link);
                            
                            }else if (st.startsWith("C:")&& (st.endsWith(".mp4")||(st.endsWith(".flv")||(st.endsWith(".mov"))))){
                                System.out.println("MP4 Link: "+st);
    

                                Element mp4=doc.createElement("MP4");
                                mp4.appendChild(doc.createTextNode(st));
                                test.appendChild(mp4);

                            }
                            else if (st.startsWith("TVB")){
                            
                                Element block=doc.createElement("Block");
                                block.appendChild(doc.createTextNode(st));
                                test.appendChild(block);
                            }
                                                        
                            else if (st.length()==11) {
                                   //System.out.println(st);
                                   Element ID=doc.createElement("VideoID");
                                   ID.appendChild(doc.createTextNode(st));
                                   test.appendChild(ID);

                                   System.out.println(st);
                                   
                                   String ar1[]=check_myuploads1(st);
                                   System.out.println("video title = "+ar1[0]);
                                   System.out.println("video id = "+ar1[1]);
                                   System.out.println("video status = "+ar1[2]);
                                   System.out.println("video rejected reason = "+ar1[3]);
                                   System.out.println("video rejected region = "+ar1[4]);
                                   System.out.println("video thumbnails = "+ar1[5]);
                                   System.out.println("video html = "+ar1[6]);
                                   //System.out.println("\n-------------------------------------------------------------\n");
                                  
                                   Element title=doc.createElement("Title");
                                   title.appendChild(doc.createTextNode(ar1[0]));
                                   test.appendChild(title);
                               
                                   Element status=doc.createElement("Status");
                                   status.appendChild(doc.createTextNode(ar1[2]));
                                   test.appendChild(status);
                              
                                   Element reason=doc.createElement("Reason");
                                   reason.appendChild(doc.createTextNode(ar1[3]));
                                   test.appendChild(reason);
                                   
                                   Element region=doc.createElement("Region");
                                   region.appendChild(doc.createTextNode(ar1[4]));
                                   test.appendChild(region);
                                                 
                                   Element youtube_link=doc.createElement("Youtube_link");
                                   youtube_link.appendChild(doc.createTextNode(ar1[5]));
                                   test.appendChild(youtube_link);

                            }
                            else 
                            {
                                System.out.println("Filmname: "+st);
                                Element title1=doc.createElement("Filmname");
                                title1.appendChild(doc.createTextNode(st));
                                test.appendChild(title1);
                            }
                    
                            //break;
                    }
                }
                System.out.println("");
            }
            file.close();
            
             //export to xml	
            TransformerFactory transformerFactory=TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(doc);
            StreamResult streamResult=new StreamResult(new File(System.getProperty("user.home")+"\\Desktop\\new\\screen_result1.xml"));
            transformer.transform(source, streamResult);
            player01_excel03.main(null); 
            player01_sorting.main(null);
            player01_deletesheet.main(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Finished updating the video status");
        }
      
  } 
 
   public static void main1(String filename) {
      
        
        file = new File (filename);
        
            File file1=LoadFileChooser.file;
            
            try
            {
            FileInputStream file = new FileInputStream(file1);

                        
              //export to xml
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document doc=documentBuilder.newDocument();
            Element orders=doc.createElement("Orders");
            doc.appendChild(orders);
            Element order=doc.createElement("Order");
            orders.appendChild(order);
            
            //Create Workbook instance holding reference to .xls file
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                
                Element test=doc.createElement("Test");
                order.appendChild(test);
                
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            break;
                        case Cell.CELL_TYPE_STRING:
                            String st=null;	 
                            st=cell.getStringCellValue();
                            //check the string starts with http
            
   
                            if (st.startsWith("http")==true) {
                                System.out.println("Link: "+st);
                                Element link=doc.createElement("Link");
                                link.appendChild(doc.createTextNode(st));
                                test.appendChild(link);
                            
                            }else if (st.startsWith("C:")&& (st.endsWith(".mp4")||(st.endsWith(".flv")||(st.endsWith(".mov"))))){
                                System.out.println("MP4 Link: "+st);
    

                                Element mp4=doc.createElement("MP4");
                                mp4.appendChild(doc.createTextNode(st));
                                test.appendChild(mp4);

                            }
                            else if (st.startsWith("TVB")){
                            
                                Element block=doc.createElement("Block");
                                block.appendChild(doc.createTextNode(st));
                                test.appendChild(block);
                            }
                                                        
                            else if (st.length()==11) {
                                   //System.out.println(st);
                                   Element ID=doc.createElement("VideoID");
                                   ID.appendChild(doc.createTextNode(st));
                                   test.appendChild(ID);

                                   String ar1[]=null;
                                   ar1=check_myuploads1(st);
                                   System.out.println("video title = "+ar1[0]);
                                   System.out.println("video id = "+ar1[1]);
                                   System.out.println("video status = "+ar1[2]);
                                   System.out.println("video rejected reason = "+ar1[3]);
                                   System.out.println("video rejected region = "+ar1[4]);
                                   System.out.println("video thumbnails = "+ar1[5]);
                                   System.out.println("video html = "+ar1[6]);
                                   //System.out.println("\n-------------------------------------------------------------\n");
                                  
                                   Element title=doc.createElement("Title");
                                   title.appendChild(doc.createTextNode(ar1[0]));
                                   test.appendChild(title);
                               
                                   Element status=doc.createElement("Status");
                                   status.appendChild(doc.createTextNode(ar1[2]));
                                   test.appendChild(status);
                              
                                   Element reason=doc.createElement("Reason");
                                   reason.appendChild(doc.createTextNode(ar1[3]));
                                   test.appendChild(reason);
                                   
                                   Element region=doc.createElement("Region");
                                   region.appendChild(doc.createTextNode(ar1[4]));
                                   test.appendChild(region);
                                                 
                                   Element youtube_link=doc.createElement("Youtube_link");
                                   youtube_link.appendChild(doc.createTextNode(ar1[5]));
                                   test.appendChild(youtube_link);

                            }
                            else if (st.startsWith("1")|| st.startsWith("2")|| st.startsWith("3")|| st.startsWith("4")|| st.startsWith("5")|| st.startsWith("6")|| st.startsWith("7")|| st.startsWith("8")|| st.startsWith("9")|| st.startsWith("0")) {                            
                                System.out.println("Duration: "+st);
                                Element title1=doc.createElement("Duration");
                                title1.appendChild(doc.createTextNode(st));
                                test.appendChild(title1);
                            }
                            else 
                            {
                                System.out.println("Filmname: "+st);
                                Element title1=doc.createElement("Filmname");
                                title1.appendChild(doc.createTextNode(st));
                                test.appendChild(title1);
                            }
                    
                            //break;
                    }
                }
                System.out.println("");
            }
            file.close();
            
             //export to xml	
            TransformerFactory transformerFactory=TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(doc);
            StreamResult streamResult=new StreamResult(new File(System.getProperty("user.home")+"\\Desktop\\new\\screen_result1.xml"));
            transformer.transform(source, streamResult);
            player01_excel03.main(null); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Finished updating the video status");
        
      
  } 
  
  
  public static String[] check_myuploads1(String videoID){
      
      List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");
      String ar[]=null;
      ar=new String[7];
       
      try {
          Credential credential = authorize(scopes);
          youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("youtube-cmdline-myuploads-sample").build();
          
          YouTube.Channels.List channelRequest = youtube.channels().list("contentDetails");
          channelRequest.setMine("true");
          channelRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
          ChannelListResponse channelResult = channelRequest.execute();
          
          List<Channel> channelsList = channelResult.getItems();
          
          if (channelsList != null) {
              String uploadPlaylistId =channelsList.get(0).getContentDetails().getRelatedPlaylists().getUploads();
              List<PlaylistItem> playlistItemList = new ArrayList<PlaylistItem>();
              YouTube.PlaylistItems.List playlistItemRequest =youtube.playlistItems().list("contentDetails,snippet");
              playlistItemRequest.setPlaylistId(uploadPlaylistId);
              playlistItemRequest.setFields("items(contentDetails/videoId,snippet/title,snippet/publishedAt),nextPageToken,pageInfo");
              String nextToken = "";
              
              YouTube.Videos.List videoRequest=youtube.videos().list(videoID, "player, contentDetails, snippet, status");
              VideoListResponse videoResult=videoRequest.execute();
              
              //System.out.println(" video title  = " + videoResult.getItems().get(0).getSnippet().getTitle());
              //System.out.println(" video id  = " + videoID);
              //System.out.println(" video published at  = " + videoResult.getItems().get(0).getSnippet().getPublishedAt());
              
              List<Video> videosList = videoResult.getItems();
              String result=videosList.get(0).getStatus().getUploadStatus();
              //System.out.println(" video upload status  = " + result);
              
              String reason;
              if (videoResult.getItems().get(0).getStatus().getRejectionReason()==null){
              reason="null";
              }else{
              reason=videoResult.getItems().get(0).getStatus().getRejectionReason();
              }
              
              //System.out.println("video reject reason  = " + videoResult.getItems().get(0).getStatus().getRejectionReason());
              
              String region;
              if (videoResult.getItems().get(0).getContentDetails().getRegionRestriction()==null){
                  //System.out.println(" video restricted region = " + videoResult.getItems().get(0).getContentDetails().getRegionRestriction());
                  region="null";
                  //System.out.println(rejected);
              }else{
                  //System.out.println(" video restricted region = " + videoResult.getItems().get(0).getContentDetails().getRegionRestriction().toString().substring(2, 9));
                  region=videoResult.getItems().get(0).getContentDetails().getRegionRestriction().toString().substring(2, 9);
                  //System.out.println(rejected);
              }
              //System.out.println(" video thumbnails  = " + videoResult.getItems().get(0).getSnippet().getThumbnails().get("standard").getUrl());    
              String str=videoResult.getItems().get(0).getPlayer().getEmbedHtml().substring(videoResult.getItems().get(0).getPlayer().getEmbedHtml().lastIndexOf("www"));
              //System.out.println(" video html  = " + str.substring(0, 33));
              //System.out.println("\n-------------------------------------------------------------\n");

              ar[0]=videoResult.getItems().get(0).getSnippet().getTitle();
              ar[1]=videoID;
              ar[2]=result;
              ar[3]=reason;             
              ar[4]=region;
              ar[5]=videoResult.getItems().get(0).getSnippet().getThumbnails().get("standard").getUrl();
              ar[6]=str.substring(0, 33);
             
          }   
      }
      catch (GoogleJsonResponseException e) {
          e.printStackTrace();
          System.err.println("There was a service error: " + e.getDetails().getCode() + " : "+ e.getDetails().getMessage());
      }
      catch (Throwable t) {
          System.err.println("Videos are under matching the video fingerprint database in Youtube");
          //t.printStackTrace();
      }
      return ar;
  }
}

