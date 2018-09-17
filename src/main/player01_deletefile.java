/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;

/**
 *
 * @author Jonathan
 */
public class player01_deletefile {
    	 public static void main(String[] args)
	    {	
	    	try{
	    		
	    		File file = new File("C:\\Users\\Jonathan\\Desktop\\locoy_result_03.xls");
	        	
	    		if(file.delete()){
	    			System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
	    	   
	    	}catch(Exception e){
	    		
	    		e.printStackTrace();
	    		
	    	}
	    	
	    }
         
         
             	 public static void main(File fileIn)
	    {	
	    	try{
	    		
	    		File file =fileIn;
	        	
	    		if(file.delete()){
	    			System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
	    	   
	    	}catch(Exception e){
	    		
	    		e.printStackTrace();
	    		
	    	}
	    	
	    }
         
}
