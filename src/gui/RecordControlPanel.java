package gui;


import core.LoadFileChooser;
import static core.LoadFileChooser.file;

import core.WebsiteSelector;
import core.config.RecordConfig;

import core.recorder.ScreenRecorder2;
import core.recorder.ScreenRecorder_h265;
import core.recorder.ScreenRecorder_stream;
import core.recorder.ScreenRecorder_openload;
import core.recorder.ScreenRecorder_xtubeth;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.GridLayout;

import java.awt.Robot;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import main.player01_deletesheet;
import main.player01_remove_empty02;
import main.player01_sorting;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import selenium.h265;
import selenium.openload;
import selenium.tvb;
import selenium.stream;
import xuggler.videoInfo;
import youtube.UploadVideo;
import youtube.check_myuploads1;

import java.util.Arrays;
import main.player01_excel05;

import selenium.xtubeth;
import youtube.UploadVideo2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;



// this class provies implimentation for the record controller panel
public class RecordControlPanel extends javax.swing.JPanel {
    // the local record config reference object

    private RecordConfig recConfig = null;
    // the record image image
    private ImageIcon record = null;
    // the recording image icon
    private ImageIcon recording = null;
    private boolean isChecking = false;

    // is recording flag
    private boolean isRecording = false;
    private boolean isUploading = false;
    // the screen recorder object
    private ScreenRecorder2 screenRecorder2 = null;
    private ScreenRecorder_h265 screenRecorder_h265 = null;
    private ScreenRecorder_openload screenRecorder_openload = null;
    private ScreenRecorder_xtubeth screenRecorder_xtubeth = null;
    private ScreenRecorder_stream screenRecorder_stream = null;

    private videoInfo videoInfo = null;
    private player01_excel05 player01_excel05 = null;
    private player01_sorting player01_sorting = null;
    private player01_deletesheet player01_deletesheet = null;
    public static boolean autopress = false;
    private UploadVideo UploadVideo = null;

    int ss, mm, hh, sc, mn, hr;

    // the default constructor
    public RecordControlPanel() {
        initComponents();
    }

    // constructor with the record config parameter
    public RecordControlPanel(RecordConfig rc) {
        // initilise components
        initComponents();
        // set the record config refernce
        this.recConfig = rc;
        // load record icons
        loadRecordIcons();
        // create a new screen recorder object

        screenRecorder2 = new ScreenRecorder2();
        screenRecorder_h265 = new ScreenRecorder_h265();
        screenRecorder_openload = new ScreenRecorder_openload();
        screenRecorder_xtubeth = new ScreenRecorder_xtubeth();
        screenRecorder_stream = new ScreenRecorder_stream();

        videoInfo = new videoInfo();
        player01_excel05 = new player01_excel05();
        player01_sorting = new player01_sorting();
        player01_deletesheet = new player01_deletesheet();

    }

    // load the recor icons from the jar resource package
    private void loadRecordIcons() {
        record = new javax.swing.ImageIcon(getClass().getResource("/gui/resources/record.png"));
        recording = new javax.swing.ImageIcon(getClass().getResource("/gui/resources/recording.png"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        autorunButtonLabel = new javax.swing.JLabel();
        websiteButtonLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Record Controls"));

        autorunButtonLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/excel.png"))); // NOI18N
        autorunButtonLabel.setToolTipText("autorun(with Youtube upload - end)");
        autorunButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                autorun_MousePressedEvent(evt);
            }
        });

        websiteButtonLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/website.png"))); // NOI18N
        websiteButtonLabel.setToolTipText("set website");
        websiteButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                websiteButtonLabelframeRateButton_MousePressedEvent(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(websiteButtonLabel)
                .addGap(67, 67, 67)
                .addComponent(autorunButtonLabel)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(273, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(websiteButtonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autorunButtonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 53, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void websiteButtonLabelframeRateButton_MousePressedEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_websiteButtonLabelframeRateButton_MousePressedEvent
        // TODO add your handling code here:
        new WebsiteSelector(recConfig);
        System.out.println("Website: " + recConfig.getWebsite());
    }//GEN-LAST:event_websiteButtonLabelframeRateButton_MousePressedEvent

    private void autorun_MousePressedEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autorun_MousePressedEvent
        // TODO add your handling code here:

        Thread thread = new Thread(new Runnable() {
            public void run() {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new GridLayout(0, 1));
                FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
                //fd.setDirectory("C:\\");
                fd.setDirectory(System.getProperty("user.home") + "\\Desktop\\");
                fd.setFile("*.xls");
                fd.setVisible(true);
                String filename1 = fd.getFile();
                file = new File(fd.getDirectory() + fd.getFile());
                if (filename1 == null) {
                    System.out.println("You cancelled the choice");
                } else {
                    System.out.println("");
                    System.out.println("start checking");
                    File file1 = LoadFileChooser.file;
                    recConfig.setLoadFile(file1);
                    System.out.println("Your selected file is " + recConfig.getLoadFile());
                    autopress = true;
                    new File(System.getProperty("user.home") + "\\Desktop\\new\\").mkdir();
                    //new File(System.getProperty("user.home")+"\\Desktop\\new\\screen_monitor\\").mkdir();
                    new File(System.getProperty("user.home") + "\\Desktop\\new\\screen_result\\").mkdir();

                    new File(System.getProperty("user.home") + "\\Desktop\\new\\screen_savedvideo\\").mkdir();
                    recConfig.setVideoFile1(new File(System.getProperty("user.home") + "\\Desktop\\new\\screen_savedvideo\\test.mp4"));

                    file = file1;

                    //counter used to check for videoID
                    int x = 0;

                    //total number of rows in the excel sheet
                    int totalrownumber = 0;

                    //this array is used to store all video ID,
                    String[] ar0 = null;

                    try {
                        player01_remove_empty02.main(null);
                        FileInputStream file = new FileInputStream(file1);

                        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                        Document doc = documentBuilder.newDocument();
                        Element orders = doc.createElement("Orders");
                        doc.appendChild(orders);
                        Element order = doc.createElement("Order");
                        orders.appendChild(order);

                        HSSFWorkbook workbook = new HSSFWorkbook(file);
                        HSSFSheet sheet = workbook.getSheetAt(0);
                        Iterator<Row> rowIterator = sheet.iterator();
                        totalrownumber = sheet.getLastRowNum() + 1;
                        System.out.println("total number of row: " + totalrownumber);
                        ar0 = new String[totalrownumber];

                        int j = 100;
                        while (rowIterator.hasNext()) {
                            Row row = rowIterator.next();
                            Iterator<Cell> cellIterator = row.cellIterator();
                            Element test = doc.createElement("Test");
                            order.appendChild(test);

                            int i = 0;
                            while (cellIterator.hasNext() && i < 2) {
                                i++;
                                Cell cell = cellIterator.next();
                                switch (cell.getCellType()) {
                                    case Cell.CELL_TYPE_NUMERIC:
                                        break;
                                    case Cell.CELL_TYPE_STRING:
                                        String st = null;
                                        st = cell.getStringCellValue();
                                        String startstring = "http";
                                        boolean retval = st.startsWith(startstring);
                                        if (retval == false) {
                                            j++;
                                            System.out.println("Item: " + j);
                                            System.out.println(st);
                                            Element title1 = doc.createElement("Filmname");
                                            title1.appendChild(doc.createTextNode(st));
                                            test.appendChild(title1);
                                        } else {
                                            if (st.contains(" ")) {
                                                System.out.println("It contains space in the link and will update it");
                                                st = st.replaceAll(" ", "%20");
                                                System.out.println(st);
                                            } else {
                                                System.out.println("it contains no space");
                                                System.out.println(st);
                                            }

                                            Desktop.getDesktop().browse(new URI(st));
                                            int count = 0;
                                            try {

                                                //System.out.println(recConfig.getVideoFile1()+"00");
                                                Date date = new Date();
                                                SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
                                                String parent = recConfig.getVideoFile1().getParent();
                                                String name = recConfig.getVideoFile().getName();
                                                String removeext = FilenameUtils.removeExtension(name);
                                                String string = parent + "\\" + removeext + "_" + j + "_" + dateFormat.format(date);
                                                File filename = new File(string + ".mp4");
                                                recConfig.setVideoFile1(filename);

                                                if (st.startsWith("http://xtubeth.com")) {
                                                    xtubeth.main(null);
                                                    Thread.sleep(1000 * recConfig.getTime());
                                                    System.out.println("wait " + recConfig.getTime() + "s to start recording");
                                                    screenRecorder_xtubeth.startRecording2(recConfig);
                                                    Thread.sleep(recConfig.getDuration());
                                                    screenRecorder_xtubeth.stopRecording();
                                                    count = tvb.main();
                                                    UploadVideo2.uploadVideo(recConfig.getVideoFile1());

                                                    int count1 = recConfig.getDuration() / 1000;
                                                    String time = String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(count1),
                                                            TimeUnit.SECONDS.toMinutes(count1) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(count1)),
                                                            TimeUnit.SECONDS.toSeconds(count1) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(count1)));

                                                    Element duration = doc.createElement("Duration");
                                                    duration.appendChild(doc.createTextNode(time));
                                                    test.appendChild(duration);

                                                } else if (st.startsWith("http://h265")) {
                                                    h265.main(null);
                                                    Thread.sleep(1000 * recConfig.getTime());
                                                    System.out.println("wait " + recConfig.getTime() + "s to start recording");
                                                    screenRecorder_h265.startRecording2(recConfig);
                                                    Thread.sleep(recConfig.getDuration());
                                                    screenRecorder_h265.stopRecording();
                                                    count = tvb.main();
                                                    UploadVideo2.uploadVideo(recConfig.getVideoFile1());

                                                    int count1 = recConfig.getDuration() / 1000;
                                                    String time = String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(count1),
                                                            TimeUnit.SECONDS.toMinutes(count1) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(count1)),
                                                            TimeUnit.SECONDS.toSeconds(count1) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(count1)));

                                                    Element duration = doc.createElement("Duration");
                                                    duration.appendChild(doc.createTextNode(time));
                                                    test.appendChild(duration);
                                                } else if (st.startsWith("https://stream.moe")) {

                                                    stream.click(recConfig.getFrameDimension_stream());
                                                    Thread.sleep(1000 * recConfig.getTime());
                                                    System.out.println("wait " + recConfig.getTime() + "s to start recording");
                                                    screenRecorder_stream.startRecording2(recConfig);
                                                    Thread.sleep(recConfig.getDuration());
                                                    screenRecorder_stream.stopRecording();
                                                    count = tvb.main();
                                                    UploadVideo2.uploadVideo(recConfig.getVideoFile1());

                                                    int count1 = recConfig.getDuration() / 1000;
                                                    String time = String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(count1),
                                                            TimeUnit.SECONDS.toMinutes(count1) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(count1)),
                                                            TimeUnit.SECONDS.toSeconds(count1) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(count1)));

                                                    Element duration = doc.createElement("Duration");
                                                    duration.appendChild(doc.createTextNode(time));
                                                    test.appendChild(duration);
                                                } else if (st.startsWith("https://openload")) {

                                                    //for checking if the website is 404 or not
                                                    int checking = 0;
                                                    System.out.println("checking" + checking);
                                                    checking = openload.main();

                                                    if (checking == 1) {
                                                        Thread.sleep(1000 * recConfig.getTime());
                                                        System.out.println("wait " + recConfig.getTime() + "s to start recording");

                                                        screenRecorder_openload.startRecording2(recConfig);

                                                        System.out.println("It will only record 5s as found sorry logo");
                                                        Thread.sleep(5000);
                                                        screenRecorder_openload.stopRecording();
                                                        count = tvb.main();
                                                        UploadVideo2.uploadVideo(recConfig.getVideoFile1());

                                                        int count1 = 5;
                                                        String time = String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(count1),
                                                                TimeUnit.SECONDS.toMinutes(count1) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(count1)),
                                                                TimeUnit.SECONDS.toSeconds(count1) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(count1)));
                                                        //System.out.println(time);

                                                        Element duration = doc.createElement("Duration");
                                                        duration.appendChild(doc.createTextNode(time));
                                                        test.appendChild(duration);

                                                    } else {
                                                        Thread.sleep(1000 * recConfig.getTime());
                                                        System.out.println("wait " + recConfig.getTime() + "s to start recording");
                                                        screenRecorder_openload.startRecording2(recConfig);
                                                        Thread.sleep(recConfig.getDuration());
                                                        screenRecorder_openload.stopRecording();
                                                        count = tvb.main();
                                                        UploadVideo2.uploadVideo(recConfig.getVideoFile1());

                                                        int count1 = recConfig.getDuration() / 1000;
                                                        String time = String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(count1),
                                                                TimeUnit.SECONDS.toMinutes(count1) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(count1)),
                                                                TimeUnit.SECONDS.toSeconds(count1) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(count1)));
                                                        //System.out.println(time);

                                                        Element duration = doc.createElement("Duration");
                                                        duration.appendChild(doc.createTextNode(time));
                                                        test.appendChild(duration);
                                                    }
                                                } else {
                                                    Thread.sleep(1000 * recConfig.getTime());
                                                    System.out.println("wait " + recConfig.getTime() + "s to start recording");
                                                    //screenRecorder1.startRecording2(recConfig);
                                                    screenRecorder2.startRecording2(recConfig);
                                                    Thread.sleep(recConfig.getDuration());
                                                    //screenRecorder1.stopRecording();
                                                    screenRecorder2.stopRecording();
                                                    count = tvb.main();
                                                    UploadVideo2.uploadVideo(recConfig.getVideoFile1());

                                                    int count1 = recConfig.getDuration() / 1000;
                                                    String time = String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(count1),
                                                            TimeUnit.SECONDS.toMinutes(count1) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(count1)),
                                                            TimeUnit.SECONDS.toSeconds(count1) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(count1)));
                                                    //System.out.println(time);

                                                    Element duration = doc.createElement("Duration");
                                                    duration.appendChild(doc.createTextNode(time));
                                                    test.appendChild(duration);

                                                }
                                            } catch (Exception ex) {
//                                                                    JOptionPane.showMessageDialog(saveButtonLabel, "Cannot record for the given configuration!\nError Info:\n" + ex, "Processor Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            Element link = doc.createElement("Link");
                                            link.appendChild(doc.createTextNode(st));
                                            test.appendChild(link);
                                            String str = recConfig.getVideoFile1().getAbsolutePath();
                                            Element mp4 = doc.createElement("MP4");
                                            mp4.appendChild(doc.createTextNode(str));
                                            test.appendChild(mp4);
                                            String st1 = null;

                                            if (count == 1) {
                                                st1 = "TVB logo not found";
                                            } else if (count == 2) {
                                                st1 = "TVB logo found";
                                            } else {
                                                st1 = "TVB logo read Error";
                                            }
                                            Element block = doc.createElement("Block");
                                            block.appendChild(doc.createTextNode(st1));
                                            test.appendChild(block);

                                            String videoID = UploadVideo2.getvideoID();
                                            Element ID = doc.createElement("VideoID");
                                            ID.appendChild(doc.createTextNode(videoID));
                                            test.appendChild(ID);

                                            Robot robot = new Robot();
                                            robot.keyPress(KeyEvent.VK_CONTROL);
                                            robot.keyPress(KeyEvent.VK_W);
                                            robot.keyRelease(KeyEvent.VK_CONTROL);
                                            robot.keyRelease(KeyEvent.VK_W);

                                            //System.out.println("videoid= "+UploadVideo2.getvideoID());
                                            //System.out.println("x="+x);
                                            ar0[x] = UploadVideo2.getvideoID();
                                            //System.out.println("id["+x+"]="+ar0[x]);
                                            x++;
                                            //System.out.println("x="+x);

                                        }
                                        break;
                                }

                            }
                            System.out.println("");
                        }
                        file.close();
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(doc);
                        StreamResult streamResult = new StreamResult(new File(System.getProperty("user.home") + "\\Desktop\\new\\screen_result.xml"));
                        transformer.transform(source, streamResult);
                        //System.out.println(recConfig.getVideoFile());
                        //player01_excel02.main2(System.getProperty("user.home")+"\\Desktop\\new\\screen_result.xml");

                        player01_excel05.main1(recConfig);
                        player01_sorting.main1(recConfig);
                        player01_deletesheet.main1(recConfig);
                        //System.out.println(file1);
                        //     if (path1.endsWith("xlsx")){
                        //     player01_deletefile.main(file1);}

                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println("Program has been finished");
                    System.out.println("");

                    //printout all the video ID
                    //System.out.println(Arrays.toString(ar0));
                    for (int i = 1; i <= totalrownumber; i++) {
                        System.out.println(i + "." + ar0[i - 1]);
                    }
                    System.out.println("");

                    //print out all the blank array
                    String[] ar2 = new String[totalrownumber];
                    //System.out.println(Arrays.toString(ar2));

                    for (int i = 1; i <= totalrownumber; i++) {
                        System.out.print(i + "." + ar2[i - 1] + ((i % 10 == 0) ? "\n" : " "));
                    }
                    System.out.println("");

                    //for monitor is checking is finished
                    int repeat_checking = 1;
                    try {
                        while (repeat_checking == 1) {

                            System.out.println("wait 30seconds and check again");
                            Thread.sleep(30000);

                            //this array is used to store all the video ID
                            String ar1[] = null;

                            for (int k = 0; k < x; k++) {
                                ar1 = check_myuploads1.check_myuploads1(ar0[k]);
                                ar2[k] = ar1[5];
                            }
                            //System.out.println(Arrays.toString(ar2));
                            System.out.println("");
                            //print array in 5numbers per line
                            int i;
                            for (i = 1; i <= x; i++) {
                                System.out.println(i + ". " + ar2[i - 1]);
                            }

                            //used to monitor if there are "null" in the array or not
                            if (Arrays.toString(ar2).contains("null")) {
                                //System.out.println("has null");
                                repeat_checking = 1;
                                //System.out.println("repeat_checking 1= "+repeat_checking);
                            } else {
                                repeat_checking = 0;
                                // System.out.println("repeat_checking 2="+repeat_checking);
                            }

                        }

                        System.out.println("");
                        check_myuploads1.main1(recConfig.getLoadFile().toString());

                        player01_sorting.main(null);
                        player01_deletesheet.main(null);
                        Desktop.getDesktop().open(recConfig.getLoadFile());
                    } catch (Exception ex) {
                        Logger.getLogger(RecordControlPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();
    }//GEN-LAST:event_autorun_MousePressedEvent

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:

        //System.setProperty("http.proxyHost", "127.0.0.1");
        //System.setProperty("http.proxyPort", "8182");
   

    }//GEN-LAST:event_jButton1MousePressed

    int i = 1;

    private void recordTime(int hh, int mm, int ss, String name) throws InterruptedException, IOException {

        final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time = timeFormat.format(date);
        // jLabel1.setText(time);

        // hh=16; mm=20; ss=00;                             
        sc = date.getSeconds();
        mn = date.getMinutes();
        hr = date.getHours();
        //date.getDay();

        if (mn == mm && hr == hh && sc == ss) {

            String str = recConfig.getVideoFile().getAbsolutePath();
            String parent = recConfig.getVideoFile().getParent();
            String ext = FilenameUtils.getExtension(str);
            File newfilename = new File(parent + "\\" + name + "." + ext);
            recConfig.setVideoFile1(newfilename);

            System.out.println("file name is " + newfilename);
            System.out.println("Recording start time is " + hh + ":" + mm + ":" + ss);
            System.out.println("Matched " + i + " epg ");
            i++;
            try {
                // print();
                screenRecorder2.autoRecording(recConfig);

                //videoInfo.main(recConfig);
            } catch (AWTException ex) {
                Logger.getLogger(RecordControlPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel autorunButtonLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel websiteButtonLabel;
    // End of variables declaration//GEN-END:variables

    // getter for  record config
    public RecordConfig getRecConfig() {
        return recConfig;
    }

    // setter for record config
    public void setRecConfig(RecordConfig recConfig) {
        this.recConfig = recConfig;
    }

    public boolean getboolean() {
        return isReadyForRecording();
    }

    // method to check whether the configuration is set for recording
    public boolean isReadyForRecording() {
        // if capture area is not set request user to select the capture area
        if (recConfig.getFrameDimension() == null) {
            //         JOptionPane.showMessageDialog(saveButtonLabel, "Capture area not set!\nPlease select capture area.", "Insufficient Configuration", JOptionPane.WARNING_MESSAGE);
            return false;
        } // if the frame rate is not set then request user to set the frame rate
        else if (recConfig.getFramesRate() == 0) {
            //        JOptionPane.showMessageDialog(saveButtonLabel, "Frame rate not set!\nPlease set the frame rate.", "Insufficient Configuration", JOptionPane.WARNING_MESSAGE);
            return false;
        } // if the video save file is not set then request user to select one
        else if (recConfig.getVideoFile() == null) {
            //      JOptionPane.showMessageDialog(saveButtonLabel, "Save file not set!\nPlease select a file to save the recorded video.", "Insufficient Configuration", JOptionPane.WARNING_MESSAGE);
            return false;
        } // if all the above condition are met then return true indication the
        // record config is set for recording
        else {
            return true;
        }
    }

    //  }
}
