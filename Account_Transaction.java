package BookIt_IS;

import java.util.Date;


public class Account_Transaction {
    private int tranID;  
    private static int nextID = 0;
    private Date tranDate; 
    private double tranAmount; 
    private char tranIdentifier;  
    
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
    
    
    
}
