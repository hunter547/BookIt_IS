package BookIt_IS;

//test
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;

public class Employee {
    private int employID; 
    private static ArrayList<Employee> employArray = new ArrayList<>();
    private static int nextID = 0;
    private String employFirstName; 
    private String employLastName; 
    private String employPhone; 
    private double employPayrate; 
    private String employUsername; 
    private String employPassword;
    private int employStatus;   
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;
    
    public Employee(){ 
        this.employID=0; 
        this.employFirstName=""; 
        this.employLastName=""; 
        this.employPhone=""; 
        this.employPayrate=0.0; 
        this.employUsername = ""; 
        this.employPassword = ""; 
        this.employStatus=0;
    } 

    public Employee(String employFirstName, String employLastName, 
            String employPhone, double employPayrate, String employUsername, 
            String employPassword, int employStatus) {
        this.employID = ++ nextID;
        this.employFirstName = employFirstName;
        this.employLastName = employLastName;
        this.employPhone = employPhone;
        this.employPayrate = employPayrate; 
        this.employUsername = employUsername;
        this.employPassword = employPassword;
        this.employStatus = employStatus;
    } 

    public Employee(int employID, String employFirstName, String employLastName, 
            String employPhone, double employPayrate, String employUsername, 
            String employPassword, int employStatus) {
        this.employID = employID;
        this.employFirstName = employFirstName;
        this.employLastName = employLastName;
        this.employPhone = employPhone;
        this.employPayrate = employPayrate;
        this.employUsername = employUsername;
        this.employPassword = employPassword;
        this.employStatus = employStatus;
        if(employID>nextID){ 
            nextID = employID;
        }
    }

         
    public static void newEmployeeFromDatabase(int employID, String employFirstName, 
            String employLastName, String employPhone, double employPayrate, 
            String employUsername, String employPassword, int employStatus){ 
        employArray.add(new Employee(employID,employFirstName,employLastName,
            employPhone,employPayrate,employUsername,employPassword,employStatus));
    } 
    
    public static void newEmployee(String employFirstName, String employLastName, 
            String employPhone, double employPayrate, String employUsername, 
            String employPassword, int employStatus){ 
        employArray.add(new Employee(employFirstName,employLastName,
            employPhone,employPayrate,employUsername,employPassword,employStatus));    
    } 
    
    public static void fillEmployeeArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.EMPLOYEE";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Employee.newEmployeeFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getString(2), 
                                    dbResults.getString(3), 
                                    dbResults.getString(4), 
                                    dbResults.getDouble(5),
                                    dbResults.getString(6),
                                    dbResults.getString(7),
                                    dbResults.getInt(8));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    }
    
    public static void clearEmployeeTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.EMPLOYEE";
        sendDBCommand(sqlQuery);
    } 
    
    public static void insertAllEmployees() { 
       for(Employee e: employArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.EMPLOYEE (EMPLOYID, EMPLOYFIRSTNAME, "
                + "EMPLOYLASTNAME, EMPLOYPHONE, EMPLOYPAYRATE, EMPLOYUSERNAME, "
                + "EMPLOYPASSWORD, EMPLOYSTATUS) VALUES (";
        sqlQuery += e.getEmployID()+ " , '";
        sqlQuery += e.getEmployFirstName()+ "', '";
        sqlQuery += e.getEmployLastName()+ "', '"; 
        sqlQuery += e.getEmployPhone()+ "', ";
        sqlQuery += e.getEmployPayrate()+ ", '"; 
        sqlQuery += e.getEmployUsername()+ "', '"; 
        sqlQuery += e.getEmployPassword()+ "', ";         
        sqlQuery += e.getEmployStatus()+ ")";

        sendDBCommand(sqlQuery);        
      } 
    }
    
    public int getEmployID() {
        return employID;
    }

    public void setEmployID(int employID) {
        this.employID = employID;
    }

    public String getEmployFirstName() {
        return employFirstName;
    }

    public void setEmployFirstName(String employFirstName) {
        this.employFirstName = employFirstName;
    }

    public String getEmployLastName() {
        return employLastName;
    }

    public void setEmployLastName(String employLastName) {
        this.employLastName = employLastName;
    }

    public String getEmployPhone() {
        return employPhone;
    }

    public void setEmployPhone(String employPhone) {
        this.employPhone = employPhone;
    }

    public double getEmployPayrate() {
        return employPayrate;
    }

    public void setEmployPayrate(double employPayrate) {
        this.employPayrate = employPayrate;
    } 

    public String getEmployUsername() {
        return employUsername;
    }

    public void setEmployUsername(String employUsername) {
        this.employUsername = employUsername;
    }

    public String getEmployPassword() {
        return employPassword;
    }

    public void setEmployPassword(String employPassword) {
        this.employPassword = employPassword;
    }

    public int getEmployStatus() {
        return employStatus;
    }

    public void setEmployStatus(int employStatus) {
        this.employStatus = employStatus;
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
