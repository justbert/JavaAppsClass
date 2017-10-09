/*
File name: CalculatorView.java
Author: Justin Bertrand, 040 592 786
Course: CST8221 – JAP, Lab Section: 
Assignment: Assignment 1, Part 1
Date: October 16th, 2015
Professor: Svillen Ranev
Purpose: This class builds the GUI of a Calculator
Class list: CalculatorView, Controller
*/

/*
BEGIN IMPORTS
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * Class for the creation of a pre-defined splashscreen.
 * @author Svillen Ranev, modified by Justin Bertrand
 * @version 2.0
 * @see 
 * @since 1.8_45
 */
public class CalculatorSplashScreen extends JWindow {

    /**
     * Method which creates and displays a pre-configured splashscreen.
     * @param duration The time in milliseconds to display the splashcreen.
     */
    public void showSplashWindow(int duration) {
    //create content pane
    JPanel content = new JPanel(new BorderLayout()); 
    content.setBackground(Color.GRAY);

    // Set the window's bounds, position the window in the center of the screen
    int width =  620+10;
    int height = 320+10;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width-width)/2;
    int y = (screen.height-height)/2; 
    //set the location and the size of the window
    setBounds(x,y,width,height);

    // create the splash screen
    JLabel label = new JLabel(new ImageIcon("Morpheus.jpg"));
    JLabel demo = new JLabel("Justin Bertrand 040 786 592", JLabel.CENTER);
    demo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    content.add(label, BorderLayout.CENTER);
    content.add(demo, BorderLayout.SOUTH);

   // create custom RGB color
    content.setBorder(BorderFactory.createLineBorder(Color.GRAY, 10));

    setContentPane(content);

    setVisible(true);

    // Wait a little while doing nothing, while the application is loading
    try {
        Thread.sleep(duration); }
    catch (Exception e) {e.printStackTrace();}
    //destroy the window and release all resources
    dispose(); 
  }
}
