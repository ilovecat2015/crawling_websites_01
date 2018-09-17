package core;

import core.config.RecordConfig;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JFrame;

// this class impliments the video file save chooser
public class LoadFileChooser {

     public static File file;
    
    // the constructor with the record config as parameter
    public LoadFileChooser(RecordConfig reConfig) {
 

        // create a file chooser
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));
        
        FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
        fd.setDirectory("C:\\");
        fd.setFile("*.xls");
        fd.setVisible(true);
        String filename = fd.getFile();
        file = new File (fd.getDirectory ()+ fd.getFile ());
        
        if (filename == null){
            System.out.println("You cancelled the choice");
            
                      // if already selected a save file then set is as the default selection

        }
        else
        {
        System.out.println("Your chosen file is: " + file); 
        reConfig.setLoadFile(file);
        System.out.println(reConfig.getLoadFile());
        }
             
        }

    
}
