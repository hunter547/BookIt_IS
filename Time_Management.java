package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import oracle.jdbc.pool.OracleDataSource;


public class Time_Management {
    private int timeID; 
    private static ArrayList<Time_Management> timeArray = new ArrayList<>();
    private static int nextID = 0;
    private int storeID; 
    private int employID; 
    private long timeIn; 
    private long timeOut; 
    private String timeInFormat; 
    private String timeOutFormat;
    private double sumToDate;
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;

    public Time_Management() {
        this.timeID = 0;
        this.storeID = 0;
        this.employID = 0;
        this.timeIn = 0;
        this.timeOut = 0; 
        this.timeInFormat = ""; 
        this.timeOutFormat="";
        this.sumToDate = 0.0;
    } 


    public Time_Management(int employID, int storeID, long timeIn) {
        SimpleDateFormat formatDate = new SimpleDateFormat("h:mm:ss a, M/d/YYYY");
        this.timeID = ++nextID;
        this.storeID = storeID;
        this.employID = employID;
        this.timeIn = timeIn; 
        this.timeOut = 0;
        this.timeInFormat = formatDate.format(timeIn); 
        this.timeOutFormat = "On Shift";
    }

    public Time_Management(int timeID, int storeID, int employID, long timeIn, long timeOut, double sumToDate) {
        SimpleDateFormat formatDate = new SimpleDateFormat("h:mm:ss a, M/d/YYYY");
        this.timeID = timeID;
        this.storeID = storeID;
        this.employID = employID;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.sumToDate = sumToDate; 
        this.timeInFormat = formatDate.format(timeIn); 
        if(timeOut==0){ 
            timeOutFormat = "On Shift";
        } 
        else
        { 
        this.timeOutFormat = formatDate.format(timeOut); 
        }
        if(timeID>nextID){ 
            nextID = timeID;
        }
    }
    
    
    
    public static void newTimeManagementFromDatabase(int timeID, int storeID, 
            int employID, long timeIn, long timeOut, double sumToDate){ 
        
        timeArray.add(new Time_Management(timeID,storeID,employID,timeIn,timeOut,sumToDate));
    } 
    
    
    public static boolean timeIn(int employID,int storeID, long timeIn){ 
        boolean didCheckOut = true; 
        for(Time_Management tm: timeArray){ 
            if(tm.getEmployID() == employID){ 
                if(tm.getTimeOut() == 0.0){ 
                    didCheckOut =false;
                }
            }
        } 
        if(didCheckOut){ 
            timeArray.add(new Time_Management(employID,storeID,timeIn)); 

        } 
        return didCheckOut;
    } 
    
    public static boolean timeOut(int employID, int storeID, long timeOut){ 
        boolean didCheckIn = true; 
        double sumToDate = 0.0;
        SimpleDateFormat formatDate = new SimpleDateFormat("h:mm:ss a, M/d/YYYY");
        Time_Management findCheckIn = new Time_Management();
        for(Time_Management tm: timeArray){ 
            if(tm.getEmployID() == employID){ 
                if(tm.getStoreID()==storeID){
                    if(tm.getTimeOut() != 0.0){ 
                        didCheckIn =false; 
                        sumToDate = tm.getSumToDate();
                    } 
                    else{ 
                        didCheckIn = true;
                        findCheckIn = tm;
                    } 
                    }
            }
        } 
        
        if(didCheckIn){ 
            sumToDate = sumToDate + (timeOut - findCheckIn.getTimeIn());
            findCheckIn.setTimeOut(timeOut); 
            findCheckIn.setSumToDate(sumToDate); 
            findCheckIn.setTimeOutFormat(formatDate.format(new Date(timeOut))); 

            
        } 
        return didCheckIn;
    }
    
    public static void fillTimeManagementArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.TIME_MANAGEMENT";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {
                long timeIn = Long.valueOf(dbResults.getString(4)); 
                long timeOut = Long.valueOf(dbResults.getString(5));
                double millisecondSum = Double.valueOf(dbResults.getString(6));
                Time_Management.newTimeManagementFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getInt(2), 
                                    dbResults.getInt(3), 
                                    timeIn,
                                    timeOut,
                                    millisecondSum);

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
                 +  "EMPLOYID, TIMEIN, TIMEOUT, SUMTODATE) VALUES (";
        sqlQuery += tm.getTimeID()+ ", ";
        sqlQuery += tm.getStoreID()+ ", ";
        sqlQuery += tm.getEmployID()+ ", '"; 
        sqlQuery += tm.getTimeIn()+ "', '"; 
        sqlQuery += tm.getTimeOut()+ "', '"; 
        sqlQuery += tm.getSumToDate()+ "')";

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

    public long getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(long timeIn) {
        this.timeIn = timeIn;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }


    public static ArrayList<Time_Management> getTimeArray() {
        return timeArray;
    } 

    public double getSumToDate() {
        return sumToDate;
    }

    public void setSumToDate(double sumToDate) {
        this.sumToDate = sumToDate;
    }

    public String getTimeInFormat() {
        return timeInFormat;
    }

    public void setTimeInFormat(String timeInFormat) {
        this.timeInFormat = timeInFormat;
    }

    public String getTimeOutFormat() {
        return timeOutFormat;
    }

    public void setTimeOutFormat(String timeOutFormat) {
        this.timeOutFormat = timeOutFormat;
    } 
    
    public static ArrayList timeLogForEmployee(int employID){ 
        ArrayList<Time_Management> timeList = new ArrayList<>(); 
        for(Time_Management tm: timeArray){ 
            if(tm.getEmployID() == employID){
                timeList.add(tm);
            }
        } 
        return timeList;
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
