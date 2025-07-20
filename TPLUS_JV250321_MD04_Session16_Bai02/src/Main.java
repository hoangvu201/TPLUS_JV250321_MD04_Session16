import business.StudentManager;
import enity.Student;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
//        List<Student> students = Arrays.asList(
//                new Student(0, "Nguyễn Văn A", 20),
//                new Student(0, "Nguyễn Văn B", 22),
//                new Student(0, "Nguyễn Văn C", 19)
//        );
//        studentManager.addStudent(students);
        Student student = new Student(1,"Nguyễn Văn A",21);
        studentManager.updateStudent(student);
    }
}
