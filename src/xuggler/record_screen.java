package xuggler;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IRational;
import core.RecordTimer;
import core.config.RecordConfig;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class record_screen implements ActionListener {
    private Robot robot;
    private Rectangle capBounds;
    private IMediaWriter writer;
    private String videoName;
    private int delayBetweenFrames=40;
    private Timer timer=new Timer(delayBetweenFrames, this);
    private long start=0;
    private long pause;
    private final double FRAME_RATE ;
    private final int SECS_TO_RUN_FOR;

    public record_screen (String videoName, Rectangle frame, double rate,int secs){
        this.videoName=videoName;
        capBounds=frame;
        FRAME_RATE=rate;
        SECS_TO_RUN_FOR=secs;
    }

    public void startCapture() throws AWTException{
        robot=new Robot();
        writer= ToolFactory.makeWriter(videoName);
        writer.addVideoStream(0,0, IRational.make(25,1),capBounds.width,capBounds.height);
        start=System.currentTimeMillis();
        timer.start();
        System.out.println(start);
        System.out.println(timer);
    }

    public void stopCapture(){
        timer.stop();
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        writer.close();
        start=0;
    }  

    public void actionPerformed(ActionEvent e){
        System.out.println(e.getSource());
        if(e.getSource()==timer){
            System.out.println(e.getSource());
            System.out.println("11");
            BufferedImage screen=getCaptureImage();
            writer.encodeVideo(0,screen,System.currentTimeMillis()-start,TimeUnit.MILLISECONDS);
        }
    }

    private BufferedImage getCaptureImage(){
        BufferedImage screen=robot.createScreenCapture(capBounds);
        System.out.println(screen.getType());
        System.out.println(BufferedImage.TYPE_3BYTE_BGR);
        if(screen.getType()!=BufferedImage.TYPE_3BYTE_BGR){
            System.out.println("00");
            BufferedImage image=new BufferedImage(screen.getWidth(),screen.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g=image.createGraphics();
            g.drawImage(screen,0,0,null);
            screen=image;
            g.dispose();
        }
        return screen;
    }

    public static void main(String[] args) throws Exception{
    	record_screen cs=new record_screen("C:\\Users\\jonathan\\Desktop\\aaaa.mp4",new Rectangle(2,128,654,382),13,10);
        cs.startCapture();
        Thread.sleep(3000);
        System.err.println("pause end");
        cs.stopCapture();
        Thread.sleep(200);
    }
    public void test1() throws AWTException  {
        
        try {
            Thread.sleep(8000);
            System.out.println("Wait 8 seconds, then start recording");
        } catch (InterruptedException ex) {
            Logger.getLogger(record_screen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //System.out.println("Start recording");
        final IMediaWriter writer = ToolFactory.makeWriter(videoName);
        robot = new Robot();
        // add 1 video stream at position 0 and fixed frame rate of framerate
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, capBounds.width, capBounds.height);
        start=System.currentTimeMillis();
        timer.start();
        long startTime = System.nanoTime();
        
        for (int i = 0;  i < SECS_TO_RUN_FOR * FRAME_RATE; i++){
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
        timer.stop();
        start=0;
        writer.close();
        
        //System.out.println("Finished recording");
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
    private  BufferedImage getScreenshot(){
        return robot.createScreenCapture(capBounds);
    }   
}