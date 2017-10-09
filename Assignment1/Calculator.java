/*
File name: Calculator.java
Author: Justin Bertrand, 040 592 786
Course: CST8221 – JAP, Lab Section: 
Assignment: Assignment 1, Part 1
Date: October 16th, 2015
Professor: Svillen Ranev
Purpose: Creates a basic calculator.
*/

/*
BEGIN IMPORTS
*/
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Creates a calculator which allows for basic arithmetic.
 * @author Justin Bertrand
 * @version 1.0
 * @see 
 * @since 1.8_60
 */
public class Calculator {
    /**
     * Main method which creates the calculator.
     * @param args No arguments are used in the main function.
     */
    public static void main(String[] args) {
        
        //Create the splashscreen object and show the window for 5 seconds.
        CalculatorSplashScreen splashScreen = new CalculatorSplashScreen();
        splashScreen.showSplashWindow(5000);
        
        //Begin the creation of the GUI
        EventQueue.invokeLater(new Runnable() {
           @Override
            public void run() {
                //Creates the frame to house the calculator GUI
                JFrame frame = new JFrame();
                frame.setTitle("Calculator");
                int width = 330;
                int height = 400;
                frame.setMinimumSize(new Dimension(width, height));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //Fill the frame with the GUI
                frame.setContentPane(new CalculatorView());
                frame.setVisible(true);
            }
        });
    }
}
