package connection;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static final String DIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/BookManagement";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public static java.sql.Connection openConnection() {
        java.sql.Connection conn = null;
        try {
            //1. Set Driver cho DriverManager
            Class.forName(DIVER);
            //2. Khởi tạo đối tượng Connection từ DriverManager
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // đóng kết nối
    public static void closeConnection(java.sql.Connection conn, CallableStatement callSt) {
        if (conn != null) {
            // nếu có kết nối thì đóng và nếu việc đóng kết nối lỗi thì ném ra exception
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // đóng tài nguyên dữ liệu không sử dụng nữa
        if (callSt != null) {
            try {
                callSt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
