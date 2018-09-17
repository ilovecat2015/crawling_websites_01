/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Jonathan
 */

import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;

public class NewClass {
    public static void main(String[] args) {
       
        String server = "202.69.73.116";
        int port = 21;
        String user = "user";
        String pass = "0000";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("C:\\Users\\Jonathan\\Desktop\\livestreaming\\test1.mp4");
 
            String firstRemoteFile = "test3.mp4";
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading file");
                        System.out.println(firstRemoteFile);
            System.out.println(inputStream);
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
          
            System.out.println(done);
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }
            inputStream.close();

 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

      