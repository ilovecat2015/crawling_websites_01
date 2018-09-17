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
public class yourupload {

    public static void main(String[] args) throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        //Desktop.getDesktop().browse(new URI("http://www.yourupload.com/watch/2w8P3Uq4wp32"));
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
                System.out.println((s.exists(new Pattern("yourupload_play.png").exact())));
               System.out.println(new Pattern("yourupload_play.png").getFileURL());
               System.out.println(s.exists(new Pattern("yourupload_play.png").similar(0.95f)));
               System.out.println(new Pattern("yourupload_play.png").getFileURL());
               System.out.println((s.exists(new Pattern("yourupload_error.png").exact())));
               System.out.println(new Pattern("yourupload_error.png").getFileURL());   

                if (s.exists(new Pattern("yourupload_play.png").exact())!=null)
                {
                    System.out.println("yourupload_play.png");
                    Debug.user("Match TVB: %s", s.find("yourupload_play.png"));
                    s.find("yourupload_play.png");
                    s.click("yourupload_play.png");
                    start1=true;
                    start=false;
                }
 
                if (s.exists( new Pattern("yourupload_error.png"))!=null)
                {
                     System.err.println("ERROR");
                    Debug.user("Match TVB: %s", s.find("yourupload_error.png"));
                    s.find("yourupload_error.png");
                 start1=false;
                    start=false;
                }
                
                count++;
                                  if (count>2){
                System.out.println("Checked count: "+count+"(if loop more than 10, it will start recording and go to the next link)");
                if (count>=5)
                {
                    start=false;
                }}

        }
     /*
        while(start1){
            count=0;
            Thread.sleep(2000);
               System.out.println("searching2");

               System.out.println((s.exists(new Pattern("filehoot_photo2.png").exact())));
               System.out.println(new Pattern("filehoot_photo2.png").getFileURL());

                if (s.exists( new Pattern("filehoot_photo2.png"))!=null)
                {
                    System.out.println("find play button");
                    Debug.user("Match TVB: %s", s.find("filehoot_photo2.png"));
                    s.find("filehoot_photo2.png");
                    s.click("filehoot_photo2.png");
                    //start2=true;
                    start1=false;
                }
                count++;
                                  if (count>2){
                System.out.println("Checked count: "+count+"(if loop more than 10, it will start recording and go to the next link)");
                if (count>=10)
                {
                    start1=false;
                }}
                       
        }*/
    
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
