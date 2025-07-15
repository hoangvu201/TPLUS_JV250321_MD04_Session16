package entity;

public class Book {
    private String bookID;
    private String bookName;
    private String bookTitle;
    private int bookPages;
    private String bookAuthor;
    private float bookPrice;
    private int typeId;
    private boolean bookStatus;

    public Book() {
    }

    public Book(String bookID, String bookName, String bookTitle, int bookPages, String bookAuthor, float bookPrice, int typeId, boolean bookStatus) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookTitle = bookTitle;
        this.bookPages = bookPages;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.typeId = typeId;
        this.bookStatus = bookStatus;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public float getBookPrice(float bookPrice) {
        return this.bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return String.format("Mã Sách: %s - Tên sách: %s - Tiêu đề:%s - Số trang: %d\n" +
                "Tác giả: %s - Giá: %.2f - Loại sách: %d - Trạng thái: $s",
                this.bookID, this.bookName, this.bookTitle, this.bookPages,
                this.bookAuthor, this.bookPrice,this.typeId, this.bookStatus);
    }
}
