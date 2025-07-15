package management;

import dao.BookDAO;
import dao.BookTypeDAOImp;
import entity.Book;
import entity.BookType;
import validate.Validation;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    private final BookDAO bookDAO;
    private final BookTypeDAOImp bookTypeDAO;

    public BookManagement(BookDAO bookDAO, BookTypeDAOImp bookTypeDAO) {
        this.bookDAO = bookDAO;
        this.bookTypeDAO = bookTypeDAO;
    }

    public void getAllBooks() {
        List<Book> books = bookDAO.getAllBooks();
        if (books.isEmpty()) {
            System.err.println("Dữ liệu rỗng, vui lòng nhập dữ liệu");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void addBook(Scanner scanner, List<BookType> bookTypeList) {
        List<Book> bookList = bookDAO.getAllBooks();
        Book book = inputBook(scanner, bookList, bookTypeList);
        bookDAO.addBook(book);
    }

    public void addListBooks(Scanner scanner, List<BookType> bookTypeList) {
        List<Book> bookList = bookDAO.getAllBooks();
        System.out.println("Nhập số lượng sách muốn thêm: ");
        do {
            try {
                int number = Integer.parseInt(scanner.nextLine());
                if (number > 0) {
                    for (int i = 0; i < number; i++) {
                        System.out.println("Nhập thông tin sách thứ " + (i + 1) + ":");
                        Book book = inputBook(scanner, bookList, bookTypeList);
                        bookDAO.addBook(book);
                        bookList.add(book);
                    }
                    System.out.println("Thêm mới danh sách sách thành công");
                } else {
                    System.err.println("Số lượng phải lớn hơn 0");
                }
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số nguyên");
            }
        } while (true);

    }

    public void updateBook(Scanner scanner, List<BookType> bookTypeList) {
        System.out.println(" Nhập ID sách cần cập nhật thông tin: ");
        do {
            String bookId = scanner.nextLine();
            Book book = bookDAO.findBookById(bookId);
            if (book != null) {
                boolean isNotExits = true;
                do {
                    System.out.println("1.Cập nhật tên sách");
                    System.out.println("2.Cập nhật tiêu đề sách");
                    System.out.println("3.Cập nhật số trang sách");
                    System.out.println("4.Cập nhật tác giả");
                    System.out.println("5.Cập nhật giá tiền");
                    System.out.println("6.Cập nhật mã loại sách");
                    System.out.println("7.Cập nhật trạng thái");
                    System.out.println("8.Thoát");
                    System.out.print(" Lựa chọn của bạn: ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            book.setBookName(inputBookName(scanner, bookDAO.getAllBooks()));
                            break;
                        case 2:
                            book.setBookTitle(inputBookTitle(scanner));
                            break;
                        case 3:
                            book.setBookPages(inputBookPages(scanner));
                            break;
                        case 4:
                            book.setBookAuthor(inputAuthor(scanner));
                            break;
                        case 5:
                            book.setBookPrice(inputBookPrice(scanner));
                            break;
                        case 6:
                            book.setTypeId(inputBookType(scanner,bookTypeList));
                            break;
                        case 7:
                            book.setBookStatus(inputBookStatus(scanner));
                            break;
                        case 8:
                            isNotExits = false;
                            break;
                        default:
                            System.out.println("Vui lòng chọn từ 1 đến 8");

                    }
                } while (isNotExits);
            } else {
                System.err.println("Không tìm thấy sách với mã ID" + bookId);
            }
        } while (true);
    }

    public void  deleteBook(Scanner scanner) {
        System.out.println("Nhập vào id sách muốn xoá: ");
        do {
            String bookId = scanner.nextLine();
            Book book = bookDAO.findBookById(bookId);
            if (book != null) {
                bookDAO.deleteBook(bookId);
            } else {
                System.err.println("Không tìm thấy sách với mã " +bookId);
            }
        }while (true);
    }

    public void findBookById(Scanner scanner) {
        System.out.println("Nhập vào id cần tìm kiếm");
        do {
            String bookId = scanner.nextLine();
            Book book =  bookDAO.findBookById(bookId);
            if (book != null) {
                System.out.println(book);
            } else {
                System.err.println("Không tìm thấy sách với mã " + bookId );
            }
        } while (true);
    }

    public Book inputBook(Scanner scanner, List<Book> bookList, List<BookType> bookTypeList) {
        Book book = new Book();
        book.setBookID(inputBookId(scanner, bookList));
        book.setBookName(inputBookName(scanner, bookList));
        book.setBookTitle(inputBookTitle(scanner));
        book.setBookPages(inputBookPages(scanner));
        book.setBookAuthor(inputAuthor(scanner));
        book.setBookPrice(inputBookPrice(scanner));
        book.setTypeId(inputBookType(scanner, bookTypeList));
        book.setBookStatus(true);
        return book;
    }

    public String inputBookId(Scanner scanner, List<Book> bookList) {
        System.out.println(" Nhập mã sách (gồm 5 ký tự)");
        do {
            try {
                String id = scanner.nextLine();
                if (id.length() != 5) {
                    System.err.println("Mã sách chỉ có tối đa 5 ký tự");
                } else if (Validation.isBookIdExists(id, bookList)) {
                    System.err.println("Mã sách đã tồn tại");
                } else {
                    return id;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }

    public String inputBookName(Scanner scanner, List<Book> bookList) {
        System.out.println("Nhập vào tên sách: ");
        do {
            try {
                String name = scanner.nextLine().trim();
                if (!Validation.isEmpty(name)) {
                    boolean result = bookList.stream()
                            .anyMatch(book -> book.getBookName().equals(name));
                    if (!result) {
                        return name;
                    } else {
                        System.err.println("Tên đã tồn tại, vui lòng nhập tên khác");
                    }
                } else {
                    System.err.println("Tên sách không được để trống");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }

    public String inputBookTitle(Scanner scanner) {
        System.out.println("Nhập vào tiêu đề sách: ");
        do {
            String title = scanner.nextLine().trim();
            if (!Validation.isEmpty(title)) {
                return title;
            } else {
                System.err.println("Tiêu đề không được để trống");
            }
        } while (true);
    }

    public int inputBookPages(Scanner scanner) {
        System.out.println(" Nhập vào số trang: ");
        do {
            try {
                int pages = Integer.parseInt(scanner.nextLine().trim());
                if (pages > 0) {
                    return pages;
                } else {
                    System.err.println("Số trang phải lớn hơn 0");
                }
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số nguyên hợp lệ");
            }
        } while (true);
    }

    public String inputAuthor(Scanner scanner) {
        System.out.println("Nhập tên tác giả: ");
        do {
            String author = scanner.nextLine().trim();
            if (!Validation.isEmpty(author)) {
                return author;
            } else {
                System.err.println("Tên tác giả không được để trống");
            }
        } while (true);
    }

    public float inputBookPrice(Scanner scanner) {
        System.out.println("Nhập giá sách: ");
        do {
            try {
                float price = Float.parseFloat(scanner.nextLine().trim());
                if (price > 0) {
                    return price;
                } else {
                    System.err.println("Giá phải lớn hơn 0");
                }
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số hợp lệ");
            }
        } while (true);
    }

    public int inputBookType(Scanner scanner,List<BookType> bookTypeList) {
        System.out.println("Nhập ID loại sách: ");
        do {
            try {
                int typeId = Integer.parseInt(scanner.nextLine().trim());
                boolean exists = bookTypeList.stream()
                        .anyMatch(bookType -> bookType.getTypeId() == typeId);
                if (exists) {
                    return typeId;
                } else {
                    System.err.println("Loại danh sách không tồn tại,vui lòng thử lại");
                }
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số nguyên hợp lệ");
            }
        } while (true);
    }

    public boolean inputBookStatus(Scanner scanner) {
        System.out.print("Trạng thái sách (1: Hoạt động, 0: Không hoạt động): ");
        do {
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                return true;
            }
            if (input.equals("0")) {
                return false;
            }
            System.err.println("Chỉ nhập 1 hoặc 0.");
        } while (true);
    }

}
