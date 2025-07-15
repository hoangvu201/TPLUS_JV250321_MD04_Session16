import dao.BookDAOImp;
import dao.BookTypeDAOImp;
import management.BookManagement;
import management.BookTypeManagement;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookTypeManagement bookTypeManagement = new BookTypeManagement(new BookTypeDAOImp());
    private static final BookManagement bookManagement = new BookManagement(new BookDAOImp(), new BookTypeDAOImp());

    public static void main(String[] args) {
        do {
            System.out.println("=====BOOK MANAGEMENT=====");
            System.out.println("1. Quản lý loại sách");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        bookTypeMenu();
                        break;
                    case 2:
                        bookMenu();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1 đến 3.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số nguyên.");
            }
        } while (true);
    }

    public static void bookTypeMenu() {
        boolean isExit = false;
        do {
            System.out.println("=====BOOK TYPE=====");
            System.out.println("1. Danh sách loại sách");
            System.out.println("2. Thêm mới loại sách");
            System.out.println("3. Cập nhật loại sách");
            System.out.println("4. Xóa loại sách");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        bookTypeManagement.showAllBookType();
                        break;
                    case 2:
                        bookTypeManagement.addBookType(scanner);
                        break;
                    case 3:
                        bookTypeManagement.updateBookType(scanner);
                        break;
                    case 4:
                        bookTypeManagement.deleteBookType(scanner);
                        break;
                    case 5:
                        isExit = true;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 0 đến 4.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số nguyên.");
            }
        } while (!isExit);
    }

    public static void bookMenu() {
        boolean isExit = false;
        do {
            System.out.println("=====BOOK=====");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Thêm mới nhiều sách");
            System.out.println("4. Cập nhật sách");
            System.out.println("5. Xóa sách");
            System.out.println("6. Tìm kiếm sách");
            System.out.println("7. Thống kê số lượng sách theo tác giả");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        bookManagement.getAllBooks();
                        break;
                    case 2:
                        bookManagement.addBook(scanner,new BookTypeDAOImp().findAll());
                        break;
                    case 3:
                        bookManagement.addListBooks(scanner,new BookTypeDAOImp().findAll());
                        break;
                    case 4:
                        bookManagement.updateBook(scanner,new BookTypeDAOImp().findAll());
                        break;
                    case 5:
                        bookManagement.deleteBook(scanner);
                        break;
                    case 6:
                       bookManagement.findBookById(scanner);
                       break;
                       case 7:
                          break;
                    case 8:
                        isExit = true;
                    default:
                        System.err.println("Vui lòng chọn từ 1 đến 8.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số nguyên.");
            }
        } while (!isExit);
    }
}
