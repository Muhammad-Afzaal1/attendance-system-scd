import java.util.ArrayList;

public class AttendanceManager {

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Boolean> attendance = new ArrayList<>();
    public void addStudent(Student student) {
        students.add(student);
        attendance.add(false);
    }

    public void markAttendance(int index, boolean present) {
        attendance.set(index, present);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Boolean> getAttendance() {
        return attendance;
    }
}