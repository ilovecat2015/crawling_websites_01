/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import core.recorder.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Jonathan
 */
public class FileUploadDemo {
     public static void main(String[] args) {
 
     
     
     /*
     
            FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;

        try {

            ftpClient.connect("202.69.73.116");
            ftpClient.login("user", "0000");

            ftpClient.enterLocalPassiveMode();
            
//System.out.println(ftpClient.storeUniqueFileStream());

        //System.out.println(ftpClient.listDirectories());
        
            
            boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory("\\Live");


            if (changeWorkingDirectory)//this is false here
            {

                System.out.println(changeWorkingDirectory);
                String filename = "test231343.mp4";
              //String filename = "test2.mp4";

              //File firstLocalFile = newfilename;
              fis = new FileInputStream("C:\\Users\\Jonathan\\Desktop\\livestreaming\\20180213_151400_test.mp4");
              
              boolean storeFile = ftpClient.storeFile(filename, fis);
              if(storeFile)
                 System.out.println("file stored");
              else
                 System.out.println("file can not be stored");

              ftpClient.logout();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        */
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
            File firstLocalFile = new File("C:\\Users\\Jonathan\\Desktop\\livestreaming\\20180213_151400_test.mp4");
            String firstRemoteFile = "testasf.mp4";
            InputStream inputStream = new FileInputStream(firstLocalFile);
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
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
