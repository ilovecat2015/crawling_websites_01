package core;

import core.config.RecordConfig;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;

public class RecordTimer {
    private static Timer t;
    private static int count=0;

    public static void stopcounter() {
        t.stop();  
        count=0;
    }

       public  void startcounter(final RecordConfig recConfig) {
            t=new Timer(1000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                String time=String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(count),
                TimeUnit.SECONDS.toMinutes(count) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(count)),
                TimeUnit.SECONDS.toSeconds(count) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(count)));
                System.out.println(time);
                recConfig.setCounter(time);
            }
        });
           t.start();
    }
}

/*
    public static void startcounter() {
            t=new Timer(1000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                String time=String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(count),
                TimeUnit.SECONDS.toMinutes(count) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(count)),
                TimeUnit.SECONDS.toSeconds(count) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(count)));
                System.out.println(time);
            }
        });
           t.start();
    }*/