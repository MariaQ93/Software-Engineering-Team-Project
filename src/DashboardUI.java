import javax.swing.*;

public class DashboardUI extends JFrame {
    private JTabbedPane tabbedPane;

    public DashboardUI() {
        setTitle("Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeTabs();
    }

    private void initializeTabs() {
        tabbedPane = new JTabbedPane();

        BankDataPanel bankDataPanel = new BankDataPanel();
        tabbedPane.addTab("Bank Data Integration", null, bankDataPanel, "Integrate with banking APIs");

        BudgetManagementPanel budgetManagementPanel = new BudgetManagementPanel();
        tabbedPane.addTab("Budget Management", null, budgetManagementPanel, "Set, view, and manage budgets");

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardUI dashboard = new DashboardUI();
            dashboard.setVisible(true);
        });
    }
}
