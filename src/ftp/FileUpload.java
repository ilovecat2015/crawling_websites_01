/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


/**
 *
 * @author Jonathan
 */
public class FileUpload {
     public static void main(String[] args) {

            FTPClient ftpClient = new FTPClient();
            FileInputStream fis = null;
        try {
            ftpClient.connect("202.69.73.116");
            ftpClient.login("user", "0000");
            ftpClient.enterLocalPassiveMode();
             ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory("\\Live");
            System.out.println(changeWorkingDirectory);

            if (changeWorkingDirectory)//this is false here
            {
                System.out.println(changeWorkingDirectory);
                String filename = "test1adgasd0000000f212333.mp4";
              //String filename = "test2.mp4";

              //File firstLocalFile = newfilename;
              fis = new FileInputStream("C:\\Users\\Jonathan\\Desktop\\livestreaming\\20180214_153000_News.mp4");
              
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
        
        
     }
     
     
     public static void upload(String firstRemoteFile, String newfilename) 
     {
       
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        try {

            ftpClient.connect("202.69.73.116");
            ftpClient.login("user", "0000");

            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory("\\Live1");

            if (changeWorkingDirectory)//this is false here
            {


                //String filename1 = "\\"+dateFormat.format(date)+"_"+removeext+"."+ext;
         
 System.out.println( ftpClient.printWorkingDirectory());
            

              
 
              File firstLocalFile = new File(newfilename);
              InputStream inputStream = new FileInputStream(firstLocalFile);
                      
              System.out.println(firstRemoteFile);
              System.out.println(newfilename);
              
            //  String filename=firstRemoteFile;
           
              // fis = new FileInputStream("C:\\Users\\Jonathan\\Desktop\\livestreaming\\20180213_151400_test.mp4");
               
              boolean storeFile = ftpClient.storeFile(firstRemoteFile, inputStream);
               //boolean storeFile = ftpClient.storeFile(firstRemoteFile, inputStream);
              if(storeFile)
                 System.out.println("file has been uploaded to FTP successfully");
              else
                 System.out.println("file has not been uploaded to FTP");

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
        
     }
     
     
     
     
     
     
     
     
     
     
}