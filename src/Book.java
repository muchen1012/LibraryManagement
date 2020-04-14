import java.util.Date;

/**
 * @author 沐辰1012
 */
public class Book {
    /**
     * 书籍的专属ID
     */
    private int bookId;
    /**
     * 书籍的名称
     */
    private String name;
    /**
     * 书籍信息
     */
    private String info;
    /**
     * 状态(借阅与否)
     */
    private boolean state;
    /**
     * 借书人id
     */
    private int borrowId;

    public Book() {
    }

    public Book(int Id, String name, String info) {
        this.bookId = Id;
        this.name = name;
        this.info = info;
        this.state = false;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public void borrowBook(int accountId) {
        this.state = true;
        this.borrowId = accountId;
    }
    public void returnBook(int accountId) {
        this.state = false;
        this.borrowId = 0;
    }
}
