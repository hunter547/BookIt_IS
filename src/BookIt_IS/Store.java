package BookIt_IS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;


public class Store {
    private static ArrayList<Store> storeArray = new ArrayList<>(); 
    private static int nextID = 0; 
    private int storeID; 
    private String storeName;
    private String storeAddress; 
    private String storePhone; 
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;    
    
    public Store() { 
        this.storeID = 0; 
        this.storeAddress = ""; 
        this.storePhone = ""; 
        this.storeName = "";
    } 
    
    public Store(String storeName,String storeAddress,String storePhone) { 
        this.storeName = storeName;
        this.storeAddress = storeAddress; 
        this.storePhone = storePhone; 
        this.storeID = ++nextID;
    } 
    
    public Store(int storeID,String storeName,String storeAddress,String storePhone) { 
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeAddress = storeAddress; 
        this.storePhone = storePhone; 
        if(storeID>nextID){ 
            nextID = storeID;
        }
    }
    
    public static void newStore(String storeName,String storeAddress,String storePhone) { 
        
        storeArray.add(new Store(storeName,storeAddress,storePhone));
        
    } 
    
    public static void newStorefromDatabase(int storeID,String storeName,String storeAddress,String storePhone) { 
        
        storeArray.add(new Store(storeID,storeName,storeAddress,storePhone));
        
    } 
    
    
    public static void fillStoreArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.STORE";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Store.newStorefromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getString(2), 
                                    dbResults.getString(3), 
                                    dbResults.getString(4));
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void clearStoreTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.STORE";
        sendDBCommand(sqlQuery);
    } 
    
    public static void insertAllStores() { 
       for(Store s: storeArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.STORE (STOREID, STORENAME, STOREADDRESS, STOREPHONE, AREAID) VALUES (";
        sqlQuery += s.getStoreID() + " , '";
        sqlQuery += s.getStoreName() + "', '";
        sqlQuery += s.getStoreAddress() + "', '"; 
        sqlQuery += s.getStorePhone() + "')";

        sendDBCommand(sqlQuery);        
      } 
    }
    
    public int getStoreID(){ 
        return this.storeID;
    } 
    
    public String getStoreAddress(){ 
        return this.storeAddress;
    } 
    
    public String getStorePhone(){ 
        return this.storePhone;
    } 
    
    
    public String getStoreName(){ 
        return this.storeName;
    } 
    
    public static String getStoreName(int storeID){ 
        String s = "";
        for(Store st: storeArray){ 
            if(storeID==st.getStoreID()){ 
                s = st.getStoreName();
            }
        } 
        
        return s;
    }
    
    public static ArrayList getStoreArray(){ 
        return storeArray;
    } 
    
    public void setStoreAddress(String storeAddress) { 
        this.storeAddress = storeAddress;
    } 
    
    public void setStorePhone(String storePhone) { 
        this.storePhone = storePhone;
    } 

    
    public void setStoreName(String storeName){ 
        this.storeName = storeName;
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

