package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;


public class Consumable_Order_Line_Item {
    private static ArrayList<Consumable_Order_Line_Item> coliArray = new ArrayList<>();
    private int coliID; 
    private static int nextID =0;
    private int poID; 
    private int conID; 
    private int coliQuantity; 
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;
    
    public Consumable_Order_Line_Item(){ 
        this.coliID = 0; 
        this.poID = 0; 
        this.conID =0; 
        this.coliQuantity = 0;
    } 
    
    public Consumable_Order_Line_Item(int poID, int conID, int coliQuantity){ 
        this.coliID = ++nextID;
        this.poID=poID; 
        this.conID=conID; 
        this.coliQuantity=coliQuantity;
    } 
    

    public Consumable_Order_Line_Item(int coliID, int poID, int conID, int coliQuantity) {
        this.coliID = coliID;
        this.poID = poID;
        this.conID = conID;
        this.coliQuantity = coliQuantity;
    } 
    
    public static void newColiFromDatabase(int coliID, int poID, int conID, int coliQuantity){ 
        coliArray.add(new Consumable_Order_Line_Item(coliID,poID,conID,coliQuantity));
    } 
    
    public static void newColi(int poID, int conID, int coliQuantity){ 
        coliArray.add(new Consumable_Order_Line_Item(poID,conID,coliQuantity));
    } 
    
    public static void fillConsumableOrderLineItemArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.CONSUMABLE_ORDER_LINE_ITEM";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Consumable_Order_Line_Item.newColiFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getInt(2), 
                                    dbResults.getInt(3), 
                                    dbResults.getInt(4));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void clearConsumableOrderLineItemTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.CONSUMABLE_ORDER_LINE_ITEM";
        sendDBCommand(sqlQuery);
    }   
    
    public static void insertAllBookOrderLineItems() { 
       for(Consumable_Order_Line_Item coli : coliArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.CONSUMABLE_ORDER_LINE_ITEM (COLIID, "
                + "POID, CONID, QUANTITY) VALUES (";
        sqlQuery += coli.getColiID()+ " , ";
        sqlQuery += coli.getPoID()+ ", ";
        sqlQuery += coli.getConID()+ ", "; 
        sqlQuery += coli.getColiQuantity()+ ")";

        sendDBCommand(sqlQuery);        
      } 
    }
    
    public int getColiID() {
        return coliID;
    }

    public void setColiID(int coliID) {
        this.coliID = coliID;
    }

    public int getPoID() {
        return poID;
    }

    public void setPoID(int poID) {
        this.poID = poID;
    }

    public int getConID() {
        return conID;
    }

    public void setConID(int conID) {
        this.conID = conID;
    }

    public int getColiQuantity() {
        return coliQuantity;
    }

    public void setColiQuantity(int coliQuantity) {
        this.coliQuantity = coliQuantity;
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
