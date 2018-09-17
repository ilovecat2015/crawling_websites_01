package core;

import core.config.RecordConfig;
import javax.swing.JOptionPane;

// this class provides implimentation for the frame rate selector
public class FrameRateSelector {

    // the constructor with record configuration parameter
    public FrameRateSelector(RecordConfig reConfig) {
        // the record config is not null then
        if (reConfig != null) {
            // show input dialog to get the frame rate
            Object value = JOptionPane.showInputDialog(
                    null,
                    "Enter Capture Frame Rate (fps)",
                    "Frame Rate Selector",
                    JOptionPane.OK_CANCEL_OPTION,
                    new javax.swing.ImageIcon(getClass().getResource("/gui/resources/fps.png")),
                    null,
                    reConfig.getFramesRate());
            // if the entered value is not null and empty
            if (value != null && !value.toString().isEmpty()) {
                try {
                    // set the frame rate to the record config
                    reConfig.setFramesRate(Integer.parseInt(value.toString()));
                
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid fps! fps should be a number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
