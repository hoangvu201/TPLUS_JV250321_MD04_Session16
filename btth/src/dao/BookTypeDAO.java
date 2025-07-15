package dao;

import entity.BookType;

import java.util.List;

public interface BookTypeDAO {
    List<BookType> findAll();
    void addBookType(BookType bookType);
    boolean updateBookType(BookType bookType);
    boolean deleteBookType(int id);
}
