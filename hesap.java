import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HesapMakinesi extends JFrame implements ActionListener {
    private JTextField display;
    private double firstOperand, secondOperand, result;
    private String operator;

    public HesapMakinesi() {
        setTitle("Hesap Makinesi");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            firstOperand = 0;
            secondOperand = 0;
            operator = null;
        } else if (command.matches("[+\\-*/]")) {
            firstOperand = Double.parseDouble(display.getText());
            operator = command;
            display.setText("");
        } else if (command.equals("=")) {
            secondOperand = Double.parseDouble(display.getText());

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Hata");
                        return;
                    }
                    break;
            }

            display.setText(Double.toString(result));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HesapMakinesi calculator = new HesapMakinesi();
            calculator.setVisible(true);
        });
    }
}
