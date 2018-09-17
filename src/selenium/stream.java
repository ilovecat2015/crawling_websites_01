/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import core.config.RecordConfig;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
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
public class stream {
    
    public static void click(Rectangle bounds) throws AWTException, InterruptedException, FindFailed {

        System.out.println("wait 10s to click the center of the frame");
        Thread.sleep(10000);

        
        int x=(int) (bounds.getX()+(bounds.getWidth()/2));
        int y=(int) (bounds.getY()+(bounds.getHeight()/2));
        System.out.println("Center of the frame: X= "+x+" Y= "+y);        
        
        System.out.println("Frame Dimension - stream: "+
                "X="+bounds.getX()+ 
                " Y="+bounds.getY()+ 
                " W="+bounds.getWidth()+
                " H="+bounds.getHeight());

        Screen s=new Screen();
        Robot bot = new Robot();
        bot.mouseMove(x, y);    
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK); 
        bot.mouseMove(0, 0);
        System.out.println("wait 10s for loading the movie");
        Thread.sleep(10000);
    }

    public static void main(String[] args) throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

          
        //Desktop.getDesktop().browse(new URI("https://stream.moe/9CO9"));
        //Debug.on(3);
        Screen s=new Screen();
        System.out.println(s);
        
        System.out.println("Start searching");
        int count=1;
        boolean start=true;
        boolean start1=false;

        
        ImagePath.add("selenium.filehoot/resources");
        ImagePath.setBundlePath("");
        System.out.println(ImagePath.getBundleFolder());

        Thread.sleep(5000);
        while(start){
            //Thread.sleep(1000);
               System.out.println("searching1");
               System.out.println((s.exists(new Pattern("stream_play.png").exact())));
               System.out.println(new Pattern("stream_play.png").getFileURL());
               System.out.println(s.exists(new Pattern("stream_play.png").similar(0.8f)));
               System.out.println(new Pattern("stream_play.png").getFileURL()); 

                if (s.exists(new Pattern("stream_play.png").exact())!=null)
                {
                    System.out.println("stream_play.png");
                    Debug.user("Match TVB: %s", s.find("stream_play.png"));
                    s.find("stream_play.png");
                    s.click("stream_play.png");
                    start1=true;
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

    }
    


    //for checking there are 404 page
       public static int main() throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        Screen s=new Screen();
        System.out.println("Start searching");
        int count=1;
        boolean start=true;
        boolean start1=false;

        int check_sorry = 0; 
        
        ImagePath.add("selenium.filehoot/resources");
        ImagePath.setBundlePath("");
        System.out.println(ImagePath.getBundleFolder());
        
        Thread.sleep(5000);
        while(start){
               System.out.println("searching1");
               System.out.println((s.exists(new Pattern("openload_play.png").exact())));
               System.out.println(new Pattern("openload_play.png").getFileURL());
               System.out.println(s.exists(new Pattern("openload_play.png").similar(0.9f)));
               System.out.println(new Pattern("openload_play.png").getFileURL());
               System.out.println((s.exists(new Pattern("openload_play2.png").exact())));
               System.out.println(new Pattern("openload_play2.png").getFileURL());
               System.out.println((s.exists(new Pattern("openload_sorry.png").similar(0.9f))));
               System.out.println(new Pattern("openload_sorry.png").getFileURL());
               System.out.println((s.exists(new Pattern("openload_sorry1.png").similar(0.9f))));
               System.out.println(new Pattern("openload_sorry1.png").getFileURL());
               
                if (s.exists(new Pattern("openload_play.png").exact())!=null)
                {
                    System.out.println("openload_play.png");
                    Debug.user("Match TVB: %s", s.find("openload_play.png"));
                    s.find("openload_play.png");
                    s.click("openload_play.png");
                    start1=true;
                    start=false;
                }
                
                if (s.exists(new Pattern("openload_sorry.png").exact())!=null)
                {
                    System.out.println("openload_sorry.png");
                    Debug.user("Match TVB: %s", s.find("openload_sorry.png"));
                    s.find("openload_sorry.png");                    
                    start1=false;
                    start=false;
                    check_sorry = 1;                    
                }
                
                if (s.exists(new Pattern("openload_sorry1.png").exact())!=null)
                {
                    System.out.println("openload_sorry1.png");
                    Debug.user("Match TVB: %s", s.find("openload_sorry1.png"));
                    s.find("openload_sorry1.png");                    
                    start1=false;
                    start=false;
                    check_sorry = 1;                    
                }

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
            System.out.println((s.exists(new Pattern("openload_play2.png").exact())));
            System.out.println(new Pattern("openload_play2.png").getFileURL());
            
            if (s.exists( new Pattern("openload_play2.png"))!=null)
                {
                    System.out.println("find play button");
                    Debug.user("Match TVB: %s", s.find("openload_play2.png"));
                    s.find("openload_play2.png");
                    s.click("openload_play2.png");
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
        
        if (check_sorry==0){
            System.out.println("Can't find sorry logo");
        } 
        else if (check_sorry==1){
            System.out.println("found sorry logo");
        } else {
            System.err.println("Error");
        }
        return check_sorry;
       }
}
