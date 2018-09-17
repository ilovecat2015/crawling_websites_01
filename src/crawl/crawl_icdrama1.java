package crawl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class crawl_icdrama1 {

    public static void main(String[] args) throws IOException, InterruptedException {

        long start = System.currentTimeMillis();

        Document htmlDocument = Jsoup.connect("http://icdrama.se/hk-drama/3752-daddy-cool/download/").timeout(5000).get();

        Elements links1 = htmlDocument.getElementsByClass("listep").select("a[href]");
        int i = 0;

        do {
            System.out.println(i + " " + links1.eq(i).attr("href"));
            i++;
            Thread.sleep(1000);
        } while (links1.eq(i).attr("href").startsWith("http"));

        System.out.println("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jonathan\\Downloads\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();

        i = 0;
        System.out.println("start crawling");
        do {
            try {

                Thread.sleep(1000);
                // System.out.println("start crawling" + " " + i);
                String link1 = links1.eq(i).attr("href");
                //  System.out.println(link1);
                driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
                driver.get(link1);
                Thread.sleep(1000);
                String name1 = driver.getCurrentUrl();
                // System.out.println("website: " + name1);
                Thread.sleep(1000);
                for (WebElement links : driver.findElements(By.tagName("a"))) {
                    Thread.sleep(1000);
                    if (links.getAttribute("href").startsWith("https://3.bp.blogspot.com")) {
                        System.out.println(links.getAttribute("href").replace("3.bp.blogspot.com", "lh3.googleusercontent.com"));
                    }
                }
                i++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // System.out.println("Time is:" + (System.currentTimeMillis() - start) + "ms");
                Thread.sleep(3000);
                //  System.out.println("");
            }
        } while (links1.eq(i).attr("href").startsWith("http"));
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();

    }

}
