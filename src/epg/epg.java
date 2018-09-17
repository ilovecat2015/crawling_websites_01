/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jonathan
 */
public class epg {
    
        public static void  main(String[] args) {    
        
            System.out.println("test");
  new epg().getPageLinks("https://www.mytvsuper.com/en/epg/J#today");
  //new epg().getPageLinks("https://www.mytvsuper.com/en/epg/C");
  //org.jsoup.nodes.Document document = Jsoup.connect("https://www.mytvsuper.com/en/epg/J#today").get();
 
    }  

          private HashSet<String> links;

    //private int Number;
    private String A;

    public epg() {
        System.out.println("test1");
        links = new HashSet<String>();

    }

    public void getPageLinks(String URL) {
        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
        if (!links.contains(URL)) {
            try {
                //4. (i) If not add it to the index
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs
   


Elements links1 = document.select("div[data-tag=today]").select("td");
//Elements links1 = doc1.getElementsByClass("defLink"); //Get words from site
String s[] = new String[links1.size()]; //Create an array

System.out.println("Array size "+links1.size());

for (int k = 0; k < links1.size(); k++) {
s[k] = links1.get(k).text();
}

         for (int k = 0; k < links1.size(); k+=2) {            
               System.out.println("Array "+k+" "+s[k]);
               System.out.println("Array "+k+" "+s[k+1].replaceAll("\\s+", ""));
               System.out.println("Array "+k+" "+s[k+1].replaceAll("[^a-zA-Z0-9\\.\\-]", ""));
               System.out.println("Array "+k+" "+s[k+1].replaceAll("[^a-zA-Z0-9.-]", ""));
               
                
                System.out.println(Integer.parseInt(s[k].substring(0,2))-6);
                System.out.println(Integer.parseInt(s[k].substring(3,5))+10);
                   
               
               //System.out.println(Integer.parseInt(s[k].substring(0,2))); 
               //System.out.println(Integer.parseInt(s[k].substring(3,5)));
               //System.out.println("name "+" "+s[k+1]);
            }
    //for (int k = 0; k < links1.size(); k+=2) {
    
    //k=k+1;
    

    //System.out.println(Integer.parseInt(s[k].substring(0,2)));
    //System.out.println(Integer.parseInt(s[k].substring(3,5)));
    //System.out.println("name "+" "+s[k+1]);

//System.out.println("adfsda"+s[0]);
//System.out.println(s[2]);
//System.out.println(s.length);


//System.out.println(getNumber());
 //              A=s[0];
//System.out.println(getNumber());



 
    }       catch (IOException ex) {
                Logger.getLogger(epg.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
   /* 
        public String getNumber(){
       // int A = 10;
        return A;
    }

    public void setNumber(String A) {
        this.A=A;
      
    }
*/
    

          

}
