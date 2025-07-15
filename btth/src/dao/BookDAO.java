package dao;

import entity.Book;

import java.util.List;
import java.util.Scanner;

public interface BookDAO {
    List<Book> getAllBooks();
    void addBook(Book book);
    void addListBooks(Scanner scanner,List<Book> books);
    void updateBook(Book book);
    void deleteBook(String bookId);
    Book findBookById(String bookId);
    List<dao.StatisticAuthor> getBooksByAuthor();
}
