
package core.recorder;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import core.RecordTimer;
import core.config.RecordConfig;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FilenameUtils;

public class ScreenRecorder_videoting implements ActionListener {
    private Robot robot;
    private Rectangle capBounds;
    private IMediaWriter writer;
    private String videoName;
    private int delayBetweenFrames=40;
    private Timer timer=new Timer(delayBetweenFrames, this);
    private long start=0;
    private long pause;
    private double FRAME_RATE ;
    private RecordTimer RecordTimer = null;

    public void startRecording(RecordConfig recConfig) throws AWTException{
        RecordTimer=new RecordTimer();
        this.recConfig = recConfig;
        videoName=recConfig.getVideoFile().getAbsolutePath();
        this.videoName=videoName;
        System.out.println("Video path is: "+videoName);
        capBounds=recConfig.getFrameDimension_videoting();
        
         System.out.println("Frame Dimension: "+
                "X="+recConfig.getFrameDimension_videoting().getX()+ 
                " Y="+recConfig.getFrameDimension_videoting().getY()+ 
                " W="+recConfig.getFrameDimension_videoting().getWidth()+
                " H="+recConfig.getFrameDimension_videoting().getHeight());
        
        FRAME_RATE=recConfig.getFramesRate();
        
        System.out.println("Start recording");
        robot=new Robot();
        writer= ToolFactory.makeWriter(videoName);
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, capBounds.width, capBounds.height);
        start=System.currentTimeMillis();

        timer.start();

        RecordTimer.startcounter(recConfig);
    }

    
    int j=100;
        public void startRecording1(RecordConfig recConfig) throws AWTException{
/*
            Date date= new Date();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
                                String parent=recConfig.getVideoFile().getParent();
                                String name=recConfig.getVideoFile().getName();
                                         
                                String removeext=FilenameUtils.removeExtension(name);
                                String string= parent+"\\"+removeext+"_"+j+"_"+dateFormat.format(date);
                                //System.out.println("New video file created: "+string);
                                File filename=new File(string+".mp4");
                                recConfig.setVideoFile1(filename);*/
                          
        RecordTimer=new RecordTimer();
        this.recConfig = recConfig;
        videoName=recConfig.getVideoFile1().getAbsolutePath();
         
        this.videoName=videoName;
        System.out.println("Video path is: "+videoName);
        capBounds=recConfig.getFrameDimension_videoting();
        
         System.out.println("Frame Dimension: "+
                "X="+recConfig.getFrameDimension_videoting().getX()+ 
                " Y="+recConfig.getFrameDimension_videoting().getY()+ 
                " W="+recConfig.getFrameDimension_videoting().getWidth()+
                " H="+recConfig.getFrameDimension_videoting().getHeight());
        
        FRAME_RATE=recConfig.getFramesRate();
        
        System.out.println("Start recording");
        robot=new Robot();
        writer= ToolFactory.makeWriter(videoName);
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, capBounds.width, capBounds.height);
        start=System.currentTimeMillis();

        timer.start();

        RecordTimer.startcounter(recConfig);
        j++;
    }
        
    public void stopRecording(){

        RecordTimer.stopcounter();

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
        }
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
    
        public void autoRecording(RecordConfig recConfig) throws AWTException  {

        RecordTimer=new RecordTimer();
        this.recConfig=recConfig;
        
        Date date= new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
        String str=recConfig.getVideoFile().getAbsolutePath();
        String parent=recConfig.getVideoFile().getParent();
        String name=recConfig.getVideoFile().getName();
        String ext=FilenameUtils.getExtension(str);
        String removeext=FilenameUtils.removeExtension(name);
        File newfilename=new File(parent+"\\"+removeext+"_"+dateFormat.format(date)+"."+ext);
        recConfig.setVideoFile1(newfilename);
         
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