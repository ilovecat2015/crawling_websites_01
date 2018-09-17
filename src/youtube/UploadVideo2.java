/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtube;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static core.LoadFileChooser.file;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;
import core.LoadFileChooser;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import main.player01_excel02;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Demo of uploading a video to a user's account using the YouTube Data API (V3) with OAuth2 for
 * authorization.
 *
 *  TODO: PLEASE NOTE, YOU MUST ADD YOUR VIDEO FILES TO THE PROJECT FOLDER TO UPLOAD THEM WITH THIS
 * APPLICATION!
 *
 * @author Jeremy Walker
 */
public class UploadVideo2 {
    private static String videoID1=null;
    public static String getvideoID()  {return videoID1;}
    public static void setvideoID(String videoID) {videoID1 = videoID;}
    
    

  /** Global instance of the HTTP transport. */
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();

  /** Global instance of Youtube object to make all API requests. */
  private static YouTube youtube;

  /* Global instance of the format used for the video being uploaded (MIME type). */
  private static String VIDEO_FILE_FORMAT = "video/*";

  /**
   * Authorizes the installed application to access user's protected data.
   *
   * @param scopes list of scopes needed to run youtube upload.
   */
  private static Credential authorize(List<String> scopes) throws Exception {

    // Load client secrets.
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, UploadVideo2.class.getResourceAsStream("/resources/client_secret.json"));

    // Checks that the defaults have been replaced (Default = "Enter X here").
    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
      System.out.println(
          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=youtube"
          + "into youtube-cmdline-uploadvideo-sample/src/main/resources/client_secret.json");
      System.exit(1);
    }

    // Set up file credential store.
    FileCredentialStore credentialStore = new FileCredentialStore(
        new File(System.getProperty("user.home"), ".credentials/youtube-api-uploadvideo.json"),
        JSON_FACTORY);

    //System.out.println(System.getProperty("user.home")+ ".credentials/youtube-api-uploadvideo.json");
    
    // Set up authorization code flow.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes).setCredentialStore(credentialStore)
        .build();

    // Build the local server and bind it to port 9000
    LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8080).build();

    // Authorize.
    return new AuthorizationCodeInstalledApp(flow, localReceiver).authorize("user");
  }

  /**
   * Uploads user selected video in the project folder to the user's YouTube account using OAuth2
   * for authentication.
   *
   * @param args command line args (not used).
   */
  public static void main(String[] args) {

    // Scope required to upload to YouTube.
    List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");

    try {
      // Authorization.
      Credential credential = authorize(scopes);

      // YouTube object used to make all API requests.
      youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
          "youtube-cmdline-uploadvideo-sample").build();

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
                                System.out.println(st);
                                Element link=doc.createElement("Link");
                                link.appendChild(doc.createTextNode(st));
                                test.appendChild(link);
                            
                            }else if (st.startsWith("C:")&& (st.endsWith(".mp4")||(st.endsWith(".flv")||(st.endsWith(".mov"))))){
                                System.out.println(st);
                                File videoFile1 = new File(st);
                                String videoID=uploadvideo(videoFile1);

                                Element mp4=doc.createElement("MP4");
                                mp4.appendChild(doc.createTextNode(st));
                                test.appendChild(mp4);

                                Element ID=doc.createElement("VideoID");
                                ID.appendChild(doc.createTextNode(videoID));
                                test.appendChild(ID);
                                
                            }
                            else if (st.startsWith("TVB")){
                            
                                Element block=doc.createElement("Block");
                                block.appendChild(doc.createTextNode(st));
                                test.appendChild(block);
                            }
                            
                            
                            else 
                            {
                                System.out.println(st);
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
            StreamResult streamResult=new StreamResult(new File(System.getProperty("user.home")+"\\Desktop\\new\\result.xml"));
            transformer.transform(source, streamResult);
            player01_excel02.main(null); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Video has been uploaded to Youtube successfully");
        System.out.println("");
        }
        
    } catch (GoogleJsonResponseException e) {
      System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
          + e.getDetails().getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("IOException: " + e.getMessage());
      e.printStackTrace();
    } catch (Throwable t) {
      System.err.println("Throwable: " + t.getMessage());
      t.printStackTrace();
    }
  }


  public static String uploadvideo(File videoFile) throws FileNotFoundException, IOException{
  
        // We get the user selected local video file to upload.
      //File videoFile = getVideoFromUser();
      System.out.println("You chose " + videoFile + " to upload.");

      // Add extra information to the video before uploading.
      Video videoObjectDefiningMetadata = new Video();

      /*
       * Set the video to public, so it is available to everyone (what most people want). This is
       * actually the default, but I wanted you to see what it looked like in case you need to set
       * it to "unlisted" or "private" via API.
       */
      VideoStatus status = new VideoStatus();
      //status.setPrivacyStatus("public");
      status.setPrivacyStatus("private");
      
      videoObjectDefiningMetadata.setStatus(status);

      // We set a majority of the metadata with the VideoSnippet object.
      VideoSnippet snippet = new VideoSnippet();

      /*
       * The Calendar instance is used to create a unique name and description for test purposes, so
       * you can see multiple files being uploaded. You will want to remove this from your project
       * and use your own standard names.
       */
      Calendar cal = Calendar.getInstance();
      //snippet.setTitle("Test Upload via Java on " + cal.getTime());
      snippet.setTitle(videoFile.getName());
      snippet.setDescription(
          "Video uploaded via YouTube Data API V3 using the Java library " + "on " + cal.getTime());

      // Set your keywords.
      
      //List<String> tags = new ArrayList<String>();
      //tags.add("test");
      //tags.add("example");
      //tags.add("java");
      //tags.add("YouTube Data API V3");
      //tags.add("erase me");
      //snippet.setTags(tags);
      

      // Set completed snippet to the video object.
      videoObjectDefiningMetadata.setSnippet(snippet);

      InputStreamContent mediaContent = new InputStreamContent(
          VIDEO_FILE_FORMAT, new BufferedInputStream(new FileInputStream(videoFile)));
      mediaContent.setLength(videoFile.length());

      /*
       * The upload command includes: 1. Information we want returned after file is successfully
       * uploaded. 2. Metadata we want associated with the uploaded video. 3. Video file itself.
       */
      YouTube.Videos.Insert videoInsert = youtube.videos().insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent);

      // Set the upload type and add event listener.
      MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

      /*
       * Sets whether direct media upload is enabled or disabled. True = whole media content is
       * uploaded in a single request. False (default) = resumable media upload protocol to upload
       * in data chunks.
       */
      
      
      // this sentence will affect the program to show insufficient data written
      //uploader.setDirectUploadEnabled(false);

      uploader.setDirectUploadEnabled(true);
      MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
        public void progressChanged(MediaHttpUploader uploader) throws IOException {
          switch (uploader.getUploadState()) {
            case INITIATION_STARTED:
                
              System.out.println("==================  Video-Start uploading  ==================");  
              System.out.println("Initiation Started");
              break;
            case INITIATION_COMPLETE:
              System.out.println("Initiation Completed");
              break;
            case MEDIA_IN_PROGRESS:
              System.out.println("==================  Video-Start uploading  =================="); 
              System.out.println("Upload in progress");
              System.out.println("Upload percentage: " + uploader.getProgress());
              break;
            case MEDIA_COMPLETE:
              System.out.println("Upload Completed!");
              break;
            case NOT_STARTED:
              System.out.println("Upload Not Started!");
              break;
          }
        }
      };
      uploader.setProgressListener(progressListener);

      /*
      System.out.println(uploader.getProgressListener());
      System.out.println(uploader.getInitiationRequestMethod());
      System.out.println(uploader.getUploadState());
      System.out.println(uploader.getTransport());
      System.out.println(uploader.getChunkSize());
      System.out.println(uploader.getInitiationHeaders());
      System.out.println(uploader.getMediaContent().getEncoding());
      System.out.println(uploader.getMediaContent().getLength());
      System.out.println(uploader.getMediaContent().getType());
      System.out.println(uploader.getMetadata().getEncoding());
      System.out.println(uploader.getMetadata().getLength());
      System.out.println(uploader.getMetadata().getType());
      
      System.out.println(uploader.getProgress());
      */
      
      // Execute upload.
      Video returnedVideo = videoInsert.execute();

      // Print out returned results.
      System.out.println("Video Id: " + returnedVideo.getId());
      System.out.println("Title: " + returnedVideo.getSnippet().getTitle());
      //System.out.println("Tags: " + returnedVideo.getSnippet().getTags());
      System.out.println("Privacy Status: " + returnedVideo.getStatus().getPrivacyStatus());
      //System.out.println("Video Count: " + returnedVideo.getStatistics().getViewCount());
      //System.out.println("==================  Video-Finished uploading  ==================");  
  
     return returnedVideo.getId();
  }   
  
  
    public static void main(String filename) {

    // Scope required to upload to YouTube.
    List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");

    try {
      // Authorization.
      Credential credential = authorize(scopes);

      // YouTube object used to make all API requests.
      youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
          "youtube-cmdline-uploadvideo-sample").build();

        System.out.println(filename);
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
                                System.out.println(st);
                                Element link=doc.createElement("Link");
                                link.appendChild(doc.createTextNode(st));
                                test.appendChild(link);
                            
                            }else if (st.startsWith("C:")&& (st.endsWith(".mp4")||(st.endsWith(".flv")||(st.endsWith(".mov"))))){
                                System.out.println(st);
                                File videoFile1 = new File(st);
                                String videoID=uploadvideo(videoFile1);

                                Element mp4=doc.createElement("MP4");
                                mp4.appendChild(doc.createTextNode(st));
                                test.appendChild(mp4);

                                Element ID=doc.createElement("VideoID");
                                ID.appendChild(doc.createTextNode(videoID));
                                test.appendChild(ID);
                                setvideoID(videoID);
                            }
                            else if (st.startsWith("TVB")){
                            
                                Element block=doc.createElement("Block");
                                block.appendChild(doc.createTextNode(st));
                                test.appendChild(block);
                            }
                            
                            
                            else 
                            {
                                System.out.println(st);
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
            StreamResult streamResult=new StreamResult(new File(System.getProperty("user.home")+"\\Desktop\\new\\screen_result.xml"));
            transformer.transform(source, streamResult);
            player01_excel02.main1(System.getProperty("user.home")+"\\Desktop\\new\\screen_result.xml"); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Video has been uploaded to Youtube successfully");
        System.out.println("");
        
        
    } catch (GoogleJsonResponseException e) {
      System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
          + e.getDetails().getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("IOException: " + e.getMessage());
      e.printStackTrace();
    } catch (Throwable t) {
      System.err.println("Throwable: " + t.getMessage());
      t.printStackTrace();
    }
  }

 

  
    
        public static void uploadVideo(File videoFile1) {

    // Scope required to upload to YouTube.
    List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");

    try {
      // Authorization.
      Credential credential = authorize(scopes);

      // YouTube object used to make all API requests.
      youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
          "youtube-cmdline-uploadvideo-sample").build();
  
            try
            {
                             //   System.out.println(st);
                              //  File videoFile1 = new File(st);
                                //System.out.println(videoFile1);
                                String videoID=uploadvideo(videoFile1);
                                setvideoID(videoID);
 
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        //System.out.println("Video has been uploaded to Youtube successfully");
        System.out.println("==================  Video-Finished uploading  ==================");  
        System.out.println("");
        
        
    } catch (GoogleJsonResponseException e) {
      System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
          + e.getDetails().getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("IOException: " + e.getMessage());
      e.printStackTrace();
    } catch (Throwable t) {
      System.err.println("Throwable: " + t.getMessage());
      t.printStackTrace();
    }
  }

 
    
    
    
}