package core.recorder;

import core.config.RecordConfig;
import javax.swing.JOptionPane;

// this class provides implimentation for the frame rate selector
public class DurationSelector {

    // the constructor with record configuration parameter
    public DurationSelector(RecordConfig reConfig) {
        // the record config is not null then
        if (reConfig != null) {
            // show input dialog to get the frame rate
            Object value = JOptionPane.showInputDialog(
                    null,
                    "Enter Video Duration (in seconds)",
                    "Duration Selector",
                    JOptionPane.OK_CANCEL_OPTION,
                    new javax.swing.ImageIcon(getClass().getResource("/gui/resources/framerate.png")),
                    null,
                    reConfig.getDuration()/1000);
            // if the entered value is not null and empty
            if (value != null && !value.toString().isEmpty()) {
                try {
                    // set the Duration to the record config
                    reConfig.setDuration(Integer.parseInt(value.toString()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid duration! duration should be a number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
