import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class BankDataTableModel extends AbstractTableModel {
    private Vector<String> columnNames;
    private Vector<Vector<String>> data;

    public BankDataTableModel(Vector<String> columnNames, Vector<Vector<String>> data) {
        this.columnNames = columnNames;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    // Notifies the listeners when data changes
    public void refreshTableData(Vector<String> columnNames, Vector<Vector<String>> data) {
        this.columnNames = columnNames;
        this.data = data;
        fireTableDataChanged();
    }
}
