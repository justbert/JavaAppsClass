/*
File name: ServerSocketRunnable.java
Author: Justin Bertrand, 040 592 786
Course: CST8221 – JAP, Lab Section: 301
Assignment: Assignment 2, Part 2
Date: December 11th, 2015
Professor: Svillen Ranev
Purpose: Handle the connections of the server
Class list: Server.java, ServerSocketRunnable.java
*/

/*
BEGING IMPORTS
*/
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the connections of the server
 * @author Justin Bertrand
 * @version 1.0
 * @see java.net.InetAddress
 * @see java.net.Socket
 * @see java.io.IOException
 * @see java.io.ObjectInputStream
 * @see java.io.ObjectOutputStream
 * @see java.util.Calendar
 * @see java.text.DateFormat
 * @see SimpleDateFormat
 * @see java.util.regex.Matcher
 * @see java.util.regex.Pattern
 * @since 1.8_60
 */
public class ServerSocketRunnable implements Runnable{
    
    private Socket socket;
    
    public ServerSocketRunnable(Socket s) {
        socket = s;
    }
    
    @Override
    public void run() {
        /*Local Variable*/
        Pattern pattern = Pattern.compile("(-?end-?|-?echo-?|-?time-?|-?date-?|-?help-?|-?cls-?|.+)(.*)");
        Matcher matcher;
        Calendar cal = Calendar.getInstance();
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ssaa");
        DateFormat dateFormat = new SimpleDateFormat("dd MMMMMMMMMMMMM yyyy");
        String clientString = "";
        String command = "";
        String outputString = "ERROR:Unrecognized command.";
        ObjectInputStream input;
        ObjectOutputStream output;
        boolean endconnection = false;
        
        try {
            input = new ObjectInputStream(socket.getInputStream());
            
            output = new ObjectOutputStream(socket.getOutputStream());

            while(true) {
                /*Try block with resources*/
                try {
                    clientString = (String)input.readObject();
                }  catch (IOException e) {
                    System.out.println("Error: Input error.");
                    break;
                }  catch (ClassNotFoundException e) {
                    System.out.println("Error: Unable to locate class. Please talk to your developer.");
                }

                matcher = pattern.matcher(clientString);


                if(matcher.find()) {
                    command = matcher.group(1);
                } else {
                    command = clientString;
                }

                System.out.println(command);

                    switch(command) {
                        case "echo":
                            outputString = "ECHO:";
                            break;
                        case "-echo-":
                            outputString = "ECHO: " + matcher.group(2);
                            break;
                        case "time":
                        case "-time-":
                            outputString = "TIME: " + timeFormat.format(cal.getTime());
                            break;
                        case "date":
                        case "-date-":
                            outputString = "DATE: " + dateFormat.format(cal.getTime());
                            break;
                        case "help":
                        case "-help-":
                            outputString = "Available Services:\nend\necho\ntime\ndate\nhelp\ncls\n";
                            break;
                        case "cls":
                        case "-cls-":
                            outputString = "cls";
                            break;
                        case "end":
                        case "-end-":
                            outputString = "Connection closed.";
                            endconnection = true;
                            break;
                        default:
                            outputString = "ERROR:Unrecognized command.";
                    }

                    try {
                        output.writeObject(outputString);
                        Thread.sleep(100);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {

                    }

                    if(endconnection)
                        break;
                }

                System.out.println("Server Socket: Closing client connection...");
                try {
                    input.close();
                    output.close();
                    socket.close(); 
                    
                } catch (IOException e) {

                }
                
        } catch (IOException e) {
        
        } catch (Exception e) {
            
        }
        
    }   
}
