/*
File name: ClientView.java
Author: Justin Bertrand, 040 592 786
Course: CST8221 – JAP, Lab Section: 301
Assignment: Assignment 2, Part 2
Date: December 11th, 2015
Professor: Svillen Ranev
Purpose: GUI for the client application
Class list: Client.java, ClientView.java
*/

/*
BEGING IMPORTS
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Creates the view for the ClientGUI
 * @author Justin Bertrand
 * @see java.awt.BorderLayout
 * @see java.awt.Color
 * @see java.awt.Dimension
 * @see java.awt.GridBagLayout
 * @see java.awt.GridBagConstraints
 * @see java.awt.Insets
 * @see java.awt.event.ActionListener
 * @see java.awt.event.ActionEvent
 * @see javax.swing.BorderFactory
 * @see javax.swing.Box
 * @see javax.swing.JButton
 * @see javax.swing.JComboBox
 * @see javax.swing.JLabel
 * @see javax.swing.JPanel
 * @see javax.swing.JScrollPane
 * @see javax.swing.JTextArea
 * @see javax.swing.JTextField
 * @see javax.swing.border.TitledBorder
 * @see 
 * @version 1.0
 * @since 1.8_60
 */
public class ClientView extends JPanel {
    
    /**
     * @
     */
    private static final int BORDER_SIZE = 10;
    
    private JTextField hostField;
    private JTextField requestLine;
    private JButton sendButton;
    private JButton connectButton;
    private JComboBox<String> portComboBox;
    private JTextArea terminalTextArea;
    
    /**
     * 
     */
    public ClientView() {

        /*Create the controller*/
        ClientController controller = new ClientController();
        
        /*Set the layout to a GridBagLayout*/
        this.setLayout(new GridBagLayout());
        /*Add an Empty Border for some spacing*/
        this.setBorder(BorderFactory.createEmptyBorder(5, 1, 5, 1));
        
        /*Create the Connection Panel
        and set the layout to a GridBagLayout*/    
        JPanel connectionPanel = new JPanel();
        connectionPanel.setLayout(new GridBagLayout());
        
        /*Add a titled Border*/
        TitledBorder setConnectionBorder = new TitledBorder(
                BorderFactory.createLineBorder(Color.RED, BORDER_SIZE), 
                "SET CONNECTION");
        connectionPanel.setBorder(setConnectionBorder);
        
        /*Declare the dimensions of the labels Host and Port*/
        Dimension labelDimension = new Dimension(40, 40);
        
        /*Setup Host label*/
        JLabel hostLabel = new JLabel("Host:");
        hostLabel.setPreferredSize(labelDimension);
        hostLabel.setDisplayedMnemonic('H');
        
        /*Setup Port label*/
        JLabel portLabel = new JLabel("Port:");
        portLabel.setPreferredSize(labelDimension);
        portLabel.setDisplayedMnemonic('P');
        
        /*Create the textfield for the server address*/
        hostField = new JTextField("localhost");
        hostField.setCaretPosition(0);
        hostLabel.setLabelFor(hostField);
        hostField.setMargin(new Insets(0, 5, 0, 0));
        
        /*Create the dimensions for the combobox and button*/
        Dimension portDimensions = new Dimension(100, 
                hostField.getPreferredSize().height);
        
        /*Create and setup comboBox*/
        /*Values for predefined ports*/
        String ports[] = {"", "8088", "65000", "65535"};
        
        portComboBox = new JComboBox(ports);
        portComboBox.setBackground(Color.WHITE);
        portComboBox.setPreferredSize(portDimensions);
        portComboBox.setEditable(true);
        portLabel.setLabelFor(portComboBox);
        
        /*Create the connection button*/
        connectButton = new JButton("Connect");
        connectButton.setMnemonic('C');
        connectButton.setBackground(Color.RED);
        connectButton.setPreferredSize(portDimensions);
        connectButton.setFont(portComboBox.getFont());
        connectButton.setActionCommand("Connect");
        connectButton.addActionListener(controller);
        
        /*Add the components to the connectionPanel
            using a GridBag with constraints */
        GridBagConstraints gridConstraints = new GridBagConstraints();
       
        /*add a strut before the labels*/
        gridConstraints.gridy = 0;
        gridConstraints.gridx = 0;
        connectionPanel.add(Box.createHorizontalStrut(3), gridConstraints);
        
        /*Add the host label to the top left corner of the panel*/
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        connectionPanel.add(hostLabel, gridConstraints);
        
        /*Add the port label to the second row and the leftmost position*/
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        connectionPanel.add(portLabel, gridConstraints);
        
        /*Add the combobox next to the port label*/
        gridConstraints.gridx = 2;
        connectionPanel.add(portComboBox, gridConstraints);
        
        /*Add a strut to make some room between the combo box and a button*/
        gridConstraints.gridx = 3;
        connectionPanel.add(Box.createHorizontalStrut(5), gridConstraints);
        
        /*Add the connect button next to the strut*/
        gridConstraints.gridx = 4;
        connectionPanel.add(connectButton, gridConstraints);
        
        /*add the localhost field next to the host label.
            Format it so that it spans the remaining all of the rows
            and fills all the available space.
        */
        gridConstraints.gridy = 0;
        gridConstraints.gridx = GridBagConstraints.RELATIVE;
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridwidth = 3;
        gridConstraints.weightx = 1.0;
        connectionPanel.add(hostField, gridConstraints);
        
        /*Create the Client Panel*/
        JPanel outerClientPanel = new JPanel();
        outerClientPanel.setLayout(new BorderLayout());
        TitledBorder clientBorder = new TitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, BORDER_SIZE), 
                "CLIENT REQUEST");
        outerClientPanel.setBorder(clientBorder);
        
       
        JPanel clientPanel = new JPanel();
//        clientPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        clientPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        clientPanel.setLayout(new GridBagLayout());
        
        outerClientPanel.add(clientPanel, BorderLayout.CENTER);
        
        GridBagConstraints requestConstraints = new GridBagConstraints();
       
        /*Create the TextField for the client requests*/
        requestLine = new JTextField("Type a server request line");
        requestLine.setMaximumSize(new Dimension(455, requestLine.getPreferredSize().height));
        requestLine.setColumns(40);
        requestConstraints.gridx = 0;
        requestConstraints.fill = GridBagConstraints.HORIZONTAL;
        requestConstraints.weightx = 1.0;
        
        /*Create the button which allows to send the requests to the server*/
        sendButton = new JButton("Send");
        sendButton.setMnemonic('S');
        sendButton.setEnabled(false);
        sendButton.setPreferredSize(new Dimension(85 ,
            requestLine.getPreferredSize().height));
        sendButton.addActionListener(controller);
        sendButton.setActionCommand("Send");
        
        /*Create the Bag constraints*/
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = GridBagConstraints.RELATIVE;
        clientPanel.add(requestLine, requestConstraints);
        clientPanel.add(Box.createHorizontalStrut(5), buttonConstraints);
        clientPanel.add(sendButton, buttonConstraints);
        
        /*Create Terminal Panel*/
        JPanel terminalPanel = new JPanel();
        terminalPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE, BORDER_SIZE), 
                "TERMINAL", TitledBorder.CENTER, TitledBorder.CENTER));
        terminalPanel.setLayout(new BorderLayout());
        
        terminalTextArea = new JTextArea();
        terminalTextArea.setEditable(false);
        
        JScrollPane terminalScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        terminalScrollPane.setViewportView(terminalTextArea);
        terminalPanel.add(terminalScrollPane, BorderLayout.CENTER);
        
        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.gridy = 0;
        panelConstraints.gridx = 0;
        panelConstraints.fill = GridBagConstraints.BOTH;
        panelConstraints.anchor = GridBagConstraints.WEST;
        panelConstraints.weightx = 1.0;
        this.add(connectionPanel, panelConstraints);
        
        panelConstraints.gridy = 1;
        this.add(outerClientPanel, panelConstraints);
        
        panelConstraints.gridy = GridBagConstraints.RELATIVE;
        panelConstraints.weighty = 1.0;
        
        this.add(terminalPanel, panelConstraints);
    }
    
    /** 
     @param text A String to be a added to the terminal
     */
    private void updateTerminal(String text) {
        terminalTextArea.setText(terminalTextArea.getText()
                                    + text);
    }
    
    /** Controller class for the Client GUI. Allows for connections and interaction
     * with the server
     * @author Justin Bertrand
     * @see java.net.InetAddress
     * @see java.net.Socket
     * @see java.io.IOException
     * @see java.io.ObjectInputStream
     * @see java.io.ObjectOutputStream
     * @version 1.0
     * @since 1.8_60
     */
    private class ClientController implements ActionListener {
        
        private Socket socket;
        ObjectOutputStream output;
        ObjectInputStream input;
        
        /**
         * 
         * @param ae The ActionEvent used by the buttons.
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            switch(ae.getActionCommand()) {
                case "Connect":
                    int port;
                    
                    /*
                    Parse out the port number from the combobox
                    */
                    try {
                        port = Integer.parseInt(portComboBox.getItemAt(
                                        portComboBox.getSelectedIndex()));
                    } catch (NumberFormatException e) {
                        updateTerminal("CLIENT>ERROR: Unknown port. "
                                + "Please supply a number between 0 to 65536.");
                        break;
                    }
                    
                    /*Assign the hostname from the hostField*/
                    String host = hostField.getText();
                    
                    
                    try {
                        InetAddress address = InetAddress.getByName(host);
                        
                        if(address.isReachable(100)) {
                            updateTerminal("CLIENT>ERROR: Unknown Host.");
                            break;
                        }
                        
                        socket = new Socket(address, port);
                        socket.setSoTimeout(4000);
                        
                        if(socket.isConnected()) {
                            updateTerminal("Connected to " + socket);
                            connectButton.setEnabled(false);
                            connectButton.setBackground(Color.BLUE);
                            sendButton.setEnabled(true);
                            output = new ObjectOutputStream(socket.getOutputStream());
                            input = new ObjectInputStream(socket.getInputStream());
                        } else {
                            socket.close();
                        }
                            
                    } catch (IOException e) {
                       
                    } catch (IllegalArgumentException e) {
                        updateTerminal("CLIENT>ERROR: Port out of range.");
                    }
                    break;
                case "Send":
                    try {
                        String command = requestLine.getText();
                        updateTerminal("\nCLIENT>" + command);
                        output.writeObject(command);
                        
                        String serverResponse = (String) input.readObject();
                        
                        switch(serverResponse) {
                            case "cls":
                                terminalTextArea.setText("");
                                break;
                            default:
                                updateTerminal("\nSERVER>" + serverResponse);
                        }
                        
                        if(serverResponse.equals("Connection closed.")) {
                            connectButton.setEnabled(true);
                            connectButton.setBackground(Color.RED);
                            sendButton.setEnabled(false);
                        }
                        
                    } catch (IOException e) {
                        
                    } catch (ClassNotFoundException e) {
                        
                    } 
            }
        }
        
    }
}
