
package gui;

import core.config.RecordConfig;
import java.awt.Toolkit;
import java.io.IOException;

// this class provides ths imapliementation for the main window
public class MainWindow extends javax.swing.JFrame {
    // the local record configuration  reference
    private RecordConfig recConfig = null;
    // the default constructor
    public MainWindow() throws IOException {
        // create a new record configuration object
        recConfig = new RecordConfig();
        // initilise the components
        initComponents();
        
        setIcon();
   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        recordControlPanel = new gui.RecordControlPanel(recConfig);
        recordConfigInfoPanel2 = new gui.RecordConfigInfoPanel(recConfig);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TVB Screen Recorder");
        setIconImages(null);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(recordControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
            .addComponent(recordConfigInfoPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(recordControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(recordConfigInfoPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.RecordConfigInfoPanel recordConfigInfoPanel2;
    private gui.RecordControlPanel recordControlPanel;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
   
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon_1.png")));

    }
}
