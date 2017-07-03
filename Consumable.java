package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;

public class Consumable {
    private static ArrayList<Consumable> consumableArray = new ArrayList<>();
    private int conID; 
    private static int nextID = 0;
    private String conName; 
    private double conCost; 
    private double conSalePrice;
    private String conDesc;  
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;  
    
    public Consumable(){ 
        this.conID=0; 
        this.conName="";  
        this.conCost=0.0; 
        this.conSalePrice=0.0;
        this.conDesc="";
    } 
    
    public Consumable(String conName,double conCost,double conSalePrice,String conDesc){ 
        this.conID= ++nextID; 
        this.conName=conName; 
        this.conCost=conCost; 
        this.conSalePrice = conSalePrice;
        this.conDesc=conDesc;
    }

    public int getConID() {
        return conID;
    }

    public void setConID(int conID) {
        this.conID = conID;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public double getConCost() {
        return conCost;
    }

    public void setConCost(double conCost) {
        this.conCost = conCost;
    }
    
    public double getConPrice() {
        return conSalePrice;
    }

    public void setConPrice(double conSalePrice) {
        this.conSalePrice = conSalePrice;
    }

    public String getConDesc() {
        return conDesc;
    }

    public void setConDesc(String conDesc) {
        this.conDesc = conDesc;
    }
    
    public static void newConsumable (String conName,double conCost,
            double conSalePrice,String conDesc)
    {
        consumableArray.add(new Consumable (conName, conCost, conSalePrice, conDesc));
    }
    
    public static void newConsumableFromDatabase (String conName,double conCost,
            double conSalePrice,String conDesc)
    {
        consumableArray.add(new Consumable (conName, conCost, conSalePrice, conDesc));
    }
    
    public static void fillConsumableArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.CONSUMABLE";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                        newConsumableFromDatabase(
                                    dbResults.getString(1),
                                    dbResults.getDouble(2), 
                                    dbResults.getDouble(3), 
                                    dbResults.getString(4));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void clearConsumableTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.CONSUMABLE";
        sendDBCommand(sqlQuery);
    } 
    
    public static void insertAllConsumables() { 
       for(Consumable c: consumableArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.CONSUMABLE (CONSUMABLEID, CONSUMABLENAME,"
                + "CONSUMABLECOST, CONSUMABLEPRICE, CONSUMABLEDESCRIPTION) VALUES (";
        sqlQuery += c.getConID() + " , '";
        sqlQuery += c.getConName() + " , '";
        sqlQuery += c.getConCost() + "', '";
        sqlQuery += c.getConPrice() + "', '"; 
        sqlQuery += c.getConDesc() + "')";

        sendDBCommand(sqlQuery);        
      } 
    }
    
    public static ArrayList getConsumableArray(){ 
        return consumableArray;
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
