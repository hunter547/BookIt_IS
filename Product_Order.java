package BookIt_IS;

import java.util.Date;


public class Product_Order {
    private int poID; 
    private static int nextID = 0;
    private Date poDate; 
    private int suppID; 
    private int salespID; 
    private int storeID; 
    private double poTotal; 

    public Product_Order() {
        this.poID = 0;
        this.poDate = new Date();
        this.suppID = 0;
        this.salespID = 0;
        this.storeID = 0;
        this.poTotal = 0.0; 
        
    } 

    public Product_Order(Date poDate, int suppID, int salespID, int storeID, double poTotal) {
        this.poID = ++ nextID;
        this.poDate = poDate;
        this.suppID = suppID;
        this.salespID = salespID;
        this.storeID = storeID;
        this.poTotal = poTotal;
    }

    public Product_Order(int poID, Date poDate, int suppID, int salespID, int storeID, double poTotal) {
        this.poID = poID;
        this.poDate = poDate;
        this.suppID = suppID;
        this.salespID = salespID;
        this.storeID = storeID;
        this.poTotal = poTotal; 
        if(poID>nextID){ 
            nextID = poID;
        }
    }
    

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
