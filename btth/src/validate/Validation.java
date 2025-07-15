package validate;

import entity.Book;
import entity.BookType;

import java.util.List;

public class Validation {
    public static boolean isEmpty(String value){
        if(value!=null && !value.trim().isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean isBookTypeNameExists(String bookTypeName, List<BookType> bookTypeList){
        return bookTypeList.stream()
                .anyMatch(bookType -> bookType.getTypeName().equals(bookTypeName.trim()));
    }

    public static boolean isBookIdExists(String bookId, List<Book> bookList) {
        return bookList.stream()
                .anyMatch(book -> book.getBookID().equals(bookId.trim()));
    }

}
