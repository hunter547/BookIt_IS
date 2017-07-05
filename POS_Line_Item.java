package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;

public class POS_Line_Item {
    private int posliID; 
    private static ArrayList<POS_Line_Item> posliArray = new ArrayList<>();
    private static int nextID = 0;
    private int orderID; 
    private int prodID; 
    private char identifier;
    private int posliQuantity; 
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;

    public POS_Line_Item() {
        this.posliID = 0;
        this.orderID = 0;
        this.prodID = 0; 
        this.identifier = ' ';
        this.posliQuantity = 0;
    }

    public POS_Line_Item(int orderID, int prodID,char identifier, int posliQuantity) {
        this.posliID = ++nextID; 
        this.identifier = identifier;
        this.orderID = orderID;
        this.prodID = prodID;
        this.posliQuantity = posliQuantity;
    }  

    public POS_Line_Item(int posliID, int orderID, int prodID, char identifier, int posliQuantity) {
        this.posliID = posliID;
        this.orderID = orderID;
        this.prodID = prodID; 
        this.identifier = identifier;
        this.posliQuantity = posliQuantity; 
        if(posliID>nextID){ 
            nextID = posliID;
        }
    } 
    
    public static void newPosLineItemFromDatabase(int posliID, int orderID, 
            int prodID, char identifier, int posliQuantity){ 
        posliArray.add(new POS_Line_Item(posliID, orderID, prodID, identifier, posliQuantity));
    } 
    
    public static void newPosLineItem(int orderID, int prodID, char identifier, int posliQuantity){ 
        posliArray.add(new POS_Line_Item(orderID,prodID,identifier,posliQuantity));
    } 
    
    public static void fillPosLineItemArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.POS_LINE_ITEM";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                POS_Line_Item.newPosLineItemFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getInt(2), 
                                    dbResults.getInt(3), 
                        (Character) dbResults.getObject(4),
                                    dbResults.getInt(5));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void clearPosLineItemTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.POS_LINE_ITEM";
        sendDBCommand(sqlQuery);
    }  
    
    public static void insertAllPosLineItems() { 
       for(POS_Line_Item posli: posliArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.POS_LINE_ITEM (POSLIID, ORDERID, "
                 +  "PRODID, IDENTIFIER, QUANTITY) VALUES (";
        sqlQuery += posli.getPosliID()+ ", ";
        sqlQuery += posli.getOrderID()+ ", ";
        sqlQuery += posli.getProdID()+ ", '"; 
        sqlQuery += posli.getIdentifier()+ "', "; 
        sqlQuery += posli.getPosliQuantity()+ ")";

        sendDBCommand(sqlQuery);        
      } 
    }

    public int getPosliID() {
        return posliID;
    }

    public void setPosliID(int posliID) {
        this.posliID = posliID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    } 

    public char getIdentifier() {
        return identifier;
    }

    public void setIdentifier(char identifier) {
        this.identifier = identifier;
    }
    

    public int getPosliQuantity() {
        return posliQuantity;
    }

    public void setPosliQuantity(int posliQuantity) {
        this.posliQuantity = posliQuantity;
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
