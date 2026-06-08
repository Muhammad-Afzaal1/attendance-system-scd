import java.util.ArrayList;

public class AttendanceManager {

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Boolean> attendance = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        attendance.add(true); // default Present
    }

    public void toggleAttendance(int index) {
        attendance.set(index, !attendance.get(index));
    }

    public boolean isPresent(int index) {
        return attendance.get(index);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}