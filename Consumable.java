package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;

public class Consumable {
    private static ArrayList<Consumable> conArray = new ArrayList<>();
    private int conID; 
    private static int nextID = 0;
    private String conName; 
    private double conCost; 
    private double conSalePrice;
    private String conDesc; 
    private int conQuantity;
    private int conStore;
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;

    public Consumable(){ 
        this.conID=0; 
        this.conName="";  
        this.conCost=0.0; 
        this.conSalePrice=0.0;
        this.conDesc="";
        this.conQuantity = 0;
        this.conStore = 1;
    } 
    
    public Consumable(String conName,double conCost,double conSalePrice,
            String conDesc, int conQuantity, int conStore){ 
        this.conID= ++nextID; 
        this.conName=conName; 
        this.conCost=conCost; 
        this.conSalePrice = conSalePrice;
        this.conDesc=conDesc;
        this.conQuantity = conQuantity;
        this.conStore = conStore;
    } 
    
    public Consumable(int conID,String conName,double conCost,double conSalePrice,String conDesc, int conQuantity, int conStore){ 
        this.conID= conID; 
        this.conName=conName; 
        this.conCost=conCost; 
        this.conSalePrice = conSalePrice;
        this.conDesc=conDesc;
        this.conQuantity = conQuantity;
        this.conStore = conStore;
        
        if(conID>nextID){ 
            nextID = conID;
        }
    } 
    
    public static void newConFromDatabase(int conID, String conName, double conCost, 
            double conSalePrice, String conDesc, int conQuantity, int conStore)
    { 
        conArray.add(new Consumable(conID,conName,conCost,conSalePrice,conDesc,conQuantity,conStore));
    } 
    
    public static void newCon(String conName,double conCost,double conSalePrice, 
            String conDesc, int conQuantity, int conStore){ 
        conArray.add(new Consumable(conName,conCost,conSalePrice,conDesc,conQuantity,conStore));
    }
    
    public static void fillConsumableArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.CONSUMABLE";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Consumable.newConFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getString(2), 
                                    dbResults.getDouble(3), 
                                    dbResults.getDouble(4),
                                    dbResults.getString(5),
                                    dbResults.getInt(6),
                                    dbResults.getInt(7))
                        ;

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
       for(Consumable c: conArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.CONSUMABLE (CON_ID, CON_NAME, "
                 +  "CON_COST, CON_SALE_PRICE, CON_DESC, CON_STORE) VALUES (";
        sqlQuery += c.getConID() + " , '";
        sqlQuery += c.getConName()+ "', ";
        sqlQuery += c.getConCost()+ ", "; 
        sqlQuery += c.getConSalePrice() + ", '";
        sqlQuery += c.getConDesc() + ", '";
        sqlQuery += c.getConStore() + "')";

        sendDBCommand(sqlQuery);        
      } 
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
    public double getConSalePrice() {
        return conSalePrice;
    } 
    
    public void setConSalePrice(double conSalePrice){ 
        this.conSalePrice=conSalePrice;
    }
    
    public int getConQuantity() {
    return conQuantity;
    } 
    
    public void setConQuantity(int conQuantity){ 
        this.conQuantity = conQuantity;
    }
    
    public int getConStore() {
    return conStore;
    } 
    
    public void setConStore(int conStore){ 
        this.conStore = conStore;
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
    
    public static ArrayList<Consumable> getConsumableArray(){ 
        return conArray;
    }
}
