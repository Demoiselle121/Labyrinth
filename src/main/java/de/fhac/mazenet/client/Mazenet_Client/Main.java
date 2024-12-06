package de.fhac.mazenet.client.Mazenet_Client;

import java.io.IOException;
import java.net.Socket;

import javax.xml.bind.UnmarshalException;


public class Main {
	static String ip;
	static String username;
	static String name ;
	static int port;
	public static void main(String[] args) throws IOException, UnmarshalException {
			
		try                                                             
        {              
       	 		ip = args[0];
       	 		
        }                                                               
        catch(Exception e)                                              
        {                                                               
        }  
		   username = "HIBA";
		   port = 5123;
			
			Socket s = new Socket(ip,port);
			
			Client c;
			try 
			{
				c = new Client(s);
				c.login(username);
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
        
	}
}
