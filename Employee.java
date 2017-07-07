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
    private int storeID;
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
        this.storeID = 0;
    } 

    public Employee(String employFirstName, String employLastName, 
            String employPhone, double employPayrate, String employUsername, 
            String employPassword, int employStatus, int storeID) {
        this.employID = ++ nextID;
        this.employFirstName = employFirstName;
        this.employLastName = employLastName;
        this.employPhone = employPhone;
        this.employPayrate = employPayrate; 
        this.employUsername = employUsername;
        this.employPassword = employPassword;
        this.employStatus = employStatus; 
        this.storeID = storeID;
    } 

    public Employee(int employID, String employFirstName, String employLastName, 
            String employPhone, double employPayrate, String employUsername, 
            String employPassword, int employStatus, int storeID) {
        this.employID = employID;
        this.employFirstName = employFirstName;
        this.employLastName = employLastName;
        this.employPhone = employPhone;
        this.employPayrate = employPayrate;
        this.employUsername = employUsername;
        this.employPassword = employPassword;
        this.employStatus = employStatus; 
        this.storeID = storeID;
        if(employID>nextID){ 
            nextID = employID;
        }
    }

         
    public static void newEmployeeFromDatabase(int employID, String employFirstName, 
            String employLastName, String employPhone, double employPayrate, 
            String employUsername, String employPassword, int employStatus,int storeID){ 
        employArray.add(new Employee(employID,employFirstName,employLastName,
            employPhone,employPayrate,employUsername,employPassword,employStatus,storeID));
    } 
    
    public static void newEmployee(String employFirstName, String employLastName, 
            String employPhone, double employPayrate, String employUsername, 
            String employPassword, int employStatus,int storeID){ 
        employArray.add(new Employee(employFirstName,employLastName,
            employPhone,employPayrate,employUsername,employPassword,employStatus, storeID));    
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
                                    dbResults.getInt(8),
                                    dbResults.getInt(9));
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
                + "EMPLOYPASSWORD, EMPLOYSTATUS, STOREID) VALUES (";
        sqlQuery += e.getEmployID()+ " , '";
        sqlQuery += e.getEmployFirstName()+ "', '";
        sqlQuery += e.getEmployLastName()+ "', '"; 
        sqlQuery += e.getEmployPhone()+ "', ";
        sqlQuery += e.getEmployPayrate()+ ", '"; 
        sqlQuery += e.getEmployUsername()+ "', '"; 
        sqlQuery += e.getEmployPassword()+ "', ";   
        sqlQuery += e.getEmployStatus()+ ", "; 
        sqlQuery += e.getStoreID()+ ")";

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

    public static ArrayList<Employee> getEmployArray() {
        return employArray;
    }  

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
    
    public static int getEmployID(String username){ 
        int employID = 0; 
        for(Employee e: employArray){ 
            if(e.getEmployUsername().matches(username)){ 
                employID = e.getEmployID();
            }
        } 
        return employID;
    } 
    
    public static int getStoreID(String username){ 
        int storeID = 0; 
        for(Employee e: employArray){ 
            if(e.getEmployUsername().matches(username)){ 
                storeID = e.getStoreID();
            }
        } 
        return storeID;
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
    
    public static boolean checkCredentials(String username, String password){ 
        boolean valid = false; 
        for(Employee e:employArray){ 
            if(e.getEmployUsername().matches(username)){ 
                if(e.getEmployPassword().matches(password)){ 
                    valid = true;
                }
            }
        } 
        return valid;
    }
}
