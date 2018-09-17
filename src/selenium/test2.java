/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 *
 * @author Jonathan
 */
public class test2 {
            public static void main(String[] args) {
                
                WebDriver driver=new FirefoxDriver();
                driver.get("http://hdfcbank.com");
                driver.manage().window();
                String homePage=driver.getWindowHandle();
                System.out.println(homePage);
                
                driver.findElement(By.xpath("//*[@id='loginsubmit']")).click();
                String popPage=driver.getWindowHandle();
                System.out.println(popPage);
                
                Set<String> windows=driver.getWindowHandles();
                System.out.println(windows.size());
                
                Iterator iterator = windows.iterator();
                String currentWindowId;
                
                while(iterator.hasNext()){
            currentWindowId=iterator.next().toString();
            System.out.println(currentWindowId);
            
            if(!currentWindowId.equals(homePage)){
                driver.switchTo().window(currentWindowId);
                driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[6]/a/img")).click();
     
                
            }
            
}
            }
}

            
