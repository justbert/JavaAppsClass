/*
File name: Server.java
Author: Justin Bertrand, 040 592 786
Course: CST8221 – JAP, Lab Section: 301
Assignment: Assignment 2, Part 2
Date: December 11th, 2015
Professor: Svillen Ranev
Purpose: Run the server
Class list: Server.java, ServerSocketRunnable.java
*/

/*
BEGING IMPORTS
*/
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Runs the server connections
 * @author Justin Bertrand
 * @version 1.0
 * @see 
 * @since 1.8_60
 */
public class Server {
    
    /**
     * Main: Creates the threads which allows clients to connect to the server.
     * @see java.io.IOException
     * @see java.net.ServerSocket
     * @see java.net.Socket
     * @see java.lang.Thread
     * @see java.lang.Runnable
     * @param args No arguments.
     */
    public static void main(String[] args) {
        
        try {
            System.out.println("Using default port: 65535");
            ServerSocket socket = new ServerSocket(65535);
            while(true) {
                Socket incoming = socket.accept();
                System.out.println("Connecting to a client "+ incoming);
                Runnable r = new ServerSocketRunnable(incoming);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (IOException e) {
            
        }
    }
}
