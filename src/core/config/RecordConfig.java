package core.config;

import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


// this class provides the fields for the record configuration
    public class RecordConfig {

    
    private long fileSize;
    private String counter;
    // the video length
    private long videoLength;
        // the video length
    private long videoLength1;
    // the capture frame rate
    private int framesRate;
    // the capture area dimension
    private Rectangle frameDimension;
    private Rectangle frameDimension_filehoot;
    private Rectangle frameDimension_h265;
        private Rectangle frameDimension_xtubeth;
    private Rectangle frameDimension_nowvideo;
    private Rectangle frameDimension_openload;
    private Rectangle frameDimension_videobug;
    private Rectangle frameDimension_videoting;
    private Rectangle frameDimension_vodlocker;
    private Rectangle frameDimension_stream;
    
    private int Duration;
    private int Time;
    // the save file 
    private File videoFile;
        // the save file 
    private File videoFile1;
    // the load file 
    private File loadFile;
    private String website;

    // default constructor
    public RecordConfig() throws IOException {
        // set the default field values
        videoLength = 0;
        fileSize = 0;
        counter = "00:00:00";
           // set the default field values
        videoLength1 = 0;
        framesRate = 24;
        frameDimension = new Rectangle (10,248,656,378);
        frameDimension_filehoot=null;
        frameDimension_h265=null;
                frameDimension_xtubeth=null;
        frameDimension_nowvideo=null;
        frameDimension_openload=null;
        frameDimension_videobug=null;
        frameDimension_videoting=null;
        frameDimension_vodlocker=null;
        frameDimension_stream=null;
        website="https://www.mytvsuper.com/en/epg/C";
        Duration = 10000;
        Time=10;
        //videoFile = new File(System.getProperty("user.home")+"\\Desktop\\new\\test1.mp4");
//        videoFile = new File("C:\\Users\\Jonathan\\AppData\\Roaming\\Microsoft\\Windows\\Network Shortcuts\\202.69.73.116\\test1.mp4");
       // videoFile = new File("ftp:\\202.69.73.116\\test1.mp4");

       

    
 videoFile = new File("C:\\Users\\Jonathan\\Desktop\\livestreaming\\test1.mp4");

 


                
        videoFile1=videoFile;
        loadFile=new File(System.getProperty("user.home")+"\\Desktop\\exceltest.xls");
    }
//GETTER AND SETTER FOR THE FILEDS
    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
       this.counter = counter;
      propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
      public long getfileSize() {
        return fileSize;
    }

    public void setfileSize(long fileSize) {
       this.fileSize = fileSize;
      propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
    public long getVideoLength() {
        return videoLength;
    }
   public void setVideoLength(long videoLength) {
       this.videoLength = videoLength;
       propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
    public long getVideoLength1() {
        return videoLength1;
    }
   public void setVideoLength1(long videoLength) {
        this.videoLength1 = videoLength;
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    public int getFramesRate() {
        return framesRate;
    }
    public void setFramesRate(int framesRate) {
        this.framesRate = framesRate;
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
        public int getDuration() {
        return Duration;
    }
    public void setDuration(int Duration) {
        this.Duration = Duration*1000;
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
        public int getTime() {
        return Time;
    }
    public void setTime(int Time) {
        this.Time = Time;
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
    
    public Rectangle getFrameDimension() {
        return frameDimension;
    }
    public void setFrameDimension(Rectangle frameDimension) {
        this.frameDimension = frameDimension;
                System.out.println("Frame Dimension: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }

        public Rectangle getFrameDimension_filehoot() {
        return frameDimension_filehoot;
    }
    public void setFrameDimension_filehoot(Rectangle frameDimension) {
        this.frameDimension_filehoot = frameDimension;
                System.out.println("Frame Dimension - filehoot: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
        public Rectangle getFrameDimension_xtubeth() {
        return frameDimension_xtubeth;
    }
    public void setFrameDimension_xtubeth(Rectangle frameDimension) {
        this.frameDimension_xtubeth = frameDimension;
                System.out.println("Frame Dimension - xtubeth: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
            public Rectangle getFrameDimension_h265() {
        return frameDimension_h265;
    }
    public void setFrameDimension_h265(Rectangle frameDimension) {
        this.frameDimension_h265 = frameDimension;
                System.out.println("Frame Dimension - h265: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
            public Rectangle getFrameDimension_nowvideo() {
        return frameDimension_nowvideo;
    }
    public void setFrameDimension_nowvideo(Rectangle frameDimension) {
        this.frameDimension_nowvideo = frameDimension;
                System.out.println("Frame Dimension - nowvideo: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
            public Rectangle getFrameDimension_openload() {
        return frameDimension_openload;
    }
    public void setFrameDimension_openload(Rectangle frameDimension) {
        this.frameDimension_openload = frameDimension;
                System.out.println("Frame Dimension - openload: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
            public Rectangle getFrameDimension_videobug() {
        return frameDimension_videobug;
    }
    public void setFrameDimension_videobug(Rectangle frameDimension) {
        this.frameDimension_videobug = frameDimension;
                System.out.println("Frame Dimension - videobug: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
            public Rectangle getFrameDimension_videoting() {
        return frameDimension_videoting;
    }
    public void setFrameDimension_videoting(Rectangle frameDimension) {
        this.frameDimension_videoting = frameDimension;
                System.out.println("Frame Dimension - videoting: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
            public Rectangle getFrameDimension_vodlocker() {
        return frameDimension_vodlocker;
    }
    public void setFrameDimension_vodlocker(Rectangle frameDimension) {
        this.frameDimension_vodlocker = frameDimension;
                System.out.println("Frame Dimension - vodlocker: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
            public Rectangle getFrameDimension_stream() {
        return frameDimension_stream;
    }
    public void setFrameDimension_stream(Rectangle frameDimension) {
        this.frameDimension_stream = frameDimension;
                System.out.println("Frame Dimension - stream: "+
                "X="+frameDimension.getX()+ 
                " Y="+frameDimension.getY()+ 
                " W="+frameDimension.getWidth()+
                " H="+frameDimension.getHeight());
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    

    
    
    public File getVideoFile() {
       return videoFile;
    }
    public void setVideoFile(File videoFile) {

        this.videoFile = videoFile;
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
    public File getVideoFile1() {
       return videoFile1;
    }
    public void setVideoFile1(File videoFile) {
        this.videoFile1 = videoFile;
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }

    public File getLoadFile() {
        return loadFile;
    }
    public void setLoadFile(File loadFile) {
        this.loadFile = loadFile;
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }
    
    // ------- PROPERY CHANGE SUPPORT ---------------------
    private String propertyName = "RECORD_CONFIG_CHANGE";

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public String getWebsite() {
         return website;
        
    }
    
    public void setWebsite(String website) {
        this.website = website;
        propertyChangeSupport.firePropertyChange(propertyName, 0, 1);
    }

}
