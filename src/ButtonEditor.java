import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String status;
    private JTable table;
    private AttendanceManager manager;

    public ButtonEditor(
            JCheckBox checkBox,
            JTable table,
            AttendanceManager manager) {

        super(checkBox);

        this.table = table;
        this.manager = manager;

        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(e -> {

            int row = table.getSelectedRow();

            manager.toggleAttendance(row);

            status = manager.isPresent(row)
                    ? "Present"
                    : "Absent";

            table.setValueAt(status, row, 2);

            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column) {

        status = value.toString();

        button.setText(status);

        if (status.equals("Present")) {
            button.setBackground(Color.BLUE);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(Color.RED);
            button.setForeground(Color.WHITE);
        }

        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return status;
    }
}