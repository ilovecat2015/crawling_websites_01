/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Jonathan
 */
public class MouseClick {
    
    public static void click(int x, int y) throws AWTException{
    Robot r = new Robot();
    r.mouseMove(x, y);    
    r.delay(100);
    r.mousePress(InputEvent.BUTTON1_MASK);
    r.mouseRelease(InputEvent.BUTTON1_MASK);
    r.mouseMove(0, 0);
}
    
        public static void main(String argv[]) throws AWTException, IOException, URISyntaxException {
            
            
            Desktop.getDesktop().browse(new URI("https://www.google.com/webmasters/tools/legal-removal-request?hl=zh-TW&pid=2&complaint_type=1"));
            click(800,800);
        }
    
}
