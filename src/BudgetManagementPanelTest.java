import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BudgetManagementPanelTest {

    private BudgetManagementPanel budgetPanel;

    @Before
    public void setUp() {
        budgetPanel = new BudgetManagementPanel();
    }

    @Test
    public void testAddValidBudgetItem() {
        budgetPanel.getCategoryField().setText("Utilities");
        budgetPanel.getAmountField().setText("100");
        budgetPanel.getAddButton().doClick();

        // Verify that the table has one row added
        assertEquals(1, budgetPanel.getBudgetTableModel().getRowCount());

        // Verify that the added row has the correct values
        assertEquals("Utilities", budgetPanel.getBudgetTableModel().getValueAt(0, 0));
        assertEquals("100", budgetPanel.getBudgetTableModel().getValueAt(0, 1));
    }

    @Test
    public void testAddBudgetItemWithEmptyCategory() {
        budgetPanel.getCategoryField().setText("");
        budgetPanel.getAmountField().setText("100");
        budgetPanel.getAddButton().doClick();

        // Verify that no row was added due to empty category
        assertEquals(0, budgetPanel.getBudgetTableModel().getRowCount());
    }

    @Test
    public void testAddBudgetItemWithInvalidAmount() {
        budgetPanel.getCategoryField().setText("Groceries");
        budgetPanel.getAmountField().setText("invalid"); // Non-numeric
        budgetPanel.getAddButton().doClick();

        // Verify that no row was added due to invalid amount
        assertEquals(0, budgetPanel.getBudgetTableModel().getRowCount());
    }
}
