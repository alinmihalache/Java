package def;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// A Swing application extends from javax.swing.JFrame
public class SwingCalculator extends JFrame {
    private JTextField tfDisplay;
    private int result = 0;          // the result so far
    private String numberInStr = ""; // the number entered as String
    private char previousOpr = ' ';  // the previous operator
    private char currentOpr = ' ';   // the current operator

    public SwingCalculator() {
        setTitle("Swing Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 420);
        setLayout(new BorderLayout(10, 10));

        // ===== Display (NORTH) =====
        tfDisplay = new JTextField("0");
        tfDisplay.setHorizontalAlignment(JTextField.RIGHT);
        tfDisplay.setFont(new Font("Consolas", Font.BOLD, 26)); // afișaj mai mare
        tfDisplay.setEditable(false);
        tfDisplay.setBackground(Color.WHITE);
        tfDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(tfDisplay, BorderLayout.NORTH);

        // ===== Buttons (CENTER) =====
        JPanel panelButtons = new JPanel(new GridLayout(5, 4, 5, 5)); // 5x4 cu rând pt Back
        add(panelButtons, BorderLayout.CENTER);

        String[] btnLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "C", "0", "=", "/",
            "Back", "%", "",  ""   // două locuri goale pt grilă fixă
        };

        NumberBtnListener numberListener = new NumberBtnListener();
        OprBtnListener oprListener = new OprBtnListener();

        for (String label : btnLabels) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setFocusPainted(false);
            panelButtons.add(btn);

            if (label.matches("\\d")) {
                btn.addActionListener(numberListener);
            } else if (label.equals("C")) {
                btn.addActionListener(new ActionListener() { // Clear
                    @Override public void actionPerformed(ActionEvent e) {
                        result = 0;
                        numberInStr = "";
                        previousOpr = ' ';
                        currentOpr = ' ';
                        tfDisplay.setText("0");
                    }
                });
            } else if (label.equals("Back")) {
                btn.addActionListener(new ActionListener() { // Backspace
                    @Override public void actionPerformed(ActionEvent e) {
                        if (!numberInStr.isEmpty()) {
                            numberInStr = numberInStr.substring(0, numberInStr.length() - 1);
                            tfDisplay.setText(numberInStr.isEmpty() ? "0" : numberInStr);
                        }
                    }
                });
            } else if (!label.isEmpty()) {
                btn.addActionListener(oprListener); // + - * / % =
            } else {
                btn.setEnabled(false); // celulele goale
            }
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Listener pentru cifre
    class NumberBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            numberInStr += evt.getActionCommand();
            tfDisplay.setText(numberInStr);
        }
    }

    // Listener pentru operatori
    class OprBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            previousOpr = currentOpr;               // salvez operatorul anterior
            currentOpr = evt.getActionCommand().charAt(0); // operatorul curent

            if (!numberInStr.isEmpty()) {
                int num = Integer.parseInt(numberInStr);
                numberInStr = "";

                switch (previousOpr) {
                    case ' ': result = num; break;       // primul număr
                    case '+': result += num; break;
                    case '-': result -= num; break;
                    case '*': result *= num; break;
                    case '/':
                        if (num != 0) result /= num;
                        else tfDisplay.setText("Error: /0");
                        break;
                    case '%': result %= num; break;
                    default:  break;
                }
            }

            if (currentOpr == '=') {
                tfDisplay.setText(String.valueOf(result));
                currentOpr = ' ';
            } else {
                tfDisplay.setText(String.valueOf(result));
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingCalculator());
    }
}
