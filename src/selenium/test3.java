/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class test3 {
     public static void main(String[] args) throws AWTException, IOException, URISyntaxException {
          
       
         System.setProperty("webdriver.chrome.driver","src\\resources\\chromedriver.exe");
          
          
          
          
          ChromeOptions options = new ChromeOptions();
          options.addArguments("window-size=1000,800");
          options.addExtensions(new File("src\\resources\\AdBlocker-Ultimate_v2.23.crx"));
          DesiredCapabilities cap = DesiredCapabilities.chrome();
          cap.setCapability(ChromeOptions.CAPABILITY, options);
          Point point=new Point(0, 0);
          
          //this will open chrome with set size
          WebDriver driver = new ChromeDriver(cap);
          driver.manage().window().setPosition(point);
  	
          //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");
          //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
          ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
          driver.switchTo().window(tabs.get(1)); //switches to new tab
          driver.get("https://hk.yahoo.com");
           
         
          //Robot robot = new Robot();
          //robot.keyPress(KeyEvent.VK_CONTROL);
          //robot.keyPress(KeyEvent.VK_T);
          //robot.keyRelease(KeyEvent.VK_CONTROL);
          //robot.keyRelease(KeyEvent.VK_T);
          driver.get("http://filehoot.com/34b9tk68z8iw");
          driver.get("http://filehoot.com/740mmfw459a5");
     }
}
/*
import java.util.ArrayList;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author Jonathan
 */
/*
public class test3 {
    
          public static void main(String[] args) {
              
          
    String pathToChrome = "C:\\chromedriver\\chromedriver.exe";
System.setProperty("webdriver.chrome.driver", pathToChrome);

ChromeOptions options = new ChromeOptions();
options.addArguments("user-data-dir=C:\\Users\\Jonathan\\AppData\\Local\\Google\\Chrome\\User Data");
options.addArguments("window-size=600,500");
//options.addArguments("--start-maximized");

//Dimension dimension = new Dimension(600, 400);
//WebDriver driver = new ChromeDriver(options);
//driver.manage().window().setSize(dimension);
Point point=new Point(0, 0);
Dimension dimension=new Dimension(500, 600);


       DesiredCapabilities cap = DesiredCapabilities.chrome();
          cap.setCapability(ChromeOptions.CAPABILITY, options);
          
          //this will open chrome with set size
          WebDriver driver = new ChromeDriver(cap);
          driver.manage().window().getSize();
          driver.manage().window().setPosition(point);
          driver.manage().window().setSize(dimension);
driver.get("http://hk.yahoo.com");

/*
DesiredCapabilities capabilities = DesiredCapabilities.chrome();
String chromeProfile = "C:\\Users\\Jonathan\\AppData\\Local\\Google\\Chrome\\User Data\\Default";
ArrayList<String> switches = new ArrayList<String>();
switches.add("--user-data-dir=" + chromeProfile);
capabilities.setCapability("chrome.switches", switches);
WebDriver driver = new ChromeDriver(capabilities);
*/

