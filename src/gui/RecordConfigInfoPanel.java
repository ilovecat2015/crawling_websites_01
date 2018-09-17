package gui;

import core.config.RecordConfig;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

// this class impliments the record configuration info panel
public class RecordConfigInfoPanel extends javax.swing.JPanel implements PropertyChangeListener {

    // the record config refernce object
    private RecordConfig recConfig = null;

    //pop up to JTextArea
    public void run() {
        redirectSystemStreams();
        System.out.println("--------------------------------------------");
        System.out.println("THIS IS A SYSTEM FOR SCREEN RECORDING");
        System.out.println("--------------------------------------------");
    }

//The following codes set where the text get redirected. In this case, jTextArea1
    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jTextArea1.append(text);
            }
        });
    }

//Followings are The Methods that do the Redirect, you can simply Ignore them.
    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    // the default constructor
    public RecordConfigInfoPanel() throws IOException {
        // initilise the visual components
        initComponents();
        // create new record config
        recConfig = new RecordConfig();
        // add the current class a listner to the record config
        recConfig.addPropertyChangeListener(this);
        // load config info onto the panel
        loadConfigInfo();
        if (RecordControlPanel.autopress) {
            loadConfigInfo1();
        }
    }

    // constructor with record config as parameter
    public RecordConfigInfoPanel(RecordConfig rc) {
        // initilise visual components
        initComponents();
        // set the local record config reference
        recConfig = rc;
        // add the current panel as a listner to the record config
        recConfig.addPropertyChangeListener(this);
        // load config info onto the panel
        loadConfigInfo();
        if (RecordControlPanel.autopress) {
            loadConfigInfo1();
        }
        run();
    }

    // method to load the record config info onto the panel
    public final void loadConfigInfo() {
        // display the video file size
        //System.out.println("loadconfig1");
        try {
            sizeValue.setForeground(Color.blue);
            sizeValue.setText(humanReadableByteCount(new FileInputStream(recConfig.getVideoFile()).available(), true));
        } catch (Exception ex) {
            sizeValue.setForeground(Color.red);
            sizeValue.setText("Unknown File Size!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension() != null) {
            frameDimensionValue.setForeground(Color.blue);
            frameDimensionValue.setText("("
                    + recConfig.getFrameDimension().x
                    + ", "
                    + recConfig.getFrameDimension().y
                    + ", "
                    + recConfig.getFrameDimension().width
                    + ", "
                    + recConfig.getFrameDimension().height
                    + ")");
        } else {
            frameDimensionValue.setForeground(Color.red);
            frameDimensionValue.setText("Unknown Dimension!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension_filehoot() != null) {

            frame_filehootValue.setForeground(Color.blue);
            frame_filehootValue.setText("("
                    + recConfig.getFrameDimension_filehoot().x
                    + ", "
                    + recConfig.getFrameDimension_filehoot().y
                    + ", "
                    + recConfig.getFrameDimension_filehoot().width
                    + ", "
                    + recConfig.getFrameDimension_filehoot().height
                    + ")");
        } else {
            frame_filehootValue.setForeground(Color.red);
            frame_filehootValue.setText("Unknown Dimension!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension_h265() != null) {

            frame_h265Value.setForeground(Color.blue);
            frame_h265Value.setText("("
                    + recConfig.getFrameDimension_h265().x
                    + ", "
                    + recConfig.getFrameDimension_h265().y
                    + ", "
                    + recConfig.getFrameDimension_h265().width
                    + ", "
                    + recConfig.getFrameDimension_h265().height
                    + ")");
        } else {
            frame_h265Value.setForeground(Color.red);
            frame_h265Value.setText("Unknown Dimension!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension_nowvideo() != null) {

            frame_nowvideoValue.setForeground(Color.blue);
            frame_nowvideoValue.setText("("
                    + recConfig.getFrameDimension_nowvideo().x
                    + ", "
                    + recConfig.getFrameDimension_nowvideo().y
                    + ", "
                    + recConfig.getFrameDimension_nowvideo().width
                    + ", "
                    + recConfig.getFrameDimension_nowvideo().height
                    + ")");
        } else {
            frame_nowvideoValue.setForeground(Color.red);
            frame_nowvideoValue.setText("Unknown Dimension!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension_xtubeth() != null) {

            frame_xtubethValue.setForeground(Color.blue);
            frame_xtubethValue.setText("("
                    + recConfig.getFrameDimension_xtubeth().x
                    + ", "
                    + recConfig.getFrameDimension_xtubeth().y
                    + ", "
                    + recConfig.getFrameDimension_xtubeth().width
                    + ", "
                    + recConfig.getFrameDimension_xtubeth().height
                    + ")");
        } else {
            frame_xtubethValue.setForeground(Color.red);
            frame_xtubethValue.setText("Unknown Dimension!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension_videobug() != null) {

            frame_videobugValue.setForeground(Color.blue);
            frame_videobugValue.setText("("
                    + recConfig.getFrameDimension_videobug().x
                    + ", "
                    + recConfig.getFrameDimension_videobug().y
                    + ", "
                    + recConfig.getFrameDimension_videobug().width
                    + ", "
                    + recConfig.getFrameDimension_videobug().height
                    + ")");
        } else {
            frame_videobugValue.setForeground(Color.red);
            frame_videobugValue.setText("Unknown Dimension!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension_videoting() != null) {

            frame_videotingValue.setForeground(Color.blue);
            frame_videotingValue.setText("("
                    + recConfig.getFrameDimension_videoting().x
                    + ", "
                    + recConfig.getFrameDimension_videoting().y
                    + ", "
                    + recConfig.getFrameDimension_videoting().width
                    + ", "
                    + recConfig.getFrameDimension_videoting().height
                    + ")");
        } else {
            frame_videotingValue.setForeground(Color.red);
            frame_videotingValue.setText("Unknown Dimension!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension_vodlocker() != null) {

            frame_vodlockerValue.setForeground(Color.blue);
            frame_vodlockerValue.setText("("
                    + recConfig.getFrameDimension_vodlocker().x
                    + ", "
                    + recConfig.getFrameDimension_vodlocker().y
                    + ", "
                    + recConfig.getFrameDimension_vodlocker().width
                    + ", "
                    + recConfig.getFrameDimension_vodlocker().height
                    + ")");
        } else {
            frame_vodlockerValue.setForeground(Color.red);
            frame_vodlockerValue.setText("Unknown Dimension!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension_stream() != null) {

            frame_streamValue.setForeground(Color.blue);
            frame_streamValue.setText("("
                    + recConfig.getFrameDimension_stream().x
                    + ", "
                    + recConfig.getFrameDimension_stream().y
                    + ", "
                    + recConfig.getFrameDimension_stream().width
                    + ", "
                    + recConfig.getFrameDimension_stream().height
                    + ")");
        } else {
            frame_streamValue.setForeground(Color.red);
            frame_streamValue.setText("Unknown Dimension!");
        }

        // display the capture area dimension
        if (recConfig.getFrameDimension_openload() != null) {

            frame_openloadValue.setForeground(Color.blue);
            frame_openloadValue.setText("("
                    + recConfig.getFrameDimension_openload().x
                    + ", "
                    + recConfig.getFrameDimension_openload().y
                    + ", "
                    + recConfig.getFrameDimension_openload().width
                    + ", "
                    + recConfig.getFrameDimension_openload().height
                    + ")");
        } else {
            frame_openloadValue.setForeground(Color.red);
            frame_openloadValue.setText("Unknown Dimension!");
        }

        // display the save file name
        if (recConfig.getVideoFile() != null && !recConfig.getVideoFile().getName().isEmpty()) {
            saveFileValue.setForeground(Color.blue);
            saveFileValue.setText(recConfig.getVideoFile().getAbsolutePath());
            //System.out.println(recConfig.getVideoFile().getName());
            //System.out.println(recConfig.getVideoFile().getAbsolutePath());
            //System.out.println(recConfig.getVideoFile().getPath());

        } else {
            saveFileValue.setForeground(Color.red);
            saveFileValue.setText("Unknown File!");
        }
        
             




        // display the frame rate value
        counterValue.setText(recConfig.getCounter());
        counterValue.setForeground(Color.blue);
        //System.out.println(recConfig.getCounter());
        durationValue.setText(recConfig.getDuration() / 1000 + " Seconds");
        timeValue1.setText(recConfig.getTime() + " Seconds");
        frameRateValue.setText(recConfig.getFramesRate() + "fps");
           // display the website name
        websiteValue.setText(recConfig.getWebsite());
        websiteValue.setForeground(Color.blue);

        try {
            lengthValue.setForeground(Color.blue);
            lengthValue.setText(humanReadableTime(recConfig.getVideoLength()));
            //System.out.println(recConfig.getVideoLength());

        } catch (ParseException ex) {
            lengthValue.setForeground(Color.red);
            lengthValue.setText("Unknown Length!");
        }
        loadFileValue1.setForeground(Color.blue);
        loadFileValue1.setText(recConfig.getLoadFile().getAbsolutePath());
    }

    public static String humanReadableTime(long millis) throws ParseException {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return hms;
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lengthLabel1 = new javax.swing.JLabel();
        sizeLabel1 = new javax.swing.JLabel();
        saveFileLabel1 = new javax.swing.JLabel();
        lengthValue1 = new javax.swing.JLabel();
        sizeValue1 = new javax.swing.JLabel();
        saveFileValue1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        saveFileLabel2 = new javax.swing.JLabel();
        loadFileValue1 = new javax.swing.JLabel();
        durationLabel = new javax.swing.JLabel();
        durationValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        timeLabel1 = new javax.swing.JLabel();
        timeValue1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lengthLabel = new javax.swing.JLabel();
        frameRateLabel = new javax.swing.JLabel();
        lengthValue = new javax.swing.JLabel();
        frameRateValue = new javax.swing.JLabel();
        saveFileLabel = new javax.swing.JLabel();
        frameDimensionLabel = new javax.swing.JLabel();
        frameDimensionValue = new javax.swing.JLabel();
        saveFileValue = new javax.swing.JLabel();
        sizeLabel = new javax.swing.JLabel();
        sizeValue = new javax.swing.JLabel();
        counterLabel = new javax.swing.JLabel();
        counterValue = new javax.swing.JLabel();
        frame_videotingLabel = new javax.swing.JLabel();
        frame_streamValue = new javax.swing.JLabel();
        frame_vodlockerLabel = new javax.swing.JLabel();
        frame_h265Label = new javax.swing.JLabel();
        frame_videobugLabel = new javax.swing.JLabel();
        frame_vodlockerValue = new javax.swing.JLabel();
        frame_filehootLabel = new javax.swing.JLabel();
        frame_videotingValue = new javax.swing.JLabel();
        frame_streamLabel = new javax.swing.JLabel();
        frame_videobugValue = new javax.swing.JLabel();
        frame_xtubethLabel = new javax.swing.JLabel();
        frame_nowvideoValue = new javax.swing.JLabel();
        frame_nowvideoLabel = new javax.swing.JLabel();
        frame_h265Value = new javax.swing.JLabel();
        frame_openloadValue = new javax.swing.JLabel();
        frame_openloadLabel = new javax.swing.JLabel();
        frame_xtubethValue = new javax.swing.JLabel();
        frame_filehootValue = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        saveFileLabel3 = new javax.swing.JLabel();
        websiteValue = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Record Config Info"));

        lengthLabel1.setText("Length :");

        sizeLabel1.setText("Size :");

        saveFileLabel1.setText("Save File :");

        lengthValue1.setText("N/A");

        sizeValue1.setText("N/A");

        saveFileValue1.setText("N/A");

        jLabel1.setText("Record Config Info (Automation)");

        saveFileLabel2.setText("Load File :");

        loadFileValue1.setText("N/A");

        durationLabel.setText("Duration:");

        durationValue.setText("N/A");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        timeLabel1.setText("Time to start recording :");

        timeValue1.setText("N/A");

        lengthLabel.setText("Length :");

        frameRateLabel.setText("Frame Rate :");

        lengthValue.setText("jLabel9");

        frameRateValue.setText("jLabel11");

        saveFileLabel.setText("Save File :");

        frameDimensionLabel.setText("Frame Dimension (x,y,w,h) :");

        frameDimensionValue.setText("jLabel12");

        saveFileValue.setText("jLabel16");

        sizeLabel.setText("Size :");

        sizeValue.setText("jLabel10");

        counterLabel.setText("Counter :");

        counterValue.setText("jLabel17");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveFileLabel)
                    .addComponent(lengthLabel)
                    .addComponent(frameDimensionLabel)
                    .addComponent(frameRateLabel)
                    .addComponent(sizeLabel)
                    .addComponent(counterLabel))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(counterValue)
                            .addComponent(sizeValue)
                            .addComponent(frameDimensionValue)
                            .addComponent(frameRateValue)
                            .addComponent(lengthValue))
                        .addGap(329, 329, 329))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(saveFileValue, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lengthLabel)
                    .addComponent(lengthValue))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sizeLabel)
                    .addComponent(sizeValue))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(frameRateLabel)
                        .addGap(0, 0, 0)
                        .addComponent(frameDimensionLabel)
                        .addGap(0, 0, 0)
                        .addComponent(saveFileLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(frameRateValue)
                        .addGap(0, 0, 0)
                        .addComponent(frameDimensionValue)
                        .addGap(0, 0, 0)
                        .addComponent(saveFileValue)))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(counterLabel)
                    .addComponent(counterValue))
                .addGap(0, 0, 0))
        );

        frame_videotingLabel.setText("Frame Dimension (x,y,w,h) - videoting:");

        frame_streamValue.setText("jLabel12");

        frame_vodlockerLabel.setText("Frame Dimension (x,y,w,h) - vodlocker:");

        frame_h265Label.setText("Frame Dimension (x,y,w,h) - h265:");

        frame_videobugLabel.setText("Frame Dimension (x,y,w,h) - videobug:");

        frame_vodlockerValue.setText("jLabel12");

        frame_filehootLabel.setText("Frame Dimension (x,y,w,h) - filehoot:");

        frame_videotingValue.setText("jLabel12");

        frame_streamLabel.setText("Frame Dimension (x,y,w,h) - stream:");

        frame_videobugValue.setText("jLabel12");

        frame_xtubethLabel.setText("Frame Dimension (x,y,w,h) - xtubeth:");

        frame_nowvideoValue.setText("jLabel12");

        frame_nowvideoLabel.setText("Frame Dimension (x,y,w,h) - nowvideo:");

        frame_h265Value.setText("jLabel12");

        frame_openloadValue.setText("jLabel12");

        frame_openloadLabel.setText("Frame Dimension (x,y,w,h) - openload:");

        frame_xtubethValue.setText("jLabel12");

        frame_filehootValue.setText("jLabel12");

        saveFileLabel3.setText("Website :");

        websiteValue.setText("jLabel17");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(frame_videotingLabel)
                            .addComponent(frame_videobugLabel)
                            .addComponent(frame_nowvideoLabel)
                            .addComponent(frame_openloadLabel)
                            .addComponent(frame_streamLabel)
                            .addComponent(frame_xtubethLabel)
                            .addComponent(frame_h265Label)
                            .addComponent(frame_vodlockerLabel)
                            .addComponent(frame_filehootLabel))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(frame_videotingValue)
                            .addComponent(frame_videobugValue)
                            .addComponent(frame_vodlockerValue)
                            .addComponent(frame_nowvideoValue)
                            .addComponent(frame_openloadValue)
                            .addComponent(frame_streamValue)
                            .addComponent(frame_xtubethValue)
                            .addComponent(frame_h265Value)
                            .addComponent(frame_filehootValue, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saveFileLabel2)
                            .addComponent(saveFileLabel1)
                            .addComponent(durationLabel)
                            .addComponent(timeLabel1)
                            .addComponent(sizeLabel1)
                            .addComponent(lengthLabel1)
                            .addComponent(saveFileLabel3))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(websiteValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addComponent(lengthValue1)
                            .addComponent(sizeValue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(timeValue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(233, 233, 233))
                            .addComponent(durationValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saveFileValue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loadFileValue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
            .addComponent(jScrollPane1)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {frame_h265Value, frame_nowvideoValue, frame_streamValue, frame_videobugValue, frame_videotingValue, frame_vodlockerValue, frame_xtubethValue});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frame_h265Label)
                    .addComponent(frame_h265Value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frame_xtubethLabel)
                    .addComponent(frame_xtubethValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frame_streamLabel)
                    .addComponent(frame_streamValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frame_openloadLabel)
                    .addComponent(frame_openloadValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frame_nowvideoLabel)
                    .addComponent(frame_nowvideoValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frame_vodlockerLabel)
                    .addComponent(frame_vodlockerValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frame_videobugLabel)
                    .addComponent(frame_videobugValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frame_videotingLabel)
                    .addComponent(frame_videotingValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frame_filehootValue)
                    .addComponent(frame_filehootLabel))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lengthLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(sizeLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(timeLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(durationLabel)
                        .addGap(0, 0, 0)
                        .addComponent(saveFileLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(saveFileLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lengthValue1)
                        .addGap(0, 0, 0)
                        .addComponent(sizeValue1)
                        .addGap(0, 0, 0)
                        .addComponent(timeValue1)
                        .addGap(0, 0, 0)
                        .addComponent(durationValue)
                        .addGap(0, 0, 0)
                        .addComponent(saveFileValue1)
                        .addGap(0, 0, 0)
                        .addComponent(loadFileValue1)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveFileLabel3)
                    .addComponent(websiteValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {durationLabel, lengthLabel1, saveFileLabel1, saveFileLabel2, sizeLabel1, timeLabel1});

        frame_streamLabel.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel counterLabel;
    private javax.swing.JLabel counterValue;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JLabel durationValue;
    private javax.swing.JLabel frameDimensionLabel;
    private javax.swing.JLabel frameDimensionValue;
    private javax.swing.JLabel frameRateLabel;
    private javax.swing.JLabel frameRateValue;
    private javax.swing.JLabel frame_filehootLabel;
    private javax.swing.JLabel frame_filehootValue;
    private javax.swing.JLabel frame_h265Label;
    private javax.swing.JLabel frame_h265Value;
    private javax.swing.JLabel frame_nowvideoLabel;
    private javax.swing.JLabel frame_nowvideoValue;
    private javax.swing.JLabel frame_openloadLabel;
    private javax.swing.JLabel frame_openloadValue;
    private javax.swing.JLabel frame_streamLabel;
    private javax.swing.JLabel frame_streamValue;
    private javax.swing.JLabel frame_videobugLabel;
    private javax.swing.JLabel frame_videobugValue;
    private javax.swing.JLabel frame_videotingLabel;
    private javax.swing.JLabel frame_videotingValue;
    private javax.swing.JLabel frame_vodlockerLabel;
    private javax.swing.JLabel frame_vodlockerValue;
    private javax.swing.JLabel frame_xtubethLabel;
    private javax.swing.JLabel frame_xtubethValue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lengthLabel;
    private javax.swing.JLabel lengthLabel1;
    private javax.swing.JLabel lengthValue;
    private javax.swing.JLabel lengthValue1;
    private javax.swing.JLabel loadFileValue1;
    private javax.swing.JLabel saveFileLabel;
    private javax.swing.JLabel saveFileLabel1;
    private javax.swing.JLabel saveFileLabel2;
    private javax.swing.JLabel saveFileLabel3;
    private javax.swing.JLabel saveFileValue;
    private javax.swing.JLabel saveFileValue1;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JLabel sizeLabel1;
    private javax.swing.JLabel sizeValue;
    private javax.swing.JLabel sizeValue1;
    private javax.swing.JLabel timeLabel1;
    private javax.swing.JLabel timeValue1;
    private javax.swing.JLabel websiteValue;
    // End of variables declaration//GEN-END:variables

    // GETTER AND SETTER FOR RECORD CONFIG
    public RecordConfig getRecConfig() {
        return recConfig;
    }

    public void setRecConfig(RecordConfig recConfig) {
        this.recConfig = recConfig;
    }

    // on record config property change call refresh all values
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        loadConfigInfo();
        if (RecordControlPanel.autopress) {
            loadConfigInfo1();
        }
    }

    // method to load the record config info onto the panel
    public final void loadConfigInfo1() {
        //System.out.println("loadconfig2");
        // display the video file size
        try {
            sizeValue1.setForeground(Color.blue);
            sizeValue1.setText(humanReadableByteCount(new FileInputStream(recConfig.getVideoFile1()).available(), true));
        } catch (Exception ex) {
            sizeValue1.setForeground(Color.red);
            sizeValue1.setText("Unknown File Size!");
        }

        // display the save file name
        if (recConfig.getVideoFile1() != null && !recConfig.getVideoFile1().getName().isEmpty()) {
            saveFileValue1.setForeground(Color.blue);
            saveFileValue1.setText(recConfig.getVideoFile1().getAbsolutePath());
        } else {
            saveFileValue1.setForeground(Color.red);
            saveFileValue1.setText("Unknown File!");
        }

        try {
            lengthValue1.setForeground(Color.blue);
            lengthValue1.setText(humanReadableTime(recConfig.getVideoLength1()));
        } catch (ParseException ex) {
            lengthValue1.setForeground(Color.red);
            lengthValue1.setText("Unknown Length!");
        }
    }
}
