package dao;

import entity.BookType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static connection.Connection.closeConnection;
import static connection.Connection.openConnection;

public class BookTypeDAOImp implements BookTypeDAO {
    @Override
    public List<BookType> findAll() {
        Connection conn = null;
        CallableStatement callstm = null;
        List<BookType> bookTypeList = null;
        try {
            conn = openConnection();
            callstm = conn.prepareCall("{call display_all_book_type()}");
            callstm.execute();
            ResultSet rs = callstm.getResultSet();
            bookTypeList = new ArrayList<>();
            while (rs.next()) {
                BookType bookType = new BookType();
                bookType.setTypeId(rs.getInt("type_id"));
                bookType.setTypeName(rs.getString("type_name"));
                bookType.setTypeDescription(rs.getString("type_description"));
                bookType.setTypeStatus(rs.getBoolean("type_status"));
                bookTypeList.add(bookType);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection(conn, callstm);
        }
        return bookTypeList;
    }

    @Override
    public void addBookType(BookType bookType) {
        Connection conn = null;
        CallableStatement callstm = null;
        try {
            conn = openConnection();
            callstm = conn.prepareCall("{call create_book_type(?,?,?,?)}");
            callstm.setInt(1, bookType.getTypeId());
            callstm.setString(2, bookType.getTypeName());
            callstm.setString(3, bookType.getTypeDescription());
            callstm.setBoolean(4, bookType.isTypeStatus());
            callstm.executeUpdate();
            System.out.println("Thêm loại sách mới thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection(conn, callstm);
        }
    }

    @Override
    public boolean updateBookType(BookType bookType) {
        Connection conn = null;
        CallableStatement callstm = null;
        try {
            conn = openConnection();
            callstm = conn.prepareCall("{call update_book_type_by_id(?,?,?,?)}");
            callstm.setInt(1, bookType.getTypeId());
            callstm.setString(2, bookType.getTypeName());
            callstm.setString(3, bookType.getTypeDescription());
            callstm.setBoolean(4, bookType.isTypeStatus());
            callstm.executeUpdate();
            System.out.println("Cập nhật thông tin loại sách thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection(conn, callstm);
        }
        return false;
    }

    @Override
    public boolean deleteBookType(int id) {
        Connection conn = null;
        CallableStatement callstm = null;
        try {
            conn = openConnection();
            callstm = conn.prepareCall("{call delete_book_type(?)}");
            callstm.setInt(1, id);
            callstm.executeUpdate();
            System.out.println("Xoá loại sách thành công");
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection(conn, callstm);
        }
        return false;
    }
}
