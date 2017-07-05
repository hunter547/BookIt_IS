package BookIt_IS;

import java.util.Date;


public class POS {
    private int posID; 
    private static int nextID = 0;
    private int storeID; 
    private int custID; 
    private Date posDate; 

    public POS() {
        this.posID = 0;
        this.storeID = 0;
        this.custID = 0;
        this.posDate = new Date();
    } 

    public POS(int storeID, int custID, Date posDate) {
        this.posID = ++nextID;
        this.storeID = storeID;
        this.custID = custID;
        this.posDate = posDate;
    } 

    public POS(int posID, int storeID, int custID, Date posDate) {
        this.posID = posID;
        this.storeID = storeID;
        this.custID = custID;
        this.posDate = posDate; 
        if(posID>nextID){ 
            nextID = posID;
        }
    }
    

    public int getPosID() {
        return posID;
    }

    public void setPosID(int posID) {
        this.posID = posID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public Date getPosDate() {
        return posDate;
    }

    public void setPosDate(Date posDate) {
        this.posDate = posDate;
    }
    
}
