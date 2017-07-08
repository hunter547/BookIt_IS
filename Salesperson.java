/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookIt_IS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;


public class Salesperson {
    private static ArrayList<Salesperson> salespArray = new ArrayList<>(); 
    private static int nextID = 0;
    private int salespID; 
    private int suppID; 
    private String salespFirstName; 
    private String salespLastName; 
    private String salespPhone; 
    private String salespEmail; 
    private static Connection dbConn;
    private static Statement commStmt;
    private static ResultSet dbResults; 
    
    public Salesperson(){ 
        this.salespID = 0; 
        this.suppID= 0; 
        this.salespFirstName = ""; 
        this.salespLastName = ""; 
        this.salespPhone = ""; 
        this.salespEmail="";
        
    } 
    
    public Salesperson(int suppID, String salespFirstName, String salespLastName, 
                       String salespPhone, String salespEmail){ 
        this.suppID = suppID; 
        this.salespFirstName = salespFirstName; 
        this.salespLastName = salespLastName; 
        this.salespPhone = salespPhone; 
        this.salespEmail = salespEmail; 
        this.salespID = ++nextID;
        
    } 
    public Salesperson(int salespID, int suppID, String salespFirstName, String salespLastName, 
                       String salespPhone, String salespEmail){ 
        this.suppID = suppID; 
        this.salespFirstName = salespFirstName; 
        this.salespLastName = salespLastName; 
        this.salespPhone = salespPhone; 
        this.salespEmail = salespEmail; 
        this.salespID = salespID; 
        if(salespID>nextID){ 
            nextID = salespID;
        }
        
    }
    public static void insertAllSalespersons() { 
       for(Salesperson s: salespArray)
       {  
        String sqlQuery = "";
        sqlQuery += "INSERT INTO JAVAUSER.SALESPERSON (SALESPID, SUPPID, SALESPFIRSTNAME, SALESPLASTNAME, SALESPPHONE, SALESPEMAIL) VALUES (";
        sqlQuery += s.getSalespID() + ", "; 
        sqlQuery += s.getSuppID() + ", '";
        sqlQuery += s.getSalespFirstName()+ "', '";
        sqlQuery += s.getSalespLastName() + "', '"; 
        sqlQuery += s.getSalespPhone() + "', '"; 
        sqlQuery += s.getSalespEmail() + "')";

        sendDBCommand(sqlQuery);        
      }  
    }   
    
    public static void fillSalespersonArray() { 
       String sqlQuery = "";
       sqlQuery = "SELECT * FROM JAVAUSER.SALESPERSON";
       sendDBCommand(sqlQuery);

        try {
            while (dbResults.next()) {

                Salesperson.newSalespersonfromDatabase(dbResults.getInt(1),
                                                       dbResults.getInt(2), 
                                                       dbResults.getString(3), 
                                                       dbResults.getString(4), 
                                                       dbResults.getString(5), 
                                                       dbResults.getString(6));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
    } 
    
    public static void clearSalespersonTable() {
        String sqlQuery = "";
        sqlQuery = "DELETE FROM JAVAUSER.SALESPERSON";
        sendDBCommand(sqlQuery);
    }
    
    public static void newSalespersonfromDatabase(int salespID, int suppID, String salespFirstName, String salespLastName, 
                       String salespPhone, String salespEmail){ 
        salespArray.add(new Salesperson(salespID,suppID,salespFirstName,salespLastName,salespPhone,salespEmail));
    } 
    
    public static void newSalesperson(int suppID, String salespFirstName, String salespLastName, 
                       String salespPhone, String salespEmail){ 
        salespArray.add(new Salesperson(suppID,salespFirstName,salespLastName,salespPhone,salespEmail));
    }
    
    public int getSalespID(){ 
        return this.salespID;
    } 
    
    public int getSuppID(){ 
        return this.suppID;
    }
    
    public String getSalespFirstName(){ 
        return this.salespFirstName;
    } 
    
    public String getSalespLastName(){ 
        return this.salespLastName;
    } 
    
    public String getSalespPhone(){ 
        return this.salespPhone;
    } 
    
    public String getSalespEmail(){ 
        return this.salespEmail;
    } 
    
    public static ArrayList getSalespersonArray(){ 
        return salespArray;
    }
    
    public void setSalespID(int salespID){ 
        this.salespID = salespID;
    } 
    
    public void setSuppID(int suppID){ 
        this.suppID = suppID;
    } 
    
    public void setSalespFirstName(String firstName){ 
        this.salespFirstName = firstName;
    } 
    
    public void setSalespLastName(String lastName){ 
        this.salespLastName = lastName;
    } 
    
    public void setSalespPhone(String phone){ 
        this.salespPhone = phone;
    } 
    
    public void setSalespEmail(String email){ 
        this.salespEmail = email;
    } 
    
    public static ArrayList getSalespeopleForSupplier(int suppID){ 
        ArrayList<Salesperson> salespForSalesperson = new ArrayList<>(); 
        for(Salesperson s: salespArray){ 
            if(s.getSuppID() == suppID){ 
                salespForSalesperson.add(s);
            }
        } 
        return salespForSalesperson;
    } 
    
    public static void modifySalesperson(int suppID,String firstName, String lastName,
                                    String phone,String email,int salespID){ 
        for(Salesperson s: salespArray){ 
            if(s.getSalespID()==salespID){ 
                s.setSuppID(suppID);
                s.setSalespFirstName(firstName); 
                s.setSalespLastName(lastName); 
                s.setSalespPhone(phone); 
                s.setSalespEmail(email);
            }
        }
    }  
    public static Salesperson getSalesperson(int salespID){ 
        Salesperson bob = new Salesperson();
        for(Salesperson s: salespArray){ 
            if(s.getSalespID()==salespID){ 
                bob = s;
            }
        } 
        return bob;
    } 
    
    public static void removeSalesperson(int salespID){ 
        ArrayList<Salesperson> referenceSalespArray = salespArray;
        int iterator = 0;
        int remove = 0;
        for(Salesperson s: referenceSalespArray)
        { 
            if(s.getSalespID()==salespID){ 
                remove = iterator;
            } 
            iterator++;
        } 
        salespArray.remove(remove);
    } 
    public static String getSalespFirstName(int salespID){ 
        String s = ""; 
        for(Salesperson sa: salespArray){ 
            if(sa.getSalespID()==salespID){ 
                s = sa.getSalespFirstName();
            }
        } 
        return s;
    } 
    public static String getSalespLastName(int salespID){ 
        String s = ""; 
        for(Salesperson sa: salespArray){ 
            if(sa.getSalespID()==salespID){ 
                s = sa.getSalespLastName();
            }
        } 
        return s;
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
