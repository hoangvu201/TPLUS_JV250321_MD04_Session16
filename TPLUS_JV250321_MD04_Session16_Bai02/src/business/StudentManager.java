package business;

import enity.Student;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentManager {
    public void addStudent(List<Student> students) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);

            callSt = conn.prepareCall("{call add_students(?, ?)}");
            for (Student student : students) {
                callSt.setString(1, student.getName());
                callSt.setInt(2, student.getAge());
                callSt.executeUpdate();
            }
            conn.commit();

        } catch (Exception e) {
           try {
            if (conn != null) {
                conn.rollback();
               e.printStackTrace();
            }
           }catch (SQLException ex){
                ex.printStackTrace();
           }
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    public void updateStudent(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);

            callSt = conn.prepareCall("{call update_student(?,?,?)}");
            callSt.setInt(1, student.getId());
            callSt.setString(2, student.getName());
            callSt.setInt(3, student.getAge());
            callSt.executeUpdate();
            System.out.println("Cập nhật sinh viên thành công");
            conn.commit();
        }catch (Exception ex){
            try {
            if (conn != null) {
                conn.rollback();
                ex.printStackTrace();
            }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
}
