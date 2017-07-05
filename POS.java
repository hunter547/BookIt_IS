package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import oracle.jdbc.pool.OracleDataSource;


public class POS {
    private int posID; 
    private static ArrayList<POS> posArray = new ArrayList<>();
    private static int nextID = 0;
    private int storeID; 
    private int custID; 
    private Date posDate; 
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;

    public POS() {
        this.posID = 0;
        this.storeID = 0;
        this.custID = 0;
        this.posDate = new Date();
    } 

    public POS(int storeID, int custID, Date posDate) {
        this.posID = ++nextID;
        this.storeID = storeID;
        this.custID = custID;
        this.posDate = posDate;
    } 

    public POS(int posID, int storeID, int custID, Date posDate) {
        this.posID = posID;
        this.storeID = storeID;
        this.custID = custID;
        this.posDate = posDate; 
        if(posID>nextID){ 
            nextID = posID;
        }
    }
    
    public static void newPosFromDatabase(int posID, int storeID, int custID, Date posDate){ 
        posArray.add(new POS(posID,storeID,custID,posDate));
    } 
    
    public static void newPos(int storeID, int custID, Date posDate){ 
        posArray.add(new POS(storeID,custID,posDate));
    } 
    
    public static void fillPosArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.POS";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                POS.newPosFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getInt(2), 
                                    dbResults.getInt(3),  
                                    dbResults.getDate(4));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void clearPosTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.POS";
        sendDBCommand(sqlQuery);
    }  
    
    public static void insertAllPos() { 
       for(POS pos: posArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.POS (POSID, STOREID, "
                 +  "CUSTID, POSDATE) VALUES (";
        sqlQuery += pos.getPosID()+ " , ";
        sqlQuery += pos.getStoreID()+ ", ";
        sqlQuery += pos.getStoreID()+ ", '"; 
        sqlQuery += pos.getPosDate()+ "')";

        sendDBCommand(sqlQuery);        
      } 
    }

    public int getPosID() {
        return posID;
    }

    public void setPosID(int posID) {
        this.posID = posID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public Date getPosDate() {
        return posDate;
    }

    public void setPosDate(Date posDate) {
        this.posDate = posDate;
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
