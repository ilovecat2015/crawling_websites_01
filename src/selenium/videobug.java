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
public class videobug {

    public static void main(String[] args) throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        //Desktop.getDesktop().browse(new URI("http://videobug.se/vid/7-JA4rBByp2jYKefsnH6J7Df5qGSgqdmxHqv1rZzpjk"));
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
                System.out.println((s.exists(new Pattern("videobug_closenplay.png").exact())));
               System.out.println(new Pattern("videobug_closenplay.png").getFileURL());
               System.out.println(s.exists(new Pattern("videobug_closenplay.png").similar(0.95f)));
               System.out.println(new Pattern("videobug_closenplay.png").getFileURL());  
               
                               System.out.println((s.exists(new Pattern("videobug_closenplay1.png").exact())));
               System.out.println(new Pattern("videobug_closenplay1.png").getFileURL());
               System.out.println(s.exists(new Pattern("videobug_closenplay1.png").similar(0.95f)));
               System.out.println(new Pattern("videobug_closenplay1.png").getFileURL());  
               

                if (s.exists(new Pattern("videobug_closenplay.png").exact())!=null)
                {
                    System.out.println("videobug_closenplay.png");
                    Debug.user("Match TVB: %s", s.find("videobug_closenplay.png"));
                    s.find("videobug_closenplay.png");
                    s.click("videobug_closenplay.png");
             
                    start=false;
                }
                                if (s.exists(new Pattern("videobug_closenplay1.png").exact())!=null)
                {
                    System.out.println("videobug_closenplay1.png");
                    Debug.user("Match TVB: %s", s.find("videobug_closenplay1.png"));
                    s.find("videobug_closenplay1.png");
                    s.click("videobug_closenplay1.png");
             
                    start=false;
                }
 
                count++;
                if(count>2){
                System.out.println("Checked count: "+count+"(if loop more than 10, it will start recording and go to the next link)");
                if (count>=5)
                {
                    start=false;
                }
                }

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
