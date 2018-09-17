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
import main.player01_excel03;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class ClearVideo {

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
                                //System.out.println("Link: "+st);

                            
                            }else if (st.startsWith("C:")&& (st.endsWith(".mp4")||(st.endsWith(".flv")||(st.endsWith(".mov"))))){
                                //System.out.println("MP4 Link: "+st);
    

                            }
                            else if (st.length()==11) {
                                   System.out.print("Video ID: "+st);
                                   
                                   String ar1[]=clearvideo(st);
                                   System.out.print(" has been "+ar1[0]);
                                   //System.out.println("\n-------------------------------------------------------------\n");

                            }
                            else 
                            {
                                //System.out.println("Filmname: "+st);

                            }
                    
                            //break;
                    }
                }
                System.out.println("");
            }
            file.close();
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Finished deleting the videos");
        }
      
  } 
 
  public static String[] clearvideo(String videoID){
      
      List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");
      String ar[]=new String[1];
       
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
              YouTube.Videos.Delete videoDelete=youtube.videos().delete(videoID);
              videoDelete.execute();
             

              ar[0]="deleted";
 
          }   
      }
      catch (GoogleJsonResponseException e) {
          e.printStackTrace();
          System.err.println("There was a service error: " + e.getDetails().getCode() + " : "+ e.getDetails().getMessage());
      }
      catch (Throwable t) {
          t.printStackTrace();
      }
      return ar;
  }
}

