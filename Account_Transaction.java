package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import oracle.jdbc.pool.OracleDataSource;

public class Account_Transaction {
    private static ArrayList<Account_Transaction> acctTranArray = new ArrayList<>(); 
    private int tranID;  
    private static int nextID = 0;
    private Date tranDate; 
    private double tranAmount; 
    private char tranIdentifier;  
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;
    
    public Account_Transaction(){ 
        tranID = 0; 
        tranDate = new Date(); 
        tranAmount = 0.0; 
        tranIdentifier = ' ';             
    } 
    
    // Used for Database
    public Account_Transaction(int tranID, Date tranDate, double tranAmount, char tranIdentifier){ 
        this.tranID= tranID; 
        this.tranDate = tranDate; 
        this.tranAmount = tranAmount; 
        this.tranIdentifier = tranIdentifier;   
        if(tranID>nextID){ 
            nextID=tranID;
        }
    } 
    
    // Used to create new account_transaction
    public Account_Transaction(Date tranDate, double tranAmount, char tranIdentifier){ 
        this.tranID= ++nextID; 
        this.tranDate = tranDate; 
        this.tranAmount = tranAmount; 
        this.tranIdentifier = tranIdentifier;             
    } 
    
    public static void newAcctTransactionFromDatabase(int tranID,Date tranDate,double tranAmount,char tranIdentifier){ 
        acctTranArray.add(
                new Account_Transaction(tranID,tranDate,tranAmount,tranIdentifier));
    } 
    
    public static void newAcctTransation(Date tranDate, double tranAmount, char tranIdentifier){ 
        acctTranArray.add(
                new Account_Transaction(tranDate,tranAmount,tranIdentifier));
    } 
    
    public static void fillAcctTransactionArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.ACCOUNT_TRANSACTION";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Account_Transaction.newAcctTransactionFromDatabase(
                                    dbResults.getInt(1),
                                    dbResults.getDate(2), 
                                    dbResults.getDouble(3), 
                                    (Character) dbResults.getObject(4));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    }  
    
    public static void clearAcctTransactionTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.ACCOUNT_TRANSACTION";
        sendDBCommand(sqlQuery);
    }  
    
    public static void insertAllAcctTransactions() { 
       for(Account_Transaction at: acctTranArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.ACCOUNT_TRANSACTION (TRANID, TRANDATE, AMOUNT, IDENTIFIER) VALUES (";
        sqlQuery += at.getTranID()+ " , '";
        sqlQuery += at.getTranDate()+ "', ";
        sqlQuery += at.getTranAmount()+ ", '"; 
        sqlQuery += at.getTranIdentifier()+ "')";

        sendDBCommand(sqlQuery);        
      } 
    } 

    public int getTranID() {
        return tranID;
    }

    public void setTranID(int tranID) {
        this.tranID = tranID;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public double getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(double tranAmount) {
        this.tranAmount = tranAmount;
    }

    public char getTranIdentifier() {
        return tranIdentifier;
    }

    public void setTranIdentifier(char tranIdentifier) {
        this.tranIdentifier = tranIdentifier;
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
