/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;
import core.recorder.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
 
/**
 *
 * @author Jonathan
 */
public class FTPCreaeteDirDemo {
     private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        
   
        String server = "202.69.73.116";
        int port = 21;
        String user = "user";
        String pass = "0000";
        FTPClient ftpClient = new FTPClient();
        
            

            ftpClient.connect(server, port);
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return;
            }
            boolean success = ftpClient.login(user, pass);
            showServerReply(ftpClient);
            if (!success) {
                System.out.println("Could not login to the server");
                return;
            }
            // Creates a directory
            String dirToCreate = "/upload123";
            success = ftpClient.makeDirectory(dirToCreate);
            showServerReply(ftpClient);
            if (success) {
                System.out.println("Successfully created directory: " + dirToCreate);
            } else {
                System.out.println("Failed to create directory. See server's reply.");
            }
            
            FileInputStream fis = null;
            ftpClient.enterLocalPassiveMode();
            
            
            boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory("\\upload123");


            if (changeWorkingDirectory)//this is false here
            {

                System.out.println(changeWorkingDirectory);
                String filename = "test3.mp4";
              //String filename = "test2.mp4";

              //File firstLocalFile = newfilename;
              fis = new FileInputStream("C:\\Users\\Jonathan\\Desktop\\livestreaming\\20180213_151400_test.mp4");
              
              boolean storeFile = ftpClient.storeFile(filename, fis);
              if(storeFile)
                 System.out.println("file stored");
              else
                 System.out.println("file can not be stored");

              
            // logs out
            ftpClient.logout();
            ftpClient.disconnect();
        } 
    }
}

