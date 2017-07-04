package BookIt_IS;

import java.util.Date;


public class Time_Management {
    private int timeID; 
    private static int nextID = 0;
    private int storeID; 
    private int employID; 
    private Date timeIn; 
    private Date timeOut;  

    public Time_Management() {
        this.timeID = 0;
        this.storeID = 0;
        this.employID = 0;
        this.timeIn = new Date();
        this.timeOut = new Date();
    } 

    public Time_Management(int storeID, int employID, Date timeIn, Date timeOut) {
        this.timeID = ++nextID;
        this.storeID = storeID;
        this.employID = employID;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public Time_Management(int timeID, int storeID, int employID, Date timeIn, Date timeOut) {
        this.timeID = timeID;
        this.storeID = storeID;
        this.employID = employID;
        this.timeIn = timeIn;
        this.timeOut = timeOut; 
        if(timeID>nextID){ 
            nextID = timeID;
        }
    }
    
    public int getTimeID() {
        return timeID;
    }

    public void setTimeID(int timeID) {
        this.timeID = timeID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getEmployID() {
        return employID;
    }

    public void setEmployID(int employID) {
        this.employID = employID;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }
    
}
