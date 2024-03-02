//This is a Simple Scientific Calculator interface
//We began by importing the necessary Java Packages to allow usage of pre-defined classes and methods.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.math.BigDecimal;

//The class ScientificCalculator is a subclass of the superclass JFrame,  demonstrating encapsulation
public class ScientificCalculator extends JFrame implements ActionListener {
     
    // Initialize all necessary variables, integers, objects, e.t.c.
    private final JTextField textfield; // Where the user's selected input is shown
    private final JPanel buttonPanel; // Where the clickable buttons are displayed

    //global variable; visible throughout the program
    private int bracketLevel = 0;

    //Initial boolean value of the Shift button
    private boolean shiftEnabled = false;

    //Creating the array within which the different buttons are stored
    JButton[] functionButtons = new JButton[50];

    //Defining the elements(buttons) of the array using gridlayout
    String functionTexts[] = {
        "(", ")", "mc", "m+", "m-", "mr", "Clr", "(+/-)", "%", "÷",
        "2ⁿᵈ", "x²", "x³", "xʸ", "eˣ", "10ˣ", "7", "8", "9", "x",
        "x⁻¹","²√x", "³√x", "x√", "ln","log₁₀", "4", "5", "6", "-",
         "!", "sin","cos","tan","e","EE", "1", "2", "3", "+",
         "Rad","sinh","cosh","tanh","π","Rand","."," ", "0", "="
    };
    
    //Declaring or initializing the necessary integers, variables and strings that will be used within the program
    double num1 = 0, num2 = 0, result = 0;
    char operator;
    
    // Create font parameter to be used later
    Font myFont = new Font("mono-spaced", Font.BOLD, 20);
     
    public ScientificCalculator() {
        super("Group 11 Calculator");

    //This sets the size of the window frame and implements a gridlayout manager
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 410);
        getContentPane().setBackground(new Color(28, 28, 28));
        setLayout(new GridBagLayout());
        
        //Setting details for the textfield
        textfield = new JTextField();
        textfield.setBorder(BorderFactory.createLineBorder(new Color(28, 28, 28)));
        textfield.setFont(myFont);
        textfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textfield.setBackground(new Color(28, 28, 28));
        textfield.setForeground(Color.WHITE);
        textfield.setEditable(false);

        // Create the calculator's button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 10, 0, 0)); // Grid layout with spacing
        buttonPanel.setBackground(new Color(28, 28, 28));

        //Code for the layout details of the buttons in the array
        //Assign button object to the Jbutton package class
        for (int i = 0; i < functionTexts.length; i++) {
            JButton button = new JButton(functionTexts[i]);
            button.addActionListener(this);
            button.setFont(myFont);
            button.setFocusable(false);

            // Dynamically set the button colors and setting conditions for specific buttons
            Color color = new Color(80, 80, 80);
            if (i%10 ==  9) {
                color = new Color(255, 150, 9);  //if the columnposition(arrayvalue%10) gives rem9 then Orange
            } else if ((6 <= i%10 && 8 >= i%10 && 1 <= i/10 && i/10 <= 3) || i == 48) {
                color = new Color(212, 212, 210);  //if the rowposition(arrayvalue/10) is 1,2 or 3 then grey
            }

            //Setting a uniform button display for the button class
            button.setBackground(color);
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(new Color(28, 28, 28), 1));
           
            //We asign the functionButtons to the button object created,
            functionButtons[i] = button;
            buttonPanel.add(button);
        }

        // Set the layout for components on the window
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;//Horizontal size across the grid
        gbc.weighty = 0.5;//Vertical size across the grid

        //The position of the textfield in the gridbaglayout(x,y)
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textfield, gbc);

        //The position of the buttonpanel in the gridbagLayout
        gbc.gridy = 1;  // Go to the row below
        gbc.weighty = 1;  // Set the buttons to use all the available height.
        add(buttonPanel, gbc);
        setVisible(true);//Makes the window show/output
    }

   //Main function/Entry
    public static void main(String[] args) {
        new ScientificCalculator();
    }

    // Give each button functionality by overriding
    @Override
    public void actionPerformed(ActionEvent e) {
     BigDecimal memory = BigDecimal.ZERO;//Used in the memory(m) functionalities 
        //variable to return the text on button that was clicked
        String input = ((JButton)e.getSource()).getText();
        for (int i = 0; i < 10; i++) {
            if (input.equals(Integer.toString(i))) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (input.equals(".")) {
            textfield.setText(textfield.getText().concat("."));
        }
        if (input.equals("+")) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (input.equals("-")) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (input.equals("x")) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if (input.equals("÷")) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }
        if (input.equals("%")) {
            num1 = Double.parseDouble(textfield.getText());
            textfield.setText("");
            operator = '%';
        }
        if (input.equals("x√")) {
            num1 = Double.parseDouble(textfield.getText());
            textfield.setText("");
            operator = '√';
        }
        if (input.equals("^")) {
            num1 = Double.parseDouble(textfield.getText());
            textfield.setText("");
            operator = '^';
        }

        if (input.equals("x²")) {
            num1 = Double.parseDouble(textfield.getText());
            result = num1 * num1;
            textfield.setText(String.valueOf(result));

        }

        if (input.equals("x³")) {
            double num1 = Double.parseDouble(textfield.getText());
            result = num1 * num1 * num1;
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("=")) {
            num2 = Double.parseDouble(textfield.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                case '√':
                    result = Math.pow(num1, 1 / num2);
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
                case '%':
                    result = num1 % num2;
                    break;
                default:
                    result = 0;
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1 = result;
        }
        if (input.equals("Clr")) {
            textfield.setText("");
        }
       
        if (input.equals("(+/-)")) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }
        if (input.equals("sin")) {
            double rad = Math.toRadians(Double.parseDouble(textfield.getText()));
            result = Math.sin(rad);
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("cos")) {
            double rad = Math.toRadians(Double.parseDouble(textfield.getText()));
            result = Math.cos(rad);
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("tan")) {
            double rad = Math.toRadians(Double.parseDouble(textfield.getText()));
            result = Math.tan(rad);
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("log₁₀")) {
            result = Math.log10(Double.parseDouble(textfield.getText()));
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("ln")) {
            result = Math.log(Double.parseDouble(textfield.getText()));
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("²√x")) {
            result = Math.sqrt(Double.parseDouble(textfield.getText()));
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("³√x")) {
            result = Math.cbrt(Double.parseDouble(textfield.getText()));
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("!")) {
            long factorial = 1;
            int num = Integer.parseInt(textfield.getText());
            for (int i = 1; i <= num; i++) {
                factorial *= i;
            }
            result = factorial;
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("x⁻¹")) {
            result = 1 / Double.parseDouble(textfield.getText());
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("e")) {
            result = Math.E;
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("π")) {
            result = Math.PI;
            textfield.setText(String.valueOf(result));
        }
        if (input.equals("sinh")) {
            double num = Double.parseDouble(textfield.getText());
            result = Math.sinh(num);
            textfield.setText(String.valueOf(result));
        }
        if (input.equals("cosh")) {
            double num = Double.parseDouble(textfield.getText());
            result = Math.cosh(num);
            textfield.setText(String.valueOf(result));
        }
        if (input.equals("tanh")) {
            double num = Double.parseDouble(textfield.getText());
            result = Math.tanh(num);
            textfield.setText(String.valueOf(result));
        }
        if (input.equals("sin⁻¹")) {
            double num = Double.parseDouble(textfield.getText());
            result = Math.toDegrees(Math.asin(num));
            textfield.setText(String.valueOf(result));
        }
        if (input.equals("cos⁻¹")) {
            double num = Double.parseDouble(textfield.getText());
            result = Math.toDegrees(Math.acos(num));
            textfield.setText(String.valueOf(result));
        }
        if (input.equals("tan⁻¹")) {
            double num = Double.parseDouble(textfield.getText());
            result = Math.toDegrees(Math.atan(num));
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("sinh⁻¹")) {
            double num = Double.parseDouble(textfield.getText());
            result = Math.log(num + Math.sqrt(num*num + 1));
            textfield.setText(String.valueOf(result));
        }
        if (input.equals("cosh⁻¹")) {
            double num = Double.parseDouble(textfield.getText());
            result = Math.log(num + Math.sqrt(num*num - 1));
            textfield.setText(String.valueOf(result));
        }
        if (input.equals("sinh⁻¹")) {
            double num = Double.parseDouble(textfield.getText());
            result = 0.5*Math.log(num + Math.sqrt(num*num + 1));
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("tanh⁻¹")) {
            double num = Double.parseDouble(textfield.getText());
            result = 0.5*Math.log((1 + num)/(1 - num));
            textfield.setText(String.valueOf(result));
        }

        if (input.equals("Rand")) {
            result = Math.random();
            textfield.setText(String.valueOf(result));
        }
        if (input.equals("mc")) {
            memory = BigDecimal.ZERO;
        }
        if (input.equals("mr")) {
            textfield.setText(memory.toPlainString());
        }
        if (input.equals("m+")) {
            String text = textfield.getText();
            BigDecimal num = new BigDecimal(text);
            memory = memory.add(num);
        }
        if (input.equals("m-")) {
            String text = textfield.getText();
            BigDecimal num = new BigDecimal(text);
            memory = memory.subtract(num);
        }

        if (input.equals("(")) {
            bracketLevel++;
            textfield.setText(textfield.getText() + "(");
        }
        if (input.equals(")")) {
            String text = textfield.getText();
            int lastIndex = text.length() - 1;
            if (bracketLevel > 0 && text.charAt(lastIndex) == '(') {
                textfield.setText(text.substring(0, lastIndex) + ")");
                bracketLevel--;
            } else {
                textfield.setText(textfield.getText() + ")");
            }
        }

        //This if-statement handles the change of trig functionality and text when the 2^nd button is pressed.
        if (input.equals("2ⁿᵈ")) {
            shiftEnabled = !shiftEnabled;
            if (shiftEnabled) {
                functionButtons[31].setText("sin⁻¹");
                functionButtons[32].setText("cos⁻¹");
                functionButtons[33].setText("tan⁻¹");
                functionButtons[41].setText("sinh⁻¹");
                functionButtons[42].setText("cosh⁻¹");
                functionButtons[43].setText("tanh⁻¹");
            } else {
                functionButtons[31].setText("sin");
                functionButtons[32].setText("cos");
                functionButtons[33].setText("tan");
                functionButtons[41].setText("sinh");
                functionButtons[42].setText("cosh");
                functionButtons[43].setText("tanh");
            }
        }
    }
}