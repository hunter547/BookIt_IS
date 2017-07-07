package BookIt_IS;

import java.util.ArrayList; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;


public class Payroll {
    private int payID; 
    private static ArrayList<Payroll> payArray = new ArrayList<>();
    private static int nextID = 0;
    private int employID; 
    private int storeID; 
    private double payHoursWorked; 
    private double payTotal; 
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;


    public Payroll() {
        this.payID = 0;
        this.employID = 0;
        this.storeID = 0;
        this.payHoursWorked = 0;
        this.payTotal = 0;
    }

    public Payroll(int employID, int storeID, double payHoursWorked, double payTotal) {
        this.payID = ++nextID;
        this.employID = employID;
        this.storeID = storeID;
        this.payHoursWorked = payHoursWorked;
        this.payTotal = payTotal;
    }

    public Payroll(int payID, int employID, int storeID, double payHoursWorked, double payTotal) {
        this.payID = payID;
        this.employID = employID;
        this.storeID = storeID;
        this.payHoursWorked = payHoursWorked;
        this.payTotal = payTotal; 
        if(payID>nextID){ 
            nextID = payID;
        }
    } 
    
    public static void newPayrollFromDatabase(int payID, int employID, int storeID, double payHoursWorked, double payTotal){ 
        payArray.add(new Payroll(payID,employID,storeID,payHoursWorked,payTotal));
    }
    
    public static void newPayroll(int employID, int storeID, double payHoursWorked, double payTotal){ 
        payArray.add(new Payroll(employID,storeID,payHoursWorked,payTotal));
    } 
    
    public static void fillPayrollArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.PAYROLL";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Payroll.newPayrollFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getInt(2), 
                                    dbResults.getInt(3), 
                                    dbResults.getDouble(4),
                                    dbResults.getDouble(5));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    public static void clearPayrollTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.PAYROLL";
        sendDBCommand(sqlQuery);
    } 
    
    public static void insertAllPayrolls() { 
       for(Payroll p: payArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.PAYROLL (PAYID, EMPLOYID, "
                 +  "STOREID, HOURSWORKED, PAYTOTAL) VALUES (";
        sqlQuery += p.getPayID()+ ", ";
        sqlQuery += p.getEmployID()+ ", ";
        sqlQuery += p.getStoreID()+ ", "; 
        sqlQuery += p.getPayHoursWorked()+ ", "; 
        sqlQuery += p.getPayTotal()+ ")";

        sendDBCommand(sqlQuery);        
      } 
    }

    public int getPayID() {
        return payID;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public int getEmployID() {
        return employID;
    }

    public void setEmployID(int employID) {
        this.employID = employID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public double getPayHoursWorked() {
        return payHoursWorked;
    }

    public void setPayHoursWorked(double payHoursWorked) {
        this.payHoursWorked = payHoursWorked;
    }

    public double getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(double payTotal) {
        this.payTotal = payTotal;
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
