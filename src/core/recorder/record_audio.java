package core.recorder;


import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by jp61639 on 8/4/2016.
 */
public class record_audio implements Runnable{





    private AudioFormat af=new AudioFormat(
            44100,16,1,true,true
    );

    private ByteArrayOutputStream bosAudio;

    private boolean running, capture;

    public record_audio(){
        bosAudio=new ByteArrayOutputStream();
    }

    public void setPause(boolean pause){
        capture=!pause;
    }
    public void stop(){
        running=false;
    }

    public byte[] getData(){
        byte[] erg=bosAudio.toByteArray();
        bosAudio.reset();
        return erg;
    }

    public void run(){
        DataLine.Info info=new DataLine.Info(
                TargetDataLine.class, af
        );
        TargetDataLine line=null;
        running=true;
        capture=true;
        try{
            line=(TargetDataLine) AudioSystem.getLine(info);
            byte[] buffer=new byte[400];
            line.open(af);
            line.start();
            while(running){
                int count=line.read(buffer,0,buffer.length);
                if(count>0 && capture)
                    bosAudio.write(buffer,0,count);

            }

        }catch (LineUnavailableException e){
            e.printStackTrace();
        }finally{
            try{
                bosAudio.close();
            }catch(IOException e){
                e.printStackTrace();

            }
            if(line!=null){
                line.flush();
                line.stop();
                line.close();
            }



        }
    }






}
