//update with audio

package core.recorder;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import core.RecordTimer;
import core.config.RecordConfig;
import ftp.FileUpload;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.FileInputStream;


import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class ScreenRecorder2 implements ActionListener {
    private Robot robot;
    private Rectangle capBounds;
    private IMediaWriter writer;
    private String videoName;
    private int delayBetweenFrames=40;
    private Timer timer=new Timer(delayBetweenFrames, this);
    private long start=0;
    private long pause;
    private record_audio audio=new record_audio();//audio
    private long audioLastFrame; //audio
    private long frameCount; //audio
    private double FRAME_RATE ;
    private RecordTimer RecordTimer = null;

    public void startRecording(RecordConfig recConfig) throws AWTException{
        RecordTimer=new RecordTimer();
        this.recConfig = recConfig;
        videoName=recConfig.getVideoFile().getAbsolutePath();
        this.videoName=videoName;
        System.out.println("Video path is: "+videoName);
        capBounds=recConfig.getFrameDimension();
        
         System.out.println("Frame Dimension: "+
                "X="+recConfig.getFrameDimension().getX()+ 
                " Y="+recConfig.getFrameDimension().getY()+ 
                " W="+recConfig.getFrameDimension().getWidth()+
                " H="+recConfig.getFrameDimension().getHeight());
        
        FRAME_RATE=recConfig.getFramesRate();
        
        System.out.println("Start recording");
        robot=new Robot();
        writer= ToolFactory.makeWriter(videoName);
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, capBounds.width, capBounds.height);
        
        writer.addAudioStream(1,0,1,44100);//audio       
        start=System.currentTimeMillis();

        
        frameCount=0; //audio
        audioLastFrame=0; //audio
        Thread t=new Thread(audio); //audio
        t.start(); //audio
        timer.start();

        RecordTimer.startcounter(recConfig);
    }

    
    int j=100;
        public void startRecording1(RecordConfig recConfig) throws AWTException{

            Date date= new Date();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
                                String parent=recConfig.getVideoFile().getParent();
                                String name=recConfig.getVideoFile().getName();
                                         
                                String removeext=FilenameUtils.removeExtension(name);
                                String string= parent+"\\"+removeext+"_"+j+"_"+dateFormat.format(date);
                                //System.out.println("New video file created: "+string);
                                File filename=new File(string+".mp4");
                                recConfig.setVideoFile1(filename);
                          
        RecordTimer=new RecordTimer();
        this.recConfig = recConfig;
        videoName=recConfig.getVideoFile1().getAbsolutePath();
         
        this.videoName=videoName;
        System.out.println("Video path is: "+videoName);
        capBounds=recConfig.getFrameDimension();
        
         System.out.println("Frame Dimension: "+
                "X="+recConfig.getFrameDimension().getX()+ 
                " Y="+recConfig.getFrameDimension().getY()+ 
                " W="+recConfig.getFrameDimension().getWidth()+
                " H="+recConfig.getFrameDimension().getHeight());
        
        FRAME_RATE=recConfig.getFramesRate();
        
        System.out.println("Start recording");
        robot=new Robot();
        writer= ToolFactory.makeWriter(videoName);
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, capBounds.width, capBounds.height);
        writer.addAudioStream(1,0,1,44100);//audio     
        
        start=System.currentTimeMillis();

        frameCount=0; //audio
        audioLastFrame=0; //audio
        Thread t=new Thread(audio); //audio
        t.start(); //audio
        
        timer.start();

        RecordTimer.startcounter(recConfig);
        j++;
    }
               public void startRecording2(RecordConfig recConfig) throws AWTException{

        RecordTimer=new RecordTimer();
        this.recConfig = recConfig;
        videoName=recConfig.getVideoFile1().getAbsolutePath();
         
        this.videoName=videoName;
        System.out.println("Video path is: "+videoName);
        capBounds=recConfig.getFrameDimension();
        
         System.out.println("Frame Dimension: "+
                "X="+recConfig.getFrameDimension().getX()+ 
                " Y="+recConfig.getFrameDimension().getY()+ 
                " W="+recConfig.getFrameDimension().getWidth()+
                " H="+recConfig.getFrameDimension().getHeight());
        
        FRAME_RATE=recConfig.getFramesRate();
        
        System.out.println("Start recording");
        robot=new Robot();
        writer= ToolFactory.makeWriter(videoName);
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, capBounds.width, capBounds.height);
        writer.addAudioStream(1,0,1,44100);//audio     
        start=System.currentTimeMillis();

        frameCount=0; //audio
        audioLastFrame=0; //audio
        Thread t=new Thread(audio); //audio
        t.start(); //audio
        
        timer.start();

        RecordTimer.startcounter(recConfig);
        j++;
    }
                
        
    public void stopRecording(){

        RecordTimer.stopcounter();

        audio.stop(); //audio
        addAudioData(); //audio
        
        
        timer.stop();
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        writer.close();
        start=0;
        System.out.println("stop recording");
    }   

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==timer){
            BufferedImage screen=getCaptureImage();
            writer.encodeVideo(0,screen,System.currentTimeMillis()-start,TimeUnit.MILLISECONDS);
            addAudioData(); //audio
            frameCount++; //audio
            
        }
    }
    
    //audio
    private void addAudioData(){
        byte[] audioData=audio.getData();
        if(audioData.length>0){
            writer.encodeAudio(1,audioByteToShort(audioData),
                    audioLastFrame*delayBetweenFrames,
                    TimeUnit.MILLISECONDS
            );
            audioLastFrame=frameCount;
        }
    }
    //audio
    private short[] audioByteToShort(byte[] b){
        short[] saudio = new short[b.length/2];
        for(int i=0; i<saudio.length;i++)
            saudio[i]=(short)(((b[2*i]<<8)&0xFF00)|(b[2*i+1]&0xFF) & 0xFFFF);
        return saudio;

    }
    
    

    private BufferedImage getCaptureImage(){
        BufferedImage screen=robot.createScreenCapture(capBounds);
        if(screen.getType()!=BufferedImage.TYPE_3BYTE_BGR){
            BufferedImage image=new BufferedImage(screen.getWidth(),screen.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g=image.createGraphics();
            g.drawImage(screen,0,0,null);
            screen=image;
            g.dispose();
        }
        return screen;
    }
    
    private static RecordConfig recConfig;
    private static Timer t;
    private static int count=0;
    
        public void autoRecording(RecordConfig recConfig) throws AWTException, InterruptedException, IOException  {

        RecordTimer=new RecordTimer();
        this.recConfig=recConfig;
        
        //Date date= new Date();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
        //String str=recConfig.getVideoFile().getAbsolutePath();
        //String parent=recConfig.getVideoFile().getParent();
        //String name=recConfig.getVideoFile().getName();
        //String ext=FilenameUtils.getExtension(str);
        //String removeext=FilenameUtils.removeExtension(name);
        //File newfilename=new File(parent+"\\"+removeext+"_"+dateFormat.format(date)+"."+ext);
        //recConfig.setVideoFile1(newfilename);
         
        //System.out.println("video file name is "+recConfig.getVideoFile().getName());
        System.out.println("video file name1 is "+recConfig.getVideoFile1().getName());
        
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String str=recConfig.getVideoFile1().getAbsolutePath();
        String parent=recConfig.getVideoFile1().getParent();
        String name=recConfig.getVideoFile1().getName();
        String ext=FilenameUtils.getExtension(str);
        String removeext=FilenameUtils.removeExtension(name);
        File newfilename=new File(parent+"\\"+dateFormat.format(date)+"_"+removeext+"."+ext);
       // System.out.println(newfilename);
        recConfig.setVideoFile1(newfilename);
        
               
        
        
       // recConfig.setVideoFile1(new File(filename + "\\無線新聞台-"+dateFormat.format(date)+".mp4"));
        //recConfig.setVideoFile1(new File(System.getProperty("user.home") + "\\Desktop\\livestreaming\\無線新聞台-"+dateFormat.format(date)+".mp4"));
         
        //recConfig.setVideoFile(new File(System.getProperty("user.home") + "\\Desktop\\livestreaming\\test-"+dateFormat.format(date)+".mp4"));
        
        videoName=recConfig.getVideoFile1().getAbsolutePath();
        this.videoName=videoName;
        capBounds=recConfig.getFrameDimension();
        FRAME_RATE=recConfig.getFramesRate();
        //System.out.println("Start recording");
        robot=new Robot();
        writer= ToolFactory.makeWriter(videoName);
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, capBounds.width, capBounds.height);
        //start=System.currentTimeMillis();
        //timer.start();

        int SECS_TO_RUN_FOR=recConfig.getDuration()/1000;
        //System.out.println(recConfig.getDuration()/1000);
        long startTime = System.nanoTime();
   
        for (int i = 0;  i < SECS_TO_RUN_FOR * FRAME_RATE; i++){
          //  System.out.println(i);
         
            //System.out.println("Start: " + System.currentTimeMillis());
            long endTime = startTime + (long)(1000/FRAME_RATE);
            //take screenshot
            BufferedImage screen = getScreenshot();
            //convert image to right type
            BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);
            //encode image to stream
            writer.encodeVideo(0, bgrScreen, System.nanoTime() - endTime, TimeUnit.NANOSECONDS);
            //System.out.println("End: " + System.currentTimeMillis());
            startTime = endTime;
            try{
                Thread.sleep((long)((1000/FRAME_RATE)));
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
  
       // timer.stop();
        //start=0;
        writer.close();
        //RecordTimer.stopcounter();
        //System.out.println("Finished recording");

              //fis = new FileInputStream(firstLocalFile);
              File firstLocalFile = newfilename;
              String firstRemoteFile = dateFormat.format(date)+"_"+removeext+"."+ext;
              InputStream inputStream = new FileInputStream(firstLocalFile);
      
              FileUpload.upload(firstRemoteFile,parent+"\\"+firstRemoteFile);

              
        /*
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("202.69.73.116");
        ftpClient.login("user", "0000");
        try {
            ftpClient.enterLocalPassiveMode();
            boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory("\\Live");



            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = newfilename;
 
            System.out.println("firstlocalfile "+firstLocalFile);
            
            String firstRemoteFile = "\\"+dateFormat.format(date)+"_"+removeext+"."+ext;
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading file");
            //System.out.println("firstremotefile "+firstRemoteFile);
           // System.out.println("inputstream "+inputStream);
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
          
            System.out.println(done);
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }
            inputStream.close();

 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        */ 
        
         }    
 
        private  BufferedImage getScreenshot(){
        return robot.createScreenCapture(capBounds);
    }   
            public BufferedImage convertToType(BufferedImage sourceImg, int targetType){
        BufferedImage img;
        if (sourceImg.getType() == targetType){
            img = sourceImg;
        }else{
            img = new BufferedImage(sourceImg.getWidth(), sourceImg.getHeight(), targetType);
            img.getGraphics().drawImage(sourceImg, 0, 0, null);
        }
        return img;
    }
}


