import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Vector;

public class BankDataPanel extends JPanel {
    private JTextField bankNameField;
    private JButton addBankButton;
    private JTable table;
    private BankDataTableModel tableModel; // Reference to your custom table model
    private Vector<String> columnNames = new Vector<>(); // This will hold the column names
    private Vector<Vector<String>> data = new Vector<>(); // This will hold the data

    public BankDataPanel() {
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        JPanel inputPanel = new JPanel(new FlowLayout());
        bankNameField = new JTextField(20);
        inputPanel.add(bankNameField);

        addBankButton = new JButton("Add Bank");
        addBankButton.addActionListener(e -> loadBankData());
        inputPanel.add(addBankButton);

        add(inputPanel, BorderLayout.NORTH);

        // Initialize the table with the custom model
        tableModel = new BankDataTableModel(columnNames, data);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadBankData() {
        String bankName = bankNameField.getText().trim();
        // Ensure to include the file extension ".txt"
        String fileName = bankName + ".txt";
        Path filePath = Paths.get(System.getProperty("user.dir"), "banksInfo", fileName);

        readBankDataFromFile(filePath.toString());
        // Inform the table model that the data has changed
        tableModel.fireTableStructureChanged(); // Since column names might change, use fireTableStructureChanged
    }

    private void readBankDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // This reads the header
            if (line != null) {
                String[] headers = line.split(",");
                columnNames.clear();
                for (String header : headers) {
                    columnNames.add(header.trim());
                }
            }

            data.clear(); // Clear the old data
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Vector<String> rowData = new Vector<>();
                for (String token : tokens) {
                    rowData.add(token.trim());
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading the bank data file: " + filePath, "Read Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public JTextField getBankNameField() {
        return bankNameField;
    }

    public JButton getAddBankButton() {
        return addBankButton;
    }

    public JTable getTable() {
        return table;
    }
}
