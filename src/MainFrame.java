import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    AttendanceManager manager = new AttendanceManager();

    JTextField rollField = new JTextField(10);
    JTextField nameField = new JTextField(10);

    JButton addButton = new JButton("Add Student");

    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> studentList = new JList<>(model);

    JButton presentButton = new JButton("Present");
    JButton absentButton = new JButton("Absent");

    public MainFrame() {

        setTitle("Attendance System");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Roll No"));
        add(rollField);

        add(new JLabel("Name"));
        add(nameField);

        add(addButton);
        add(presentButton);
        add(absentButton);

        add(new JScrollPane(studentList));

        addButton.addActionListener(e -> addStudent());
        presentButton.addActionListener(e -> markPresent());
        absentButton.addActionListener(e -> markAbsent());

        setVisible(true);
    }

    private void addStudent() {

        try {

            String roll = rollField.getText().trim();
            String name = nameField.getText().trim();

            if(roll.isEmpty() || name.isEmpty()) {
                throw new IllegalArgumentException(
                        "All fields are required");
            }

            Student student = new Student(roll, name);

            manager.addStudent(student);

            model.addElement(student.toString());

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
    private void markPresent() {

        int index = studentList.getSelectedIndex();

        if(index >= 0) {
            manager.markAttendance(index, true);

            JOptionPane.showMessageDialog(
                    this,
                    "Marked Present");
        }
    }
    private void markAbsent() {

        int index = studentList.getSelectedIndex();

        if(index >= 0) {
            manager.markAttendance(index, false);

            JOptionPane.showMessageDialog(
                    this,
                    "Marked Absent");
        }
    }
}