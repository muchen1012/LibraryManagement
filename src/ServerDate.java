import java.util.HashMap;
/**
 * @author 沐辰1012
 */
public class ServerDate {
    private static final int ADMIN  = 2,User = 1,ILLEGAL_USER = 0;

    /**
     * 账号初始ID
     */
    private static int accountInitId = 100000;
    /**
     *
     */
    private static int bookInitId = 100000;//书籍初始ID
    /**
     *书籍数据
     */
    private static HashMap<Integer, Book> allBooks;
    /**
     *按书名存的书籍数据
     */
    private static HashMap<String, Book> allBooks1;
    /**
     *账号数据
     */
    private static HashMap<Integer, Account> accounts;

    public static int getAccountInitId() {
        return accountInitId;
    }

    public static void setAccountInitId(int accountInitId) {
        ServerDate.accountInitId = accountInitId;
    }

    public static int getBookInitId() {
        return bookInitId;
    }

    public static void setBookInitId(int bookInitId) {
        ServerDate.bookInitId = bookInitId;
    }

    public static HashMap<Integer, Book> getAllBooks() {
        return allBooks;
    }

    public static void setAllBooks(HashMap<Integer, Book> allBooks) {
        ServerDate.allBooks = allBooks;
    }

    public static HashMap<Integer, Account> getAccounts() {
        return accounts;
    }

    public static void setAccounts(HashMap<Integer, Account> accounts) {
        ServerDate.accounts = accounts;
    }

    /**
     *判断身份，略写
     */
    public static int identifyJudge(String name,String studentId){
        //管理员
        if(studentId.length() == 6){
            return ADMIN;
        }
        //用户
        else if(studentId.length() == 11){
            return User;
        }
        //非法用户
        else{
            return ILLEGAL_USER;
        }
    }
    /**
     *登录
     */
    public static Account accountLogin(int id, String password){
        if(accounts.get(id).getPassword().equals(password)){
            return accounts.get(id);
        }
        else {
            return null;
        }
    }
    /**
     *注册
     */
    public static Account accountRegister(String name, String studentId, String password){
        int studentID = Integer.parseInt(studentId);
        Account account = new Account();
        //非法用户
        if(ServerDate.identifyJudge(name, studentId) == ILLEGAL_USER){
            return null;
        }
        //用户
        else if(ServerDate.identifyJudge(name, studentId) == User){
            account =new Account(accountInitId++, password, name, studentID);
        }
        //管理员
        else if(ServerDate.identifyJudge(name, studentId) == ADMIN){
            account =new Account(accountInitId++, password, name, studentID);
            account.setStatus(true);
        }
        accounts.put(account.getAccountId(), account);
        return account;
    }
    /**
     *按书名查书
     */
    public static Book searchBook(String bookName){
        if(allBooks1.containsKey(bookName)){
            return allBooks.get(bookName);
        }
        else {
            return null;
        }
    }
    /**
     *按书的ID查书
     */
    public static Book searchBook(int bookId){
        if(allBooks.containsKey(bookId)){
            return allBooks.get(bookId);
        }
        else {
            return null;
        }
    }
    /**
     *借书
     */
    public static Boolean borrowBook(int accountId,int bookId){
        if(!allBooks.containsKey(bookId)){
            return false;
        }
        if(!accounts.containsKey(accountId)){
            return false;
        }
        Account account = accounts.get(accountId);
        Book book = allBooks.get(bookId);
        if(book.getState() == false ){
            book.borrowBook(accountId);
            account.borrowBook(book);
            return true;
        }
        else {
            return false;
        }
    }
    /**
     *还书
     */
    public static Boolean returnBook(int accountId,int bookId){
        if(!allBooks.containsKey(bookId)){
            return false;
        }
        if(!accounts.containsKey(accountId)){
            return false;
        }
        Account account = accounts.get(accountId);
        Book book = allBooks.get(bookId);
        book.borrowBook(accountId);
        account.borrowBook(book);
        return true;
    }
    /**
     *添加书籍
     */
    public static Book addBook(String name, String info){
        Book book = new Book(bookInitId ++ ,name,info);
        allBooks.put(book.getBookId(),book);
        allBooks1.put(book.getName(),book);
        return book;
    }
    /**
     *删除书籍
     */
    public static void removeBook(int bookId){
        if(allBooks.containsKey(bookId)){
            Book book = allBooks.get(bookId);
            allBooks.remove(bookId);
            if(allBooks1.containsKey(book.getName())){
                allBooks1.remove(book.getName());
            }
        }
    }
    /**
     *添加管理员账号
     */
    public static Boolean addAdmin(int adminAccountId, int userAccountId){
        //账号是否存在
        if(accounts.containsKey(adminAccountId) && accounts.containsKey(userAccountId)){
            Account admin = accounts.get(adminAccountId);
            Account user = accounts.get(userAccountId);
            //处理账号是否为管理员
            if(admin.getStatus()){
                user.setStatus(true);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    /**
     *删除用户账号
     */
    public static Boolean removeUser(int adminAccountId, int userAccountId){
        if(accounts.containsKey(adminAccountId) && accounts.containsKey(userAccountId)) {
            Account admin = accounts.get(adminAccountId);
            Account user = accounts.get(userAccountId);
            if(admin.getStatus() ==true && user.getStatus() == false){
                accounts.remove(user.getAccountId());
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }
}
