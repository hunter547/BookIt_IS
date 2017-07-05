package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;


public class Customer {
    private static ArrayList<Customer> custArray = new ArrayList<>(); 
    private static int nextID = 0; 
    private int custID;
    private String custFirstName; 
    private String custLastName; 
    private String custPhone; 
    private String custAddress; 
    private String custEmail;
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;
    
    // Default constructors
    public Customer() { 
        custID = 0;
        custFirstName = ""; 
        custLastName = ""; 
        custPhone = ""; 
        custAddress = "";  
        custEmail = "";
    } 
    
    public Customer(String custFirstName, String custLastName, 
                    String custPhone, String custAddress, String custEmail) 
    {  
        this.custID = ++nextID; 
        this.custFirstName = custFirstName; 
        this.custLastName = custLastName; 
        this.custPhone = custPhone; 
        this.custAddress = custAddress; 
        this.custEmail = custEmail;
    } 
    
    public Customer(int custID,String custFirstName, String custLastName, 
                    String custPhone, String custAddress, String custEmail) 
    {  
        this.custID = custID; 
        this.custFirstName = custFirstName; 
        this.custLastName = custLastName; 
        this.custPhone = custPhone; 
        this.custAddress = custAddress; 
        this.custEmail = custEmail;
        if(custID>nextID){ 
            nextID = custID;
        }
    }  
    
    public static void newCustomer(String custFirstName, String custLastName, 
                    String custPhone, String custAddress, String custEmail) 
    {  
        custArray.add
        (new Customer(custFirstName,custLastName,custPhone,custAddress,custEmail));
    } 
    
    public static void newCustomerFromDatabase(int custID, String custFirstName, String custLastName, 
                    String custPhone, String custAddress, String custEmail) 
    {  
        custArray.add
        (new Customer(custID,custFirstName,custLastName,custPhone,custAddress,custEmail));
    }

    public static void fillCustomerArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.CUSTOMER";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Customer.newCustomerFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getString(2), 
                                    dbResults.getString(3), 
                                    dbResults.getString(4), 
                                    dbResults.getString(5),
                                    dbResults.getString(6));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void clearCustomerTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.CUSTOMER";
        sendDBCommand(sqlQuery);
    } 
    
    public static void insertAllCustomers() { 
       for(Customer c: custArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.CUSTOMER (CUSTID, CUSTFIRSTNAME, CUSTLASTNAME, CUSTPHONE, CUSTADDRESS, CUSTEMAIL) VALUES (";
        sqlQuery += c.getCustID() + " , '";
        sqlQuery += c.getCustFirstName() + "', '";
        sqlQuery += c.getCustLastName() + "', '"; 
        sqlQuery += c.getCustPhone() + "', '";
        sqlQuery += c.getCustAddress() + "', '";
        sqlQuery += c.getCustEmail() + "')";

        sendDBCommand(sqlQuery);        
      } 
    }   

            
    public static ArrayList getCustArray(){ 
        return custArray;
    }
    public int getCustID() { 
        return this.custID;
    }
    
    public String getCustFirstName() { 
        return this.custFirstName;
    } 
    
    public String getCustLastName() { 
        return this.custLastName;
    }   
    
    public String getCustPhone() { 
        return this.custPhone;
    } 

    public String getCustAddress() { 
        return this.custAddress;
    }  
    
    public static String getCustFirstName(int custID){ 
        String s = ""; 
        for(Customer c: custArray){ 
            if(c.getCustID()==custID){ 
                s = c.getCustFirstName();
            }
        } 
        return s;
    } 
    
    public static String getCustLastName(int custID){ 
        String s = ""; 
        for(Customer c: custArray){ 
            if(c.getCustID()==custID){ 
                s = c.getCustLastName();
            }
        } 
        return s;
    } 
    
    public static Customer getCustomer(int custID){ 
        Customer bob = new Customer(); 
        for(Customer c: custArray){ 
            if(c.getCustID()==custID){ 
                bob = c;
            }
        } 
        return bob;
    } 
    
    public static void modifyCustomer(String custFirstName, String custLastName, 
                    String custPhone, String custAddress, int custID){ 
        for(Customer c: custArray){ 
            if(c.getCustID()==custID){ 
                c.setCustFirstName(custFirstName); 
                c.setCustLastName(custLastName); 
                c.setCustPhone(custPhone); 
                c.setCustAddress(custAddress);
            }
        }
    } 
    
    public static void removeCustomer(int custID){ 
        ArrayList<Customer> referenceCustArray = custArray;
        int iterator = 0;
        int remove = 0;
        for(Customer c: referenceCustArray)
        { 
            if(c.getCustID()==custID){ 
                remove = iterator;
            } 
            iterator++;
        } 
        custArray.remove(remove);
    }
    
    public void setCustFirstName(String custFirstName){ 
        this.custFirstName = custFirstName;
    } 
    
    public void setCustLastName(String custLastName){ 
        this.custLastName = custLastName;
    } 

    public void setCustPhone(String custPhone){ 
        this.custPhone = custPhone;
    } 

    public void setCustAddress(String custAddress){ 
        this.custAddress = custAddress;
    } 

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
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
