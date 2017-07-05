package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import oracle.jdbc.pool.OracleDataSource;


public class Product_Order {
    private int poID; 
    private static ArrayList<Product_Order> poArray = new ArrayList<>();
    private static int nextID = 0;
    private Date poDate; 
    private int suppID; 
    private int salespID; 
    private int storeID; 
    private double poTotal; 
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;

    public Product_Order() {
        this.poID = 0;
        this.poDate = new Date();
        this.suppID = 0;
        this.salespID = 0;
        this.storeID = 0;
        this.poTotal = 0.0; 
        
    } 

    public Product_Order(Date poDate, int suppID, int salespID, int storeID, 
            double poTotal) {
        this.poID = ++ nextID;
        this.poDate = poDate;
        this.suppID = suppID;
        this.salespID = salespID;
        this.storeID = storeID;
        this.poTotal = poTotal;
    }

    public Product_Order(int poID, Date poDate, int suppID, int salespID, 
            int storeID, double poTotal) {
        this.poID = poID;
        this.poDate = poDate;
        this.suppID = suppID;
        this.salespID = salespID;
        this.storeID = storeID;
        this.poTotal = poTotal; 
        if(poID>nextID){ 
            nextID = poID;
        }
    }
    
    public static void newProductOrderFromDatabase(int poID, Date poDate, 
            int suppID, int salespID, int storeID, double poTotal){ 
        poArray.add(new Product_Order(poID,poDate,suppID,salespID,storeID,poTotal));
    } 
    
    public static void newProductOrder(Date poDate, 
            int suppID, int salespID, int storeID, double poTotal){ 
        poArray.add(new Product_Order(poDate,suppID,salespID,storeID,poTotal));
    } 
    
    public static void fillProductOrderArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.PRODUCT_ORDER";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Product_Order.newProductOrderFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getDate(2), 
                                    dbResults.getInt(3), 
                                    dbResults.getInt(4), 
                                    dbResults.getInt(5),
                                    dbResults.getDouble(6));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    public static void clearProductOrderTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.PRODUCT_ORDER";
        sendDBCommand(sqlQuery);
    } 
    
    public static void insertAllProductOrders() { 
       for(Product_Order po: poArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.PRODUCT_ORDER (POID, PODATE, "
                 +  "SUPPID, SALESPID, STOREID, POTOTAL) VALUES (";
        sqlQuery += po.getPoID()+ ", '";
        sqlQuery += po.getPoDate()+ "', ";
        sqlQuery += po.getSuppID()+ ", "; 
        sqlQuery += po.getSalespID()+ ", "; 
        sqlQuery += po.getStoreID()+ ", ";         
        sqlQuery += po.getPoTotal()+ ")";

        sendDBCommand(sqlQuery);        
      } 
    }
    
    public int getPoID() {
        return poID;
    }

    public void setPoID(int poID) {
        this.poID = poID;
    }

    public Date getPoDate() {
        return poDate;
    }

    public void setPoDate(Date poDate) {
        this.poDate = poDate;
    }

    public int getSuppID() {
        return suppID;
    }

    public void setSuppID(int suppID) {
        this.suppID = suppID;
    }

    public int getSalespID() {
        return salespID;
    }

    public void setSalespID(int salespID) {
        this.salespID = salespID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public double getPoTotal() {
        return poTotal;
    }

    public void setPoTotal(double poTotal) {
        this.poTotal = poTotal;
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
