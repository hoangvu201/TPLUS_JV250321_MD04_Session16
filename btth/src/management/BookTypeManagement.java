package management;

import dao.BookTypeDAO;
import entity.BookType;
import validate.Validation;

import java.util.List;
import java.util.Scanner;

public class BookTypeManagement {
    public final BookTypeDAO bookTypeDAO;

    public BookTypeManagement(BookTypeDAO bookTypeDAO) {
        this.bookTypeDAO = bookTypeDAO;
    }

    public BookType inputBookType(Scanner scanner, List<BookType> bookTypeList) {
        BookType bookType = new BookType();
        bookType.setTypeId(inputBookTypeId(bookTypeList));
        bookType.setTypeName(inputBookTypeName(scanner, bookTypeList));
        bookType.setTypeDescription(inputBookTypeDescription(scanner));
        bookType.setTypeStatus(true);
        return bookType;
    }

    public int inputBookTypeId(List<BookType> bookTypeList) {
        return bookTypeList.stream()
                .mapToInt(BookType::getTypeId)
                .max()
                .orElse(0) + 1;
    }

    public String inputBookTypeName(Scanner scanner, List<BookType> bookTypeList) {
        System.out.println("Nhập vào tên loại sách: ");
        do {
            String bookTypeName = scanner.nextLine().trim();
            if (!Validation.isEmpty(bookTypeName)) {
                boolean rs = bookTypeList.stream().
                        anyMatch(bookType -> bookType.getTypeName().equals(bookTypeName.trim()));
                if (!rs) {
                    return bookTypeName.trim();
                } else {
                    System.err.println("Tên loại sách bị trùng, vui lòng nhập lại");
                }
            } else {
                System.err.println("Tên không được để trống, vui lòng  nhập lại");
            }
        } while (true);
    }

    public String inputBookTypeDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả: ");
        do {
            String bookTypeDescription = scanner.nextLine();
            if (!Validation.isEmpty(bookTypeDescription)) {
                return bookTypeDescription;
            } else {
                System.err.println("Mô tả không được để trống, vui lòng  nhập lại");
            }
        }
        while (true);
    }

    public static boolean inputBookTypeStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái của loại sách(0 hoặc 1):");
        do {
            try {
                int bookTypeStatus = Integer.parseInt(scanner.nextLine().trim());
                if (bookTypeStatus == 0) {
                    return false;
                } else if (bookTypeStatus == 1) {
                    return true;
                } else {
                    System.err.println("Trạng thái phải là 0 hoặc 1");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số hợp lệ(0 hoặc 1)");
            }
        } while (true);
    }

    public void showAllBookType() {
        List<BookType> bookTypeList = bookTypeDAO.findAll();
        if (bookTypeList.isEmpty()) {
            System.err.println("Dữ liệu rỗng vui lòng nhập dữ liệu");
        } else {
            bookTypeList.forEach(System.out::println);
        }
    }

    public void addBookType(Scanner scanner) {
        List<BookType> bookTypeList = bookTypeDAO.findAll();
        BookType bookType = inputBookType(scanner, bookTypeList);
        bookTypeDAO.addBookType(bookType);
    }

    public void updateBookType(Scanner scanner) {
        List<BookType> bookTypeList = bookTypeDAO.findAll();
        System.out.println("Nhập vào ID loại danh sách cần cập nhật: ");
        do {
            try {
                int bookTypeId = Integer.parseInt(scanner.nextLine().trim());
                BookType bookType = bookTypeList.stream()
                        .filter(bt -> bt.getTypeId() == bookTypeId)
                        .findFirst()
                        .orElse(null);
                if (bookType != null) {
                    boolean isNotExit = true;
                    do {
                        System.out.println("1.Cập nhật tên loại sách");
                        System.out.println("2.Cập nhật mô tả loại sách");
                        System.out.println("3.Cập nhật trạng thái loại sách");
                        System.out.println("4.Thoát");
                        System.out.print("Lựa chọn của bạn: ");
                        int choice = Integer.parseInt(scanner.nextLine().trim());
                        switch (choice) {
                            case 1:
                                System.out.println("Nhập vào tên mới: ");
                                String newBookTypeName = scanner.nextLine();
                                if (!Validation.isBookTypeNameExists(newBookTypeName, bookTypeList)) {
                                    bookType.setTypeName(newBookTypeName);
                                } else {
                                    System.err.println("Tên đã tồn tại");
                                }
                                break;
                            case 2:
                                System.out.println("Nhập vào mô tả mới: ");
                                String newBookTypeDescription = scanner.nextLine();
                                bookType.setTypeDescription(newBookTypeDescription);
                                break;
                            case 3:
                                bookType.setTypeStatus(!bookType.isTypeStatus());
                                break;
                            case 4:
                                isNotExit = false;
                                break;
                            default:
                                System.err.println("Vui lòng chọn từ 1 đến 4");
                        }
                    } while (isNotExit);

                    boolean result = bookTypeDAO.updateBookType(bookType);
                    if (result) {
                        System.out.println("Cập nhật thành công");
                    } else {
                        System.err.println("Có lỗi trong quá trình cập nhật");
                    }
                    break;
                } else {
                    System.err.println("Không tìm thấy loại sách có id: " + bookTypeId);
                }
            } catch (NumberFormatException e) {
                System.err.println("ID không hợp lệ, Vui lòng nhập lại");
            }
        } while (true);
    }

    public void deleteBookType(Scanner scanner) {
        List<BookType> bookTypeList = bookTypeDAO.findAll();
        System.out.println("Nhập vào ID loại sách cần xoá: ");
        do {
            try {
                int bookTypeId = Integer.parseInt(scanner.nextLine());
                boolean exits = bookTypeList.stream().anyMatch(bt -> bt.getTypeId() == bookTypeId);
                if (exits) {
                   boolean result =  bookTypeDAO.deleteBookType(bookTypeId);
                   if (result) {
                       System.out.println("Xoá thành công");
                   } else {
                       System.err.println("Có lỗi trong quá trình xoá");
                   }
                } else {
                    System.err.println("Không có loại sách có id " + bookTypeId);
                }
            } catch (Exception e) {
                System.err.println("ID không hợp lệ, vui lòng nhập lại");
            }
        } while (true);
    }

}
