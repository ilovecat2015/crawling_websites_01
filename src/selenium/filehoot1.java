/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;

import org.sikuli.script.ImagePath;
import org.sikuli.script.ImagePath.PathEntry;
import static org.sikuli.script.ImagePath.find;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

/**
 *
 * @author Jonathan
 */
public class filehoot1 {

    public static void main(String[] args) throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        //Desktop.getDesktop().browse(new URI("http://filehoot.com/34b9tk68z8iw"));
         Debug.on(3);
        Screen s=new Screen();
        System.out.println("Start searching");
        int count=1;
        boolean start=true;
        boolean start1=false;

        ImagePath.add("selenium.filehoot/resources");
        System.out.println(ImagePath.getBundleFolder());
        ImagePath.setBundlePath("");
        System.out.println(ImagePath.getBundleFolder());
        
//System.out.println(ImagePath.getBundlePath());

      /*  
        PathEntry path1 = null;
        
        System.out.println("**************");

        List<ImagePath.PathEntry> paths=ImagePath.getPaths();
        for(ImagePath.PathEntry path:paths){
        System.out.println(path);
        path1=path;
        }
System.out.println("**************"); 
System.out.println(path1.toString());

String path=path1.toString();


        URL file1=find("filehoot_photo1.png");
        System.out.println(file1.getFile());
        System.out.println(file1.getPath());
 
        Pattern pimage1=new Pattern(file1.getPath());
*/
        //ImagePath.add(filehoot.class.getCanonicalName() + "/resources");
  
        Thread.sleep(5000);
        while(start){
            //Thread.sleep(1000);
               System.out.println("searching1");
               //System.out.println(s.find(new Pattern("src\\resources\\filehoot_photo1.png").similar((float) 0.95)));


       
               System.out.println((s.exists(new Pattern("filehoot_photo1.png").exact())));

                                try{
      
        Debug.user("Match TVB: %s", s.find("filehoot_photo1.png"));

        Debug.user("Match TVB: %s", s.find("filehoot_photo2.png"));
        Debug.user("Match TVB: %s", s.find("filehoot_filenotfound"));

        }catch(FindFailed e)
        {e.printStackTrace();
        }
         
         
               System.out.println(new Pattern("filehoot_photo1.png").getFileURL());
               System.out.println("**************"); 
               
               
               //System.out.println((s.exists(new Pattern("filehoot_photo1.png").exact())));
               System.out.println(s.exists(new Pattern("filehoot_photo1.png").similar(0.95f)));
               System.out.println(new Pattern("filehoot_photo1.png").getFileURL());
               System.out.println("**************"); 
               
               System.out.println((s.exists(new Pattern("filehoot_photo2.png").exact())));
               System.out.println(new Pattern("filehoot_photo2.png").getFileURL());
               System.out.println("**************"); 
               System.out.println((s.exists(new Pattern("filehoot_filenotfound").exact())));
               System.out.println(new Pattern("filehoot_filenotfound").getFileURL());
                 System.out.println("**************");              
               //System.out.println(s.exists(pimage.exact()));
               //System.out.println(pimage.getFileURL());
               //System.out.println("**************"); 
               //System.out.println(s.exists(pimage1.exact()));
               //System.out.println(pimage1.getFileURL());
               //System.out.println("**************"); 
                
               
                if (s.exists(new Pattern("filehoot_photo1.png").exact())!=null)
                {
                    System.out.println("filehoot_photo1.png");
                    Debug.user("Match TVB: %s", s.find("filehoot_photo1.png"));
                    s.find("filehoot_photo1.png");
                    s.click("filehoot_photo1.png");
                    start1=true;
                    start=false;
                }
                /*
                if (s.exists(pimage1.exact())!=null)
                {
                    System.out.println("pimage");
                    Debug.user("Match TVB: %s", s.find(pimage1));
                    s.find(pimage1);
                    s.click(pimage1);
                    start1=true;
                    start=false;
                }*/
                
                /*
                if (s.exists(pimage.exact())!=null)
                {
                    System.out.println("pimage");
                    Debug.user("Match TVB: %s", s.find(pimage));
                    s.find(pimage);
                    s.click(pimage);
                    start1=true;
                    start=false;
                }
                
                
                
                if (s.exists(photo4)!=null)
                {
                    System.err.println("photo4");
                    Debug.user("Match TVB: %s", s.find(photo4));
                    s.find(photo4);
                    System.err.println("File Not Found");
                    start1=false;
                    start=false;
                }   */

                if (s.exists( new Pattern("filehoot_filenotfound.png"))!=null)
                {
                     System.err.println("File Not Found");
                    Debug.user("Match TVB: %s", s.find("filehoot_filenotfound.png"));
                    s.find("filehoot_filenotfound.png");
                     System.err.println("File Not Found");
                 start1=false;
                    start=false;
                }
                
              
     
                
        }
     
        while(start1){
            Thread.sleep(2000);
               System.out.println("searching2");
               /*
                 if (s.exists(pimage2)!=null)
                {
                    Debug.user("Match TVB: %s", s.find(pimage2));
                    s.find(pimage2);
                    s.click(pimage2);
                    start2=true;
                    start1=false;
                }
               */
               /*
                       try{
      
        s.find("");
        s.click("filehoot_photo2.png");

                            start2=true;
                    start1=false;

        }catch(FindFailed e)
        {e.printStackTrace();
        }*/

                             System.out.println("**************"); 
               System.out.println((s.exists(new Pattern("filehoot_photo2.png").exact())));
               System.out.println(new Pattern("filehoot_photo2.png").getFileURL());
               System.out.println("**************"); 
                if (s.exists( new Pattern("filehoot_photo2.png"))!=null)
                {

                    System.out.println("find play button");
                    Debug.user("Match TVB: %s", s.find("filehoot_photo2.png"));
                    s.find("filehoot_photo2.png");
                    s.click("filehoot_photo2.png");
                    //start2=true;
                    start1=false;
                }
                       
                       /*
                if (s.exists(photo2)!=null)
                {

                    Debug.user("Match TVB: %s", s.find(photo2));
                    s.find(photo2);
                    s.click(photo2);
                    start2=true;
                    start1=false;
                }
                       */
                       
        }
    
        //Robot r = new Robot();
        //r.mouseMove(0, 0);
       /*
        int checking=1000;
        
        while(count<5){
            Thread.sleep(checking);
            System.out.println(count);
            count++;
            try {
                Debug.user("Match TVB: %s"+count, s.find(photo3));
                if (s.exists(photo3)!=null)
                {
                    System.out.println("Found TVB logo: " +count+" times");  
                    checking=1000;
                }

            } catch (FindFailed ex) {
                Debug.error(ex.getMessage());
            }
        }
        System.out.println("It is TVB drama");

        */
        
        
        
        
    }
}
