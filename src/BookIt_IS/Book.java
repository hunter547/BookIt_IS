package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;


public class Book 
{
    private static ArrayList<Book> bookArray = new ArrayList<>();
    private int bookID; 
    private static int nextID = 0;
    private String bookTitle; 
    private String bookAuthor; 
    private double bookCost; 
    private double bookSalePrice; 
    private String bookDescription;
    private int bookQuantity;
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;

    public Book(){ 
        this.bookID = 0; 
        this.bookTitle=""; 
        this.bookAuthor=""; 
        this.bookCost=0.0; 
        this.bookSalePrice=0.0; 
        this.bookDescription ="";
        this.bookQuantity = 0;
    } 
    
    public Book(String bookTitle, String bookAuthor, double bookCost, double bookSalePrice, String bookDescription, int bookQuantity) { 
        this.bookTitle = bookTitle; 
        this.bookAuthor = bookAuthor; 
        this.bookCost = bookCost; 
        this.bookSalePrice = bookSalePrice; 
        this.bookDescription = bookDescription;
        this.bookQuantity = bookQuantity;
        this.bookID = ++nextID;
    } 
    
    public Book(int bookID,String bookTitle, String bookAuthor, double bookCost, double bookSalePrice, String bookDescription, int bookQuantity){ 
        this.bookTitle = bookTitle; 
        this.bookAuthor = bookAuthor; 
        this.bookCost = bookCost; 
        this.bookSalePrice = bookSalePrice; 
        this.bookDescription = bookDescription; 
        this.bookID = bookID; 
        this.bookQuantity = bookQuantity;
        
        // Ensures the nextID is equal to the largest current bookID when being 
        // completely loaded from the database. This setup allows for an easy 
        // assignment of the next bookID in the constructor above
        if(bookID>nextID){ 
            nextID = bookID;
        }
    }
    
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public double getBookCost() {
        return bookCost;
    }

    public void setBookCost(double bookCost) {
        this.bookCost = bookCost;
    }

    public double getBookSalePrice() {
        return bookSalePrice;
    }

    public void setBookSalePrice(double bookSalePrice) {
        this.bookSalePrice = bookSalePrice;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
    
    public int getBookQuantity()
    {
        return bookQuantity;
    }
    
    public void setBookQuantity(int bookQuantity)
    {
        this.bookQuantity = bookQuantity;
    }
    
    public static void newBook (String bookTitle, String bookAuthor, 
            double bookCost, double bookSalePrice, String bookDescription, int bookQuantity)
    {
        bookArray.add
        (new Book(bookTitle, bookAuthor, bookCost, bookSalePrice, bookDescription, bookQuantity));
    }
    
    public static void newBookFromDatabase (String bookTitle, String bookAuthor, 
            double bookCost, double bookSalePrice, String bookDescription, int bookQuantity)
    {
        bookArray.add
        (new Book(bookTitle, bookAuthor, bookCost, bookSalePrice, bookDescription, bookQuantity));
    }
    
    public static void fillBookArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.BOOK";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Book.newBookFromDatabase(
                                    dbResults.getString(1),
                                    dbResults.getString(2), 
                                    dbResults.getDouble(3), 
                                    dbResults.getDouble(4), 
                                    dbResults.getString(5),
                                    dbResults.getInt(6));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void clearBookTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.BOOK";
        sendDBCommand(sqlQuery);
    } 
    
    public static void insertAllBooks() { 
       for(Book b: bookArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.BOOK (BOOKID, BOOKTITLE, "
                 +  "BOOKAUTHOR, BOOKCOST, BOOKSALEPRICE, BOOKDESCRIPTION, BOOKQUANTITY) VALUES (";
        sqlQuery += b.getBookID() + " , '";
        sqlQuery += b.getBookTitle() + "', '";
        sqlQuery += b.getBookAuthor() + "', '"; 
        sqlQuery += b.getBookCost() + "', '";
        sqlQuery += b.getBookSalePrice() + "', '";
        sqlQuery += b.getBookQuantity() + "', '";
        sqlQuery += b.getBookDescription() + "')";

        sendDBCommand(sqlQuery);        
      } 
    }   
   
    public static ArrayList getBookArray(){ 
        return bookArray;
    }
    
    private static void sendDBCommand(String sqlQuery) {
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser"; 
        String userPASS = "javapass";
        
        OracleDataSource ds;
        System.out.println(sqlQuery);
        try {
            ds = new OracleDataSource();
            ds.setURL(URL);
            dbConn = ds.getConnection(userID, userPASS);
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            dbResults = commStmt.executeQuery(sqlQuery); // Sends the Query to the DB
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
