import business.StudentManager;
import enity.Student;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
//        List<Student> students = Arrays.asList(
//                new Student(0, "Nguyễn Văn A", 20),
//                new Student(0, "Nguyễn Văn B", 22),
//                new Student(0, "Nguyễn Văn C", 19)
//        );
//        studentManager.addStudent(students);

//        Student student = new Student(1,"Nguyễn Văn A",21);
//        studentManager.updateStudent(student);

        System.out.println("Nhập tuổi để xoá những học sinh có độ tuổi lớn hơn: ");
        int numberAge = Integer.parseInt(sc.nextLine());
        studentManager.deleteStudent(numberAge);
    }
}
