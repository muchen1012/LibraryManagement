import java.util.HashMap;

/**
 * @author 沐辰1012
 */
public class Account {
    /**
     * 账号id
     */
    private int accountId;
    /**
     * 账号密码
     */
    private String password;
    /**
     * 身份，即是否为管理员
     */
    private boolean status;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学号
     */
    private int studentId;
    /**
     * 已借书籍
     */
    private HashMap<Integer, Book>borrowBooks;
    /**
     * 收藏
     */
    private HashMap<Integer,Book>bookCollection;


    public Account() {
        this.bookCollection = new HashMap<Integer,Book>();
        this.borrowBooks = new HashMap<Integer,Book>();
    }

    public Account(int accountId, String password, String name, int stuentId) {
        this();
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.studentId = stuentId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStuentId() {
        return studentId;
    }

    public void setStuentId(int stuentId) {
        this.studentId = stuentId;
    }

    public HashMap<Integer, Book> getBorrowBooks() {
        return borrowBooks;
    }

    public void setBorrowBooks(HashMap<Integer, Book> borrowBooks) {
        this.borrowBooks = borrowBooks;
    }

    public HashMap<Integer, Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(HashMap<Integer, Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    public void borrowBook(Book book){
        borrowBooks.put(book.getBookId(),book);
    }

    public void returnBook(Book book){
        borrowBooks.remove(book.getBookId(),book);
    }
}
