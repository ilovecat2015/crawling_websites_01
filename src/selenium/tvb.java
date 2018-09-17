/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

/**
 *
 * @author Jonathan
 */
public class tvb {

    public static void main(String[] args) throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        //Desktop.getDesktop().browse(new URI("http://filehoot.com/34b9tk68z8iw"));
        //Debug.on(3);
        Screen s=new Screen();
        Region box = new Region(376, 182, 52, 44);
        s.setX(376);
        s.setY(182);
        s.setW(52);
        s.setH(44);
      //s.setROI(box);
        
        ScreenImage file = s.capture(376, 182, 52, 44);
        
    //ImageIO.write(file.getImage(), "PNG", new File("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png"));

    
   // s.exists(new Pattern("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png").similar(0.1f));
    //System.err.println("Founding TVB logo OK: ");
                    
    //System.out.println(new Pattern("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png").similar(0.5f));
                     
         //s.mouseMove("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png");
          //Debug.user("Match TVB: %s", s.find("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png"));
        
          
        //System.out.println("Start searching");
        boolean count=true;
        //int count_tvb=1;
       // int count_notvb=1;
        

        while(count){
            //Debug.user("Match TVB: %s", s.find("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png"));
            System.out.println("test1"+s.exists(new Pattern("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png").similar(0.2f)));
            s.mouseMove("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png");
            Thread.sleep(200);
         //   box.find("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png");
              
        //System.out.println("test2"+box.exists(new Pattern("C:\\Users\\Jonathan\\Desktop\\CapturedRegion.png")));
          //  Thread.sleep(200);
        
        }
        
        
        
        
        
        
        
        
        
    }
    
       public static int main() throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        //Desktop.getDesktop().browse(new URI("http://filehoot.com/34b9tk68z8iw"));
        //Debug.on(3);
        Screen s=new Screen();
        //System.out.println("Start searching");
        boolean count=true;
        int count_tvb=1;
        int count_notvb=1;

        ImagePath.add("selenium.filehoot/resources");
        //System.out.println(ImagePath.getBundleFolder());
        ImagePath.setBundlePath("");
        //System.out.println(ImagePath.getBundleFolder());
        System.out.println("searching TVB logo");
        while(count){
            
            try{
                if (s.exists(new Pattern("tvb2.png").similar(0.65f))!=null)
                {
                     
                     System.err.println("Found TVB logo1: "+count_tvb);
                     System.out.println(s.exists(new Pattern("tvb2.png").similar(0.65f)));
                    //Debug.user("Match TVB: %s", s.find("tvb2.png"));
                    //s.find("tvb2.png");
                    count_tvb++;
                }
 
                
                else if (s.exists( new Pattern("tvb3.png").similar(0.65f))!=null)
                {
                    
                     System.err.println("Found TVB logo2: "+count_tvb);
                     System.out.println(s.exists(new Pattern("tvb3.png").similar(0.65f)));
                    //Debug.user("Match TVB: %s", s.find("tvb3.png"));
                    s.find("tvb3.png");
                    count_tvb++;
                }
                else if (s.exists( new Pattern("tvb4.png").similar(0.65f))!=null)
                {
                    
                     System.err.println("Found TVB logo3: "+count_tvb);
                     System.out.println(s.exists(new Pattern("tvb4.png").similar(0.65f)));
                   // Debug.user("Match TVB: %s", s.find("tvb4.png"));
                    s.find("tvb4.png");
                    count_tvb++;
                }
                else if (s.exists( new Pattern("tvb5.png").similar(0.65f))!=null)
                {
                    
                     System.err.println("Found TVB logo4: "+count_tvb);
                     System.out.println(s.exists(new Pattern("tvb5.png").similar(0.65f)));
                    //Debug.user("Match TVB: %s", s.find("tvb5.png"));
                    s.find("tvb5.png");
                    count_tvb++;
                }
                else if (s.exists( new Pattern("tvb6.png").similar(0.65f))!=null)
                {
               
                     System.err.println("Found TVB logo5: "+count_tvb);
                     System.out.println(s.exists(new Pattern("tvb6.png").similar(0.65f)));
                    //Debug.user("Match TVB: %s", s.find("tvb6.png"));
                    s.find("tvb6.png");
                    count_tvb++;
                }
                else if (s.exists( new Pattern("tvb7.png").similar(0.65f))!=null)
                {
                    
                     System.err.println("Found TVB logo6: "+count_tvb);
                     System.out.println(s.exists(new Pattern("tvb7.png").similar(0.65f)));
                    //Debug.user("Match TVB: %s", s.find("tvb7.png"));
                    s.find("tvb7.png");
                    count_tvb++;
                }
                
                else
                {
                System.err.println("Can't find TVB logo: "+count_notvb);
                count_notvb++;
                }

                if (count_notvb==3){
                count=false;
                } else if (count_tvb==3)
                {count=false;
                } else
                {
                    count=true;
                }
                

                       }catch(FindFailed e)
        {e.printStackTrace();
        }
        }
        int check_block = 0;
                if (count_notvb==3){
        System.out.println("Can't find TVB logo");
        check_block=1;
        } else if (count_tvb==3){
        System.out.println("found TVB logo");
        check_block=2;
    } else {
            System.err.println("Error");
        }
        
        
    return check_block;
    } 
    
    
}
