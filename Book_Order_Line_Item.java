package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;

public class Book_Order_Line_Item {
    private int boliID; 
    private static ArrayList<Book_Order_Line_Item> boliArray = new ArrayList<>();
    private static int nextID = 0;
    private int orderID; 
    private int bookID;
    private int boliQuantity;  
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;
    
    public Book_Order_Line_Item(){ 
        this.boliID=0; 
        this.orderID=0; 
        this.bookID=0; 
        this.boliQuantity=0;
    } 
    
    public Book_Order_Line_Item(int orderID, int bookID, int boliQuantity){ 
        this.orderID=orderID; 
        this.bookID=bookID; 
        this.boliQuantity=boliQuantity; 
        this.boliID=++nextID;
    } 
    
    public Book_Order_Line_Item(int boliID, int orderID, int bookID, int boliQuantity){ 
        this.boliID=boliID; 
        this.orderID=orderID; 
        this.bookID=bookID; 
        this.boliQuantity=boliQuantity; 
        if(boliID>nextID){ 
            nextID=boliID;
        }
    } 
    
    public static void newBoliFromDatabase(int boliID, int orderID, int bookID, int boliQuantity){ 
        boliArray.add(new Book_Order_Line_Item(boliID,orderID,bookID,boliQuantity));
    } 
    
    public static void newBoli(int orderID,int bookID, int boliQuantity){ 
        boliArray.add(new Book_Order_Line_Item(orderID,bookID,boliQuantity));
    } 
    
    public static void fillBookOrderLineItemArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.BOOK_ORDER_LINE_ITEM";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Book_Order_Line_Item.newBoliFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getInt(2), 
                                    dbResults.getInt(3), 
                                    dbResults.getInt(4));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void clearBookOrderLineItemTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.BOOK_ORDER_LINE_ITEM";
        sendDBCommand(sqlQuery);
    }  
    
    public static void insertAllBookOrderLineItems() { 
       for(Book_Order_Line_Item boli : boliArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.BOOK_ORDER_LINE_ITEM (BOLI_ID, ORDER_ID, BOOK_ID, QUANTITY) VALUES (";
        sqlQuery += boli.getBoliLineItemID()+ " , ";
        sqlQuery += boli.getOrderID()+ ", ";
        sqlQuery += boli.getBookID()+ ", "; 
        sqlQuery += boli.getBoliQuantity()+ ")";

        sendDBCommand(sqlQuery);        
      } 
    }

    
    public int getBoliLineItemID() {
        return boliID;
    }

    public void setBoliLineItemID(int boliID) {
        this.boliID = boliID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBoliQuantity() {
        return boliQuantity;
    }

    public void setBoliQuantity(int boliQuantity) {
        this.boliQuantity = boliQuantity;
    } 
    
    private static void sendDBCommand(String sqlQuery) {
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser"; 
        //<editor-fold defaultstate="collapsed" desc="password">
        String userPASS = "javapass"; // Change to YOUR Oracle password
//</editor-fold>
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
