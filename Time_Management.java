package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import oracle.jdbc.pool.OracleDataSource;


public class Time_Management {
    private int timeID; 
    private static ArrayList<Time_Management> timeArray = new ArrayList<>();
    private static int nextID = 0;
    private int storeID; 
    private int employID; 
    private Date timeIn; 
    private Date timeOut;  
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;

    public Time_Management() {
        this.timeID = 0;
        this.storeID = 0;
        this.employID = 0;
        this.timeIn = new Date();
        this.timeOut = new Date();
    } 

    public Time_Management(int storeID, int employID, Date timeIn, Date timeOut) {
        this.timeID = ++nextID;
        this.storeID = storeID;
        this.employID = employID;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public Time_Management(int timeID, int storeID, int employID, Date timeIn, Date timeOut) {
        this.timeID = timeID;
        this.storeID = storeID;
        this.employID = employID;
        this.timeIn = timeIn;
        this.timeOut = timeOut; 
        if(timeID>nextID){ 
            nextID = timeID;
        }
    } 
    
    public static void newTimeManagementFromDatabase(int timeID, int storeID, 
            int employID, Date timeIn, Date timeOut){ 
        
        timeArray.add(new Time_Management(timeID,storeID,employID,timeIn,timeOut));
    } 
    
    public static void newTimeManagement(int storeID, int employID, Date timeIn, 
            Date timeOut){ 
        
        timeArray.add(new Time_Management(storeID,employID,timeIn,timeOut));
    } 
    
    public static void fillTimeManagementArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.TIME_MANAGEMENT";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Time_Management.newTimeManagementFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getInt(2), 
                                    dbResults.getInt(3), 
                                    dbResults.getDate(4),
                                    dbResults.getDate(5));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    public static void clearTimeManagementTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.TIME_MANAGEMENT";
        sendDBCommand(sqlQuery);
    } 
    
    public static void insertAllTimeManagements() { 
       for(Time_Management tm: timeArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.TIME_MANAGEMENT (TIMEID, STOREID, "
                 +  "EMPLOYID, TIMEIN, TIMEOUT) VALUES (";
        sqlQuery += tm.getTimeID()+ ", ";
        sqlQuery += tm.getStoreID()+ ", ";
        sqlQuery += tm.getEmployID()+ ", '"; 
        sqlQuery += tm.getTimeIn()+ "', '"; 
        sqlQuery += tm.getTimeOut()+ "')";

        sendDBCommand(sqlQuery);        
      } 
    }
    
    public int getTimeID() {
        return timeID;
    }

    public void setTimeID(int timeID) {
        this.timeID = timeID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getEmployID() {
        return employID;
    }

    public void setEmployID(int employID) {
        this.employID = employID;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
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
