package BookIt_IS;

import java.util.Date;


public class Account_Transaction {
    private int tranID; 
    private Date tranDate; 
    private double tranAmount; 
    private char tranIdentifier;  
    
    public Account_Transaction(){ 
        tranID = 0; 
        tranDate = new Date(); 
        tranAmount = 0.0; 
        tranIdentifier = ' ';             
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
