package BookIt_IS;

import java.util.Date;


public class Product_Order {
    private int poID; 
    private Date poDate; 
    private int suppID; 
    private int salespID; 
    private int storeID; 
    private double poTotal; 

    public int getPoID() {
        return poID;
    }

    public void setPoID(int poID) {
        this.poID = poID;
    }

    public Date getPoDate() {
        return poDate;
    }

    public void setPoDate(Date poDate) {
        this.poDate = poDate;
    }

    public int getSuppID() {
        return suppID;
    }

    public void setSuppID(int suppID) {
        this.suppID = suppID;
    }

    public int getSalespID() {
        return salespID;
    }

    public void setSalespID(int salespID) {
        this.salespID = salespID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public double getPoTotal() {
        return poTotal;
    }

    public void setPoTotal(double poTotal) {
        this.poTotal = poTotal;
    }
    
}
