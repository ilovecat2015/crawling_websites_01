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
public class xtubeth {

    public static void main(String[] args) throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        //Desktop.getDesktop().browse(new URI("http://xtubeth.com/file/90278.php"));
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
                System.out.println((s.exists(new Pattern("xtubeth_play1.png").exact())));
               System.out.println(new Pattern("xtubeth_play1.png").getFileURL());
               System.out.println(s.exists(new Pattern("xtubeth_play1.png").similar(0.95f)));
               System.out.println(new Pattern("xtubeth_play1.png").getFileURL());
               System.out.println((s.exists(new Pattern("xtubeth_play2").exact())));
               System.out.println(new Pattern("xtubeth_play2").getFileURL());       

                if (s.exists(new Pattern("xtubeth_play1.png").exact())!=null)
                {
                    System.out.println("xtubeth_play1.png");
                    Debug.user("Match TVB: %s", s.find("xtubeth_play1.png"));
                    s.find("xtubeth_play1.png");
                    s.click("xtubeth_play1.png");
             
                    start=false;
                }
 
                if (s.exists( new Pattern("xtubeth_play2.png"))!=null)
                {
                     System.err.println("xtubeth_play2.png");
                    Debug.user("Match TVB: %s", s.find("xtubeth_play2.png"));
                    s.find("xtubeth_play2.png");
                      s.click("xtubeth_play2.png");
 
                    start=false;
                }
                
                count++;
                                  if (count>2){
                System.out.println("Checked count: "+count+"(if loop more than 10, it will start recording and go to the next link)");
                if (count>=10)
                {
                    start=false;
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
