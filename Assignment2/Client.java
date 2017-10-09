/*
File name: Client.java
Author: Justin Bertrand, 040 592 786
Course: CST8221 – JAP, Lab Section: 301
Assignment: Assignment 2, Part 2
Date: December 11th, 2015
Professor: Svillen Ranev
Purpose: Application which allows to connect to a server
Class list: Client.java, ClientView.java
*/

/*
BEGING IMPORTS
*/
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Main class for the Client application
 * @author Justin Bertrand
 * @see javax.swing.JFrame
 * @see java.awt.Dimension
 * @see java.awt.EventQueue
 * @version 1.0
 * @since 1.8_60
 */
public class Client {
 
  public static void main(String[] args) {
        
        //Begin the creation of the GUI
        EventQueue.invokeLater(new Runnable() {
           @Override
            public void run() {
                //Creates the frame to house the calculator GUI
                JFrame frame = new JFrame();
                frame.setTitle("Justin's Client");
                int width = 600;
                int height = 550;
                frame.setSize(width, height);
                frame.setMinimumSize(new Dimension(width, height));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //Fill the frame with the GUI
                frame.setContentPane(new ClientView());
                frame.setVisible(true);
            }
        });
    }
}
