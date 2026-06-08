import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    private AttendanceManager manager = new AttendanceManager();

    private JTextField rollField = new JTextField(10);
    private JTextField nameField = new JTextField(10);

    private JButton addButton = new JButton("Add Student");

    private DefaultTableModel tableModel;
    private JTable table;

    public MainFrame() {

        setTitle("Attendance System");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("Roll No"));
        topPanel.add(rollField);

        topPanel.add(new JLabel("Name"));
        topPanel.add(nameField);

        topPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);

        String[] columns = {
                "Roll No",
                "Name",
                "Attendance"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        table = new JTable(tableModel);

        table.setRowHeight(35);

        table.getColumn("Attendance")
                .setCellRenderer(new ButtonRenderer());

        table.getColumn("Attendance")
                .setCellEditor(
                        new ButtonEditor(
                                new JCheckBox(),
                                table,
                                manager));

        add(new JScrollPane(table), BorderLayout.CENTER);

        addButton.addActionListener(e -> addStudent());

        setVisible(true);
    }

    private void addStudent() {

        try {

            String roll = rollField.getText().trim();
            String name = nameField.getText().trim();

            if (roll.isEmpty() || name.isEmpty()) {
                throw new IllegalArgumentException(
                        "All fields are required");
            }

            Student student = new Student(roll, name);

            manager.addStudent(student);

            tableModel.addRow(new Object[]{
                    student.getRollNo(),
                    student.getName(),
                    "Present"
            });

            rollField.setText("");
            nameField.setText("");

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unexpected Error",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}