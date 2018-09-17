package core;

import core.config.RecordConfig;
import core.constants.Extension;
import core.filters.VideoFileFilter;
import java.io.File;
import javax.swing.JFileChooser;

// this class impliments the video file save chooser
public class SaveFileChooser {

    // the constructor with the record config as parameter
    public SaveFileChooser(RecordConfig reConfig) {
        // create a file chooser
        //JFileChooser jfc = new JFileChooser(Directory.VIDEO_DB);

        
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(System.getProperty("user.home")+"\\Desktop"));
        // disable multi file selection
        jfc.setMultiSelectionEnabled(false);
        // set video file filter
        jfc.setFileFilter(new VideoFileFilter());

        // if already selected a save file then set is as the default selection
        if (reConfig != null && reConfig.getVideoFile() != null) {
            jfc.setSelectedFile(reConfig.getVideoFile());
            //System.out.println("Video will be saved in this path "+reConfig.getVideoFile());
        }

        // show the chooser dialog
        int result = jfc.showSaveDialog(jfc);
        //System.out.println(result);
        // on user selection
        if (result == JFileChooser.APPROVE_OPTION) {
        //System.out.println(result);
            // if the selected file doesnot ends with the video file extension 
            // then add the extension to it and set the save video file
            if (jfc.getSelectedFile().getName().toLowerCase().endsWith(Extension.VIDEO_EXTENSION)) {
                reConfig.setVideoFile(jfc.getSelectedFile());
                //reConfig.setVideoFile1(jfc.getSelectedFile());
      
                System.out.println("Video will be saved in this path: "+jfc.getSelectedFile());
                
            } else {
                System.err.println("******Warning: Please check if your video extension file is mp4 or not!******");
                System.err.println("Video will be saved in this path: "+jfc.getSelectedFile());
                reConfig.setVideoFile(new File(jfc.getSelectedFile().getAbsolutePath() + Extension.VIDEO_EXTENSION));
                //reConfig.setVideoFile1(new File(jfc.getSelectedFile().getAbsolutePath() + Extension.VIDEO_EXTENSION));
                //System.out.println("Video will be saved in this path: "+reConfig.getVideoFile());

            }
        }
    }
}
