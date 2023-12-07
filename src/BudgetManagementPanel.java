import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BudgetManagementPanel extends JPanel {
    private JTable budgetTable;
    private DefaultTableModel budgetTableModel;
    private JTextField categoryField;
    private JTextField amountField;
    private JButton addButton;

    public BudgetManagementPanel() {
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        // Table Model for budget items
        String[] columnNames = {"Category", "Amount"};
        budgetTableModel = new DefaultTableModel(columnNames, 0);
        budgetTable = new JTable(budgetTableModel);

        // Scroll Pane for Table
        JScrollPane scrollPane = new JScrollPane(budgetTable);
        add(scrollPane, BorderLayout.CENTER);

        // Input panel for new budget items
        JPanel inputPanel = new JPanel();
        categoryField = new JTextField(15);
        amountField = new JTextField(10);
        addButton = new JButton("Add");

        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Add button action
        addButton.addActionListener(e -> addBudgetItem());
    }

    private void addBudgetItem() {
        String category = categoryField.getText().trim();
        String amount = amountField.getText().trim();
        
        // Input validation
        if (category.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both category and amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double.parseDouble(amount); // This is a simple validation to check if the amount is a valid number.
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add item to the table
        budgetTableModel.addRow(new Object[]{category, amount});

        // Clear input fields
        categoryField.setText("");
        amountField.setText("");
    }

    public JTable getBudgetTable() {
        return budgetTable;
    }

    public DefaultTableModel getBudgetTableModel() {
        return budgetTableModel;
    }

    public JTextField getCategoryField() {
        return categoryField;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
