package selenium;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class test4 {

    public static void main(String[] args) throws InterruptedException, FindFailed, URISyntaxException, IOException, AWTException {
        // Opening chrome with that addon
        
    /*    
        //ChromeOptions options = new ChromeOptions();
        //options.addExtensions(new File("src\\resources\\AdBlocker-Ultimate_v2.23.crx"));     
        //options.addArguments("window-size=1000,800");
        
          WebDriver driver=new FirefoxDriver();
        //WebDriver driver = new ChromeDriver();
        Point point=new Point(0, 0);
        driver.manage().window().setPosition(point);
        
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://www.flipkart.com");

      //     WebElement ele=driver.findElement(By.cssSelector(".menu-text.fk-inline-block"));
   
   //        Actions act=new Actions(driver);
    //       Thread.sleep(5000);
   //        act.moveToElement(ele).perform();
   */
    //System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
    WebDriver driver=new FirefoxDriver();
    Point point=new Point(0, 0);
        driver.manage().window().setPosition(point);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
        driver.get("http://embeda.videoting.se/video/TVRReU9EQT0=.html");
        Thread.sleep(6000);
    driver.findElement(By.xpath("//*[@id=\"next\"]")).click();
    

   WebElement ele=driver.findElement(By.xpath("//html//body//div[3]//a[1]"));
   System.out.println(ele);    
   //
   System.out.println("Page title is: " + driver.getTitle());
       
   String ele3=driver.findElement(By.xpath("//html//body//div[3]//a[1]")).getAttribute("href");
   System.out.println(ele3);
   String ele4=driver.findElement(By.xpath("//html//body//div[3]//a[2]")).getAttribute("href");
   System.out.println(ele4);
   String ele5=driver.findElement(By.xpath("//*[@id=\"media-player\"]//div[2]//video")).getAttribute("src");
   System.out.println(ele5);
   
   
   
   //String ele5=driver.findElement(By.xpath("//span[@class=bs btnt-primary]")).getAttribute("href");
   //System.out.println(ele5);
   //String ele6=driver.findElement (By.className("server")).getText();
   //System.out.println(ele6);
   


    driver.quit();

    //Actions act=new Actions(driver);
    
    //act.moveToElement(ele).perform();
    
    }

        

    }