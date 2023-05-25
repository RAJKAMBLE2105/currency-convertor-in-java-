import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame {
    private JComboBox<String> currencyFromComboBox;
    private JComboBox<String> currencyToComboBox;
    private JTextField amountTextField;
    private JLabel resultLabel;

    private static final double[] CONVERSION_RATES = {0.89, 1.11, 0.75, 1.33};
    private static final String[] CURRENCY_NAMES = {"USD", "EUR", "GBP", "CAD"};

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel amountLabel = new JLabel("Amount:");
        amountTextField = new JTextField();
        JLabel currencyFromLabel = new JLabel("From:");
        currencyFromComboBox = new JComboBox<>(CURRENCY_NAMES);
        JLabel currencyToLabel = new JLabel("To:");
        currencyToComboBox = new JComboBox<>(CURRENCY_NAMES);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Converted Amount: N/A");

        convertButton.addActionListener(new ConvertButtonListener());

        mainPanel.add(amountLabel);
        mainPanel.add(amountTextField);
        mainPanel.add(currencyFromLabel);
        mainPanel.add(currencyFromComboBox);
        mainPanel.add(currencyToLabel);
        mainPanel.add(currencyToComboBox);
        mainPanel.add(convertButton);
        mainPanel.add(resultLabel);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class ConvertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                double amount = Double.parseDouble(amountTextField.getText());
                int fromIndex = currencyFromComboBox.getSelectedIndex();
                int toIndex = currencyToComboBox.getSelectedIndex();

                double convertedAmount = amount * CONVERSION_RATES[toIndex] / CONVERSION_RATES[fromIndex];

                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                String result = decimalFormat.format(convertedAmount);

                resultLabel.setText("Converted Amount: " + result);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverter();
        });
    }
}
