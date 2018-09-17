
package core.filters;

import core.constants.Extension;
import java.io.File;
import javax.swing.filechooser.FileFilter;

// this class provides implimentaion for the video file filter
public class VideoFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {

        String name = f.getName().toLowerCase();
        // accept only the specified formated video files and directories
        return f.isDirectory()
                || name.endsWith(Extension.VIDEO_EXTENSION);
    }

    @Override
    public String getDescription() {
        return "Video Files";
    }
}
