/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

/**
 *
 * @author Jonathan
 */
public class nowvideo {

    public static void main(String[] args) throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        //Desktop.getDesktop().browse(new URI("http://www.nowvideo.sx/video/652e496d72dec"));
        //Debug.on(3);
        Screen s=new Screen();
        System.out.println("Start searching");
        int count=1;
        boolean start=true;
        boolean start1=false;

        ImagePath.add("selenium.filehoot/resources");
        //System.out.println(ImagePath.getBundleFolder());
        ImagePath.setBundlePath("");
        System.out.println(ImagePath.getBundleFolder());
        
  
        Thread.sleep(5000);
        while(start){
            //Thread.sleep(1000);
               System.out.println("searching1");
                System.out.println((s.exists(new Pattern("nowvideo_continue.png").exact())));
               System.out.println(new Pattern("nowvideo_continue.png").getFileURL());
               System.out.println(s.exists(new Pattern("nowvideo_continue.png").similar(0.95f)));
               System.out.println(new Pattern("nowvideo_continue.png").getFileURL());
   

                if (s.exists(new Pattern("nowvideo_continue.png").exact())!=null)
                {
                    System.out.println("nowvideo_continue.png");
                    Debug.user("Match TVB: %s", s.find("nowvideo_continue.png"));
                    s.find("nowvideo_continue.png");
                    s.click("nowvideo_continue.png");
                    start1=true;
                    start=false;
                }
 /*
                if (s.exists( new Pattern("filehoot_filenotfound.png"))!=null)
                {
                     System.err.println("File Not Found");
                    Debug.user("Match TVB: %s", s.find("filehoot_filenotfound.png"));
                    s.find("filehoot_filenotfound.png");
                 start1=false;
                    start=false;
                }
                */
                count++;
                                  if (count>2){
                System.out.println("Checked count: "+count+"(if loop more than 10, it will start recording and go to the next link)");
                if (count>=10)
                {
                    start=false;
                }}

        }
      count=0;
        while(start1){
           
            Thread.sleep(2000);
               System.out.println("searching2");

               System.out.println((s.exists(new Pattern("nowvideo_play.png").exact())));
               System.out.println(new Pattern("nowvideo_play.png").getFileURL());

                if (s.exists( new Pattern("nowvideo_play.png"))!=null)
                {
                    System.out.println("find play button");
                    Debug.user("Match TVB: %s", s.find("nowvideo_play.png"));
                    s.find("nowvideo_play.png");
                    s.click("nowvideo_play.png");
                    //start2=true;
                    start1=false;
                }
                count++;
                                  if (count>2){
                System.out.println("Checked count: "+count+"(if loop more than 10, it will start recording and go to the next link)");
                if (count>=5)
                {
                    start1=false;
                }}
                       
        }
    
        Robot r = new Robot();
        r.mouseMove(0, 0);
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
