import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankDataPanelTest {

    private BankDataPanel bankDataPanel;

    @Before
    public void setUp() {
        bankDataPanel = new BankDataPanel();
    }

    @Test
    public void testBankDataLoading_ValidBankName() {
        // Set the text of the bankNameField to a known bank name with data
        bankDataPanel.getBankNameField().setText("Chase");
        // Simulate clicking the add bank button
        bankDataPanel.getAddBankButton().doClick();

        // Verify that the table has been populated with data
        assertTrue(bankDataPanel.getTable().getModel().getRowCount() > 0);
    }

    @Test
    public void testBankDataLoading_InvalidBankName() {
        // Set the text of the bankNameField to a non-existent bank name
        bankDataPanel.getBankNameField().setText("NonExistentBank");
        // Simulate clicking the add bank button
        bankDataPanel.getAddBankButton().doClick();

        // Verify that the table has not been populated with data
        assertEquals(0, bankDataPanel.getTable().getModel().getRowCount());
    }

    @Test
    public void testBankDataLoading_EmptyBankName() {
        // Clear the text of the bankNameField
        bankDataPanel.getBankNameField().setText("");
        // Simulate clicking the add bank button
        bankDataPanel.getAddBankButton().doClick();

        // Verify that the table has not been populated with data
        assertEquals(0, bankDataPanel.getTable().getModel().getRowCount());
    }
}
