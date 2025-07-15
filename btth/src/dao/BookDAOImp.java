package dao;

import entity.Book;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static connection.Connection.closeConnection;
import static connection.Connection.openConnection;
public class BookDAOImp implements BookDAO {
    @Override
    public List<Book> getAllBooks() {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Book> books = null;
        try {
            conn = openConnection();
            stmt = conn.prepareCall("{call display_all_book_by_book_price_ASC()}");
            stmt.execute();
            ResultSet rs = stmt.executeQuery();
            books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookTitle(rs.getString("book_title"));
                book.setBookPages(rs.getInt("book_pages"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookPrice(rs.getFloat("book_price"));
                book.setTypeId(rs.getInt("type_id"));
                book.setBookStatus(rs.getBoolean("book_status"));
                books.add(book);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection(conn,stmt);
        }
        return books;
    }

    @Override
    public void addBook(Book book) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = openConnection();
            stmt = conn.prepareCall("{call create_book(?,?,?,?,?)}");
            stmt.setString(1, book.getBookID());
            stmt.setString(2, book.getBookName());
            stmt.setString(3, book.getBookTitle());
            stmt.setInt(4, book.getBookPages());
            stmt.setString(5, book.getBookAuthor());
            stmt.setInt(6, book.getTypeId());
            stmt.setBoolean(7, book.isBookStatus());
            stmt.executeUpdate();
            System.out.println("Thêm mới sách thành công");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            closeConnection(conn,stmt);
        }
    }

    @Override
    public void addListBooks(Scanner scanner,List<Book> books) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall("{call create_book(?,?,?,?,?,?,?)}");
            for (Book book : books) {
                stmt.setString(1, book.getBookID());
                stmt.setString(2, book.getBookName());
                stmt.setString(3, book.getBookTitle());
                stmt.setInt(4, book.getBookPages());
                stmt.setString(5, book.getBookAuthor());
                stmt.setInt(6, book.getTypeId());
                stmt.setBoolean(7, book.isBookStatus());
                stmt.executeUpdate();
                System.out.println("Thêm mới sách thành công");
            }
            conn.commit();
        } catch (Exception e) {
           if (conn != null) {
               try {
                    conn.rollback();
               }catch (Exception ex){
                    ex.printStackTrace();
               }
           }
        }
        finally {
            closeConnection(conn,stmt);
        }
    }

    @Override
    public void updateBook(Book book) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = openConnection();
            stmt = conn.prepareCall("{call update_book_by_id(?,?,?,?,?)}");
            stmt.setString(1, book.getBookID());
            stmt.setString(2, book.getBookName());
            stmt.setString(3, book.getBookTitle());
            stmt.setInt(4, book.getBookPages());
            stmt.setString(5, book.getBookAuthor());
            stmt.setInt(6, book.getTypeId());
            stmt.setBoolean(7, book.isBookStatus());
            stmt.executeUpdate();
            System.out.println("Cập nhật thông tin sách thành công");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            closeConnection(conn,stmt);
        }
    }

    @Override
    public void deleteBook(String bookId) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = openConnection();
            stmt = conn.prepareCall("{call delete_book(?)}");
            stmt.setString(1, bookId);
            stmt.executeUpdate();
            System.out.println("Xoá sách thành công");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            closeConnection(conn,stmt);
        }
    }

    @Override
    public Book findBookById(String bookId) {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = openConnection();
            stmt = conn.prepareCall("{call find_book_by_book_id(?)}");
            stmt.setString(1, bookId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookTitle(rs.getString("book_title"));
                book.setBookPages(rs.getInt("book_pages"));
                book.setBookAuthor(rs.getString("book_Author"));
                book.setBookPrice(rs.getFloat("book_price"));
                book.setTypeId(rs.getInt("type_id"));
                book.setBookStatus(rs.getBoolean("book_status"));
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, stmt);
        }
        return null;
    }

    @Override
    public List<StatisticAuthor> getBooksByAuthor() {
        Connection conn = null;
        CallableStatement stmt = null;
        List<StatisticAuthor> statisticAuthors = null;
        try {
            conn = openConnection();
            stmt = conn.prepareCall("{call statistic_author()}");
            stmt.execute();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                StatisticAuthor statisticAuthor = new StatisticAuthor();
                statisticAuthor.setAuthorName(rs.getString("book_author"));
                statisticAuthor.setCount(rs.getInt("count(book_id)"));
                statisticAuthors.add(statisticAuthor);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally{
            closeConnection(conn,stmt);
        }
        return statisticAuthors;
    }


}
