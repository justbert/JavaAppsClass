/*
File name: CalculatorView.java
Author: Justin Bertrand, 040 592 786
Course: CST8221 – JAP, Lab Section: 
Assignment: Assignment 1, Part 1
Date: October 30th, 2015
Professor: Svillen Ranev
Purpose: This class builds the GUI of a Calculator
Class list: CalculatorView, Controller, CalculatorModel
*/

/*
BEGING IMPORTS
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class which builds the GUI of a Calculator
 * @author Justin Bertrand
 * @version 1.0
 * @see 
 * @since 1.8_60
 */
public class CalculatorView extends JPanel {
   
    /**
     * The color of the radio buttons which deal with precision.
     */
    private static final Color PRECISION_RADIO_COLOR = Color.YELLOW;
    
    /**
     * {@value} The number of columns of chars in the calculator display.
     */
    public static final int DISPLAY_COLUMNS = 16;
    
    /**
     * A text field which is used as the display of the calculator.
     */
    private final JTextField display; //the calculator display field reference

    /**
     * A label used for error reporting, and displaying the mode.
     */
    private final JLabel error; //the error display label reference
    
    /**
     * A button that takes in the dot reference.
     */
    private JButton dotButton; //the decimal point(dot) button reference
    
    /**
     * The controller of everything but the numeric keypad
     */
    private final Controller controller;
    
    /**
     * Default constructor which builds the interface of a calculator.
     * @see javax.swing.JLabel
     * @see javax.swing.JCheckBox
     * @see javax.swing.JRadioButton
     * @see java.awt.GridLayout
     * @see java.awt.BorderLayout
     * @see javax.swing.JPanel
     * @see javax.swing.JButton
     * @see javax.swing.ButtonGroup
     * @see javax.swing.JTextField
     * @see java.awt.Color
     * @see javax.swing.BorderFactory
     * 
     */
    public CalculatorView() {
        super();
        this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
        
        //Create the controller
        controller = new Controller();
        
        //Set the borderLayout to this objects JPanel
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
           
        //Creating the number part of the interface
        //which is inside a gridpane
        GridLayout numericLayout = new GridLayout(4, 4);
        numericLayout.setHgap(5);
        numericLayout.setVgap(5);
        //The panel which houses the numeric buttons and operators
        JPanel numericPanel = new JPanel();
        numericPanel.setBorder(BorderFactory.createEmptyBorder(5,1,5,1));
        numericPanel.setLayout(numericLayout);
        //An array of values for creating the numeric and operative buttons
        String[] labels =   {"7", "8", "9", "/", 
                            "4", "5", "6", "*", 
                            "1", "2", "3", "-", 
                            "\u00B1", "0", ".", "+"
        };
        //The array of colours to be used for the buttons
        Color[] colours =   {Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW,
                            Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW,
                            Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW,
                            Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW
        };
        //A loop which creates the buttons in the numeric panel
        for(int index = 0; index < labels.length; ++index) {
            
            //A temp JButton to hold the instance of the created button
            JButton temp = createButton(labels[index], labels[index], colours[index], Color.BLUE, controller);
            
            //Verifies if the current button is the dot button,
            //if so, returns a reference of itself to the dotButton field
            if(temp.getText().equals(".")) {
                dotButton = temp;
            }
            
            numericPanel.add(temp);
        }
        
        this.add(numericPanel, BorderLayout.CENTER);
        
        //Create the CLEAR Button
        JButton clear = createButton("C", "C", Color.BLACK, Color.red, controller);
        this.add(clear, BorderLayout.LINE_START);
        
        //Create the equals button
        JButton equal = createButton("=", "Enter", Color.BLACK, Color.YELLOW, controller);
        this.add(equal, BorderLayout.LINE_END);
        
        //Create the display field
        display = new JTextField("0.0", DISPLAY_COLUMNS);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setPreferredSize(new Dimension(25, 30));
        display.setBackground(Color.WHITE);
        display.setEditable(false);
        
        //Create the Error/Mode Label
        error = new JLabel("F");
        error.setPreferredSize(new Dimension(25, 25));
        error.setBackground(Color.YELLOW);
        error.setHorizontalAlignment(SwingConstants.CENTER);
        error.setOpaque(true);
        
        //Creates the backspace button
        JButton backspace = new JButton("\u2190");
        backspace.setPreferredSize(new Dimension(25,25));
        backspace.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        backspace.setOpaque(false);
        backspace.setContentAreaFilled(false);
        backspace.setMnemonic('B');
        backspace.setToolTipText("Backspace (Alt-B)");
        backspace.setForeground(Color.RED);
        backspace.setActionCommand("backspace");
        backspace.addActionListener(controller);
        
        //Create the displayPanel and add its components
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout());
        displayPanel.add(error);
        displayPanel.add(display);
        displayPanel.add(backspace);
        displayPanel.setBackground(Color.WHITE);
        
        //CREATING THE PANEL THAT HOUSES THE CHECKBOX AND RADIO BUTTONS
        //Create the checkbox
        JCheckBox mode = new JCheckBox("Int", false);
        mode.setBackground(Color.GREEN);
        mode.setActionCommand("mode");
        mode.addActionListener(controller);
        
        //Create the radiobuttons
        //Create the scientific precision radio button
        JRadioButton scientificPrecision = new JRadioButton("Sci");
        scientificPrecision.setBackground(PRECISION_RADIO_COLOR);
        scientificPrecision.setActionCommand("scientificPrecision");
        scientificPrecision.addActionListener(controller);
        
        //Create Single precision radio button
        JRadioButton singlePrecision = new JRadioButton(".0");
        singlePrecision.setBackground(PRECISION_RADIO_COLOR);
        singlePrecision.setMaximumSize(scientificPrecision.getMaximumSize());
        singlePrecision.setActionCommand("singlePrecision");
        singlePrecision.addActionListener(controller);
        
        //Create the double precision radio button
        JRadioButton doublePrecision = new JRadioButton(".00");
        doublePrecision.setSelected(true);
        doublePrecision.setBackground(PRECISION_RADIO_COLOR);
        doublePrecision.setMaximumSize(scientificPrecision.getMaximumSize());
        doublePrecision.setActionCommand("doublePrecision");
        doublePrecision.addActionListener(controller);
        
        //Add the buttons to the button group
        ButtonGroup precisionGroup = new ButtonGroup();
        precisionGroup.add(singlePrecision);
        precisionGroup.add(doublePrecision);
        precisionGroup.add(scientificPrecision);
        
        //Create the precision panel to house the radio buttons
        JPanel precisionPanel = new JPanel();
        precisionPanel.setLayout(new BoxLayout(precisionPanel, BoxLayout.X_AXIS));
        precisionPanel.add(singlePrecision);
        precisionPanel.add(doublePrecision);
        precisionPanel.add(scientificPrecision);
        
        //Create the panel and add them to the 
        JPanel modePrecisionPanel = new JPanel(new FlowLayout());
        modePrecisionPanel.add(mode);
        modePrecisionPanel.add(Box.createHorizontalStrut(11));
        modePrecisionPanel.add(precisionPanel);
        modePrecisionPanel.setBackground(Color.BLACK);
        
        //Create the panel to house the two previous panels
        JPanel displayPrecisionPanel = new JPanel();
        displayPrecisionPanel.setLayout(new BoxLayout(displayPrecisionPanel, BoxLayout.Y_AXIS));
        displayPrecisionPanel.add(displayPanel);
        displayPrecisionPanel.add(modePrecisionPanel);
        
        this.add(displayPrecisionPanel, BorderLayout.PAGE_START);
               
        this.setVisible(true);
    }
    
    /**
     * A factory method for creating JButtons.
     * @param text The label of the button.
     * @param ac The buttons action command.
     * @param fg The color of the text (foreground color).
     * @param bg The color of the background.
     * @param handler The ActionListener of the button.
     * @return An reference to the created object with its members set to the parameters passed.
     * @see javax.swing.JButton
     * @see java.awt.Color
     */
    private JButton createButton (String text, String ac, Color fg, Color bg, ActionListener handler) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(fg);
        
        //Checks to see if ac is null, if not assigns it to action command
        if(ac != null) {
            button.setActionCommand(ac);
        }
        
        button.setFont(button.getFont().deriveFont(20F));
        button.addActionListener(handler);
        return button;
    }
    
    /**
     * Controller which takes care of all the inner workings of the Calculator.
     * @author Justin Bertrand
     * @version 1.0
     * @see 
     * @since 1.8_60
     */
    private class Controller implements ActionListener {
        
        private final CalculatorModel model;
        private boolean operandSet;

        public Controller() {
            this.model = new CalculatorModel();
            operandSet = false;
        }
       
        /**
         * Takes in actionEvent and sets the calculator display to the 
         * action command of the button pressed.
         * @param e An action event to be handled.
         * @see CalculatorModel
         * @see java.awt.event.ActionEvent
         * @see javax.swing.JLabel
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.print(e.getActionCommand());
            
            if(model.getErrorState()) {
                switch(e.getActionCommand()) {
                    case "mode":
                        setMode(e);
                        break;
                    case "C":
                        resetErrorView();
                        model.setOperand1("0");
                        model.setOperand2("0");
                        model.setOperator("+");
                        model.performCalculations();
                        display.setText(model.getResult());
                        break;
                    case "scientificPrecision":
                    case "doublePrecision":
                    case "singlePrecision":
                        model.setPrecision(e.getActionCommand());
                        break;
                }
                
            } else {
                switch(e.getActionCommand()) {
                    case "C":
                        resetErrorView();
                        model.setOperand1("0");
                        model.setOperand2("0");
                        model.setOperator("+");
                        model.performCalculations();
                        display.setText(model.getResult());
                        break;
                    case "Enter":
                        model.setOperand2(display.getText());
                        model.performCalculations();
                        if(model.getErrorState()) {
                            setErrorView();
                        } else {
                            display.setText(model.getResult());
                        }
                        operandSet = false;
                        break;
                    case "mode":
                        setMode(e);
                        break;
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                    case ".":
                        addDigit(e.getActionCommand());
                        break;
                    case "/":
                    case "+":
                    case "-":
                    case "*":
                        model.setOperand1(display.getText());
                        operandSet = true;
                        model.setOperator(e.getActionCommand());
                        break;
                    case "\u00B1"://plus-minus
                        if(display.getText().charAt(0) == '-') {
                            display.setText(display.getText().substring(1));
                        } else {
                            display.setText("-" + display.getText());
                        }
                        break;
                    case "backspace":
                        display.setText(display.getText().substring(0, display.getText().length() - 1));
                        if(display.getText().length() == 0) {
                            if(error.getText().equals("I")) {
                                display.setText("0");
                            } else {
                                display.setText("0.0");
                            }
                        }
                        break;
                    case "scientificPrecision":
                    case "doublePrecision":
                    case "singlePrecision":
                        model.setPrecision(e.getActionCommand());
                        break;
                }
            }

        }
        
        /**
         * Method which sets the view to display that an error has occurred.
         * @see javax.swing.JLabel
         * @see javax.swing.JTextField
         * @see java.awt.Color
         */
        private void setErrorView() {
            
            //Set the error/mode label to the correct specs 
            error.setBackground(Color.RED);
            error.setText("E");
            
            display.setText(CalculatorModel.ERROR_STRING);            
        }
        
        /**
         * Method which resets the views after an error has been detected.
         * @see CalculatorModel
         * @see javax.swing.JLabel
         * @see java.awt.Color
         */
        private void resetErrorView() {
            
            model.setErrorState(false);
            
            error.setBackground(Color.GREEN);
        }
        
        /**
         * A method which changes the error label, and changes some of the 
         * interface depending on which mode is selected.
         * @param e The action event for the Mode CheckBox
         * @see javax.swing.JLabel
         * @see javax.swing.JCheckBox
         * @see javax.swing.JLabel
         * @see java.awt.Color
         * @see javax.swing.JButton
         * @see java.awt.event.ActionEvent
         */
        private void setMode(ActionEvent e) {

            JCheckBox mode = (JCheckBox) e.getSource();

            if(mode.isSelected()) {
                error.setBackground(Color.GREEN);
                error.setText("I");

                model.setMode("integer");

                dotButton.setBackground(Color.GRAY);
                dotButton.setEnabled(false);
                
                if(display.getText().contains(".")) {
                    display.setText(display.getText().substring(display.getText().indexOf('.')));
                }
            } else {
                error.setBackground(Color.YELLOW);
                error.setText("F");

                model.setMode("float");

                dotButton.setBackground(Color.BLUE);
                dotButton.setEnabled(true);
            }
            
        }
        
        /**
         * Method which adds a digit to the display.
         * @param digit A string of the digit to be added.
         * @see java.lang.String
         * @see javax.swing.JTextField
         * @see java.lang.Float
         */
        private void addDigit(String digit) {
            
            if(Float.parseFloat(display.getText()) != 0.0f && !operandSet) {
                display.setText(display.getText() + digit);
            } else {
                display.setText(digit);
            }
            operandSet = false;
        }
    }  
}
