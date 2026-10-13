import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public boolean addStudent(Student student) {
        if (!isIdUnique(student.getId())) {
            return false;
        }
        students.add(student);
        return true;
    }

    public boolean isIdUnique(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }

    public boolean deleteStudent(String id) {
        return students.removeIf(student -> student.getId().equalsIgnoreCase(id));
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> searchStudents(int choice, String searchTerm) {
        List<Student> results = new ArrayList<>();
        for (Student student : students) {
            boolean match = false;
            if (choice == 1) {
                match = student.getId().toLowerCase().contains(searchTerm.toLowerCase());
            } else if (choice == 2) {
                match = student.getName().toLowerCase().contains(searchTerm.toLowerCase());
            }
            if (match) {
                results.add(student);
            }
        }
        return results;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}