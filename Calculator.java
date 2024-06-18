package calculator;

		import javax.swing.*;
		import java.awt.*;
		import java.awt.event.ActionEvent;
		import java.awt.event.ActionListener;

		public class Calculator extends JFrame implements ActionListener {
		    private JTextField display;
		    private String currentInput = "";
		    private String operator = "";
		    private String previousInput = "";

		    public Calculator() {
		        setTitle("Calculator");
		        setSize(400, 600);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        setLocationRelativeTo(null);
		        
		        // Create display field
		        display = new JTextField("0");
		        display.setHorizontalAlignment(JTextField.RIGHT);
		        display.setEditable(false);
		        display.setFont(new Font("Arial", Font.PLAIN, 24));
		        add(display, BorderLayout.NORTH);

		        // Create buttons
		        String[] buttonLabels = {
		            "7", "8", "9", "/", 
		            "4", "5", "6", "*", 
		            "1", "2", "3", "-", 
		            "0", "00", "=", "+", 
		            "C"
		        };

		        JPanel panel = new JPanel();
		        panel.setLayout(new GridLayout(5, 4, 5, 5));
		        for (String label : buttonLabels) {
		            JButton button = new JButton(label);
		            button.setFont(new Font("Arial", Font.PLAIN, 18));
		            button.addActionListener(this);
		            panel.add(button);
		        }
		        add(panel);

		        setVisible(true);
		    }

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String value = e.getActionCommand();

		        switch (value) {
		            case "=":
		                if (!currentInput.isEmpty() && !previousInput.isEmpty() && !operator.isEmpty()) {
		                    currentInput = evaluateExpression(previousInput, currentInput, operator);
		                    operator = "";
		                    previousInput = "";
		                }
		                break;
		            case "+":
		            case "-":
		            case "*":
		            case "/":
		                if (!currentInput.isEmpty()) {
		                    operator = value;
		                    previousInput = currentInput;
		                    currentInput = "";
		                }
		                break;
		            case "C":
		                currentInput = "";
		                previousInput = "";
		                operator = "";
		                break;
		            default:
		                currentInput += value;
		                break;
		        }

		        updateDisplay(currentInput.isEmpty() ? (previousInput.isEmpty() ? "0" : previousInput) : currentInput);
		    }

		    private void updateDisplay(String value) {
		        display.setText(value);
		    }

		    private String evaluateExpression(String a, String b, String operator) {
		        double num1 = Double.parseDouble(a);
		        double num2 = Double.parseDouble(b);

		        switch (operator) {
		            case "+": return String.valueOf(num1 + num2);
		            case "-": return String.valueOf(num1 - num2);
		            case "*": return String.valueOf(num1 * num2);
		            case "/": return num2 != 0 ? String.valueOf(num1 / num2) : "Error";
		            default: return "Error";
		        }
		    }

		    public static void main(String[] args) {
		        SwingUtilities.invokeLater(Calculator::new);
		    }
		
	}


