package core;

import core.config.RecordConfig;
import javax.swing.JOptionPane;

// this class provides implimentation for the frame rate selector
public class WebsiteSelector {

    // the constructor with record configuration parameter
    public WebsiteSelector(RecordConfig reConfig) {
        // the record config is not null then
        if (reConfig != null) {
            // show input dialog to get the frame rate
            Object value = JOptionPane.showInputDialog(
                    null,
                    "Enter Website",
                    "Website Selector",
                    JOptionPane.OK_CANCEL_OPTION,
                    new javax.swing.ImageIcon(getClass().getResource("/gui/resources/website.png")),
                    null,
                    reConfig.getWebsite());
            // if the entered value is not null and empty
            if (value != null && !value.toString().isEmpty()) {
                try {
                    // set the frame rate to the record config
                    reConfig.setWebsite(value.toString());
                    
                
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid website! it shoudd be a website.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
