import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterApp {
    private static Map<String, Double> exchangeRates;

    public static void main(String[] args) {
        // Initialize exchange rates
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.74);
        exchangeRates.put("JPY", 113.31);
        exchangeRates.put("INR", 74.0);
        // Add more exchange rates here

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        JLabel amountLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField(10);
        JComboBox<String> fromCurrencyComboBox = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        JComboBox<String> toCurrencyComboBox = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        JButton convertButton = new JButton("Convert");
        JLabel resultLabel = new JLabel("Result: ");

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(fromCurrencyComboBox);
        panel.add(toCurrencyComboBox);
        panel.add(convertButton);
        panel.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fromCurrency = fromCurrencyComboBox.getSelectedItem().toString();
                String toCurrency = toCurrencyComboBox.getSelectedItem().toString();
                String amountText = amountField.getText();
                double amount = Double.parseDouble(amountText);

                double fromRate = exchangeRates.get(fromCurrency);
                double toRate = exchangeRates.get(toCurrency);

                double result = amount * (toRate / fromRate);
                DecimalFormat df = new DecimalFormat("#.##");
                resultLabel.setText("Result: " + df.format(result) + " " + toCurrency);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
