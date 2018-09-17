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
public class google {

    public static void main(String[] args) throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        Robot bot = new Robot();
        Desktop.getDesktop().browse(new URI("https://www.google.com/webmasters/tools/legal-removal-request?hl=zh-TW&pid=2&complaint_type=1"));
        //  bot.mouseMove(646, 405);
        // bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        // bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        // SCROLL THE MOUSE WHEEL
       bot.mouseMove(646, 405);

        System.out.println("move");
        //-- scroll down
   
        for (int index = 0; index < 6; index++) {
            
            bot.mouseWheel(5);
            bot.delay(1000);
            System.out.println(index);
        }
        
        Screen s = new Screen();
        System.out.println("Start searching");
        int count = 1;
        boolean start = true;
        boolean start1 = false;

        ImagePath.add("selenium.filehoot/resources");
        //System.out.println(ImagePath.getBundleFolder());
        ImagePath.setBundlePath("");
        System.out.println(ImagePath.getBundleFolder());

        Thread.sleep(5000);
        while (start) {
            //Thread.sleep(1000);
            System.out.println("searching1");
            System.out.println((s.exists(new Pattern("google.png").exact())));
            System.out.println(new Pattern("google.png").getFileURL());
            System.out.println(s.exists(new Pattern("google.png").similar(0.9f)));
            System.out.println(new Pattern("google.png").getFileURL());

            System.out.println((s.exists(new Pattern("google.png").exact())));
            System.out.println(new Pattern("google.png").getFileURL());
            System.out.println(s.exists(new Pattern("google.png").similar(0.9f)));
            System.out.println(new Pattern("google.png").getFileURL());

            if (s.exists(new Pattern("google.png").exact()) != null) {
                System.out.println("google.png");
                Debug.user("Match TVB: %s", s.find("google.png"));
                s.find("google.png");
                s.click("google.png");
                start1 = true;
                start = false;
            }

            count++;
            if (count > 2) {
                System.out.println("Checked count: " + count + "(if loop more than 10, it will start recording and go to the next link)");
                if (count >= 10) {
                    start = false;
                }
            }

        }
        count = 0;

        Robot r = new Robot();
        r.mouseMove(0, 0);

    }

    //for checking there are 404 page
    public static int main() throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {

        Screen s = new Screen();
        System.out.println("Start searching");
        int count = 1;
        boolean start = true;
        boolean start1 = false;

        int check_sorry = 0;

        ImagePath.add("selenium.filehoot/resources");
        ImagePath.setBundlePath("");
        System.out.println(ImagePath.getBundleFolder());

        Thread.sleep(5000);
        while (start) {
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

            if (s.exists(new Pattern("openload_play.png").exact()) != null) {
                System.out.println("openload_play.png");
                Debug.user("Match TVB: %s", s.find("openload_play.png"));
                s.find("openload_play.png");
                s.click("openload_play.png");
                start1 = true;
                start = false;
            }

            if (s.exists(new Pattern("openload_sorry.png").exact()) != null) {
                System.out.println("openload_sorry.png");
                Debug.user("Match TVB: %s", s.find("openload_sorry.png"));
                s.find("openload_sorry.png");
                start1 = false;
                start = false;
                check_sorry = 1;
            }

            if (s.exists(new Pattern("openload_sorry1.png").exact()) != null) {
                System.out.println("openload_sorry1.png");
                Debug.user("Match TVB: %s", s.find("openload_sorry1.png"));
                s.find("openload_sorry1.png");
                start1 = false;
                start = false;
                check_sorry = 1;
            }

            count++;
            if (count > 2) {
                System.out.println("Checked count: " + count + "(if loop more than 10, it will start recording and go to the next link)");
                if (count >= 10) {
                    start = false;
                }
            }

        }
        count = 0;
        while (start1) {
            Thread.sleep(2000);
            System.out.println("searching2");
            System.out.println((s.exists(new Pattern("openload_play2.png").exact())));
            System.out.println(new Pattern("openload_play2.png").getFileURL());

            if (s.exists(new Pattern("openload_play2.png")) != null) {
                System.out.println("find play button");
                Debug.user("Match TVB: %s", s.find("openload_play2.png"));
                s.find("openload_play2.png");
                s.click("openload_play2.png");
                start1 = false;
            }
            count++;
            if (count > 2) {
                System.out.println("Checked count: " + count + "(if loop more than 10, it will start recording and go to the next link)");
                if (count >= 5) {
                    start1 = false;
                }
            }
        }
        Robot r = new Robot();
        r.mouseMove(0, 0);

        if (check_sorry == 0) {
            System.out.println("Can't find sorry logo");
        } else if (check_sorry == 1) {
            System.out.println("found sorry logo");
        } else {
            System.err.println("Error");
        }
        return check_sorry;
    }
}
