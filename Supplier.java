package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;


public class Supplier {
    private static ArrayList<Supplier> suppArray = new ArrayList<>();  
    private static int nextID = 0; 
    private int suppID; 
    private String suppName; 
    private String suppAddress; 
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults;    
    
    public Supplier(){ 
        suppID = 0; 
        suppName = ""; 
        suppAddress = ""; 

    } 
    
    public Supplier(String suppName,String suppAddress) { 
        this.suppName = suppName; 
        this.suppAddress = suppAddress; 
        this.suppID = ++nextID;
    } 
    
    public Supplier(int suppID,String suppName,String suppAddress) { 
        this.suppName = suppName; 
        this.suppAddress = suppAddress; 
        this.suppID = suppID; 
        if(suppID>nextID){ 
            nextID = suppID;
        }
    }
    
    
    public static void newSupplier(String suppName,String suppAddress) 
    { 
        suppArray.add(new Supplier(suppName,suppAddress));
    }  
    
    public static void newSupplierfromDatabase(int suppID,String suppName,String suppAddress) 
    { 
        suppArray.add(new Supplier(suppID,suppName,suppAddress));
    }
    
    
    
    public static void fillSupplierArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.SUPPLIER";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Supplier.newSupplierfromDatabase(
                                    dbResults.getInt(1), 
                                    dbResults.getString(2), 
                                    dbResults.getString(3));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void modifySupplier(String suppName, String suppAddress,
                                      int suppID){ 
        for(Supplier s: suppArray){ 
            if(s.getSuppID()==suppID){ 
                s.setSuppName(suppName); 
                s.setSuppAddress(suppAddress); 
            }
        }
    }
    
    public static Supplier getSupplier(int suppID){ 
        Supplier bob = new Supplier();
        for(Supplier s: suppArray){ 
            if(s.getSuppID()==suppID){ 
                bob = s;
            }
        } 
        return bob;
    } 
    
    public static void clearSupplierTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.SUPPLIER";
        sendDBCommand(sqlQuery);
    } 
    
    public static void insertAllSuppliers() { 
       for(Supplier s: suppArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.SUPPLIER (SUPPID, SUPPNAME, SUPPADDRESS) VALUES (";
        sqlQuery += s.getSuppID() + " , '";
        sqlQuery += s.getSuppName()+ "', '";
        sqlQuery += s.getSuppAddress() + "')"; 

        sendDBCommand(sqlQuery);        
      } 
    } 
    
    public static void removeSupplier(int suppID){ 
        ArrayList<Supplier> referenceSuppArray = suppArray;
        int remove = 0;
        int iterator = 0;
        for(Supplier t: referenceSuppArray)
        { 
            if(t.getSuppID()==suppID){ 
                remove = iterator;
            } 
            iterator++;
        } 
        suppArray.remove(remove);
    }
    
    public int getSuppID() { 
        return this.suppID;
    } 
    
    public String getSuppName() { 
        return this.suppName;
    } 
    public static String getSuppName(int suppID) { 
        String s = ""; 
        for(Supplier su: suppArray){ 
            if(su.getSuppID()==suppID){ 
                s = su.getSuppName();
            }
        } 
        return s;
    }
    
    public String getSuppAddress() { 
        return this.suppAddress;
    } 
    

    
    public static ArrayList getSupplierArray(){ 
        return suppArray;
    }
    
    public void setSuppName(String suppName){ 
        this.suppName = suppName;
    } 
    public void setSuppAddress(String suppAddress){ 
        this.suppAddress = suppAddress;
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

