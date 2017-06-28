package BookIt_IS;


public class Payroll {
    private int payID; 
    private int employID; 
    private int storeID; 
    private double payHoursWorked; 
    private double payTotal; 

    public int getPayID() {
        return payID;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public int getEmployID() {
        return employID;
    }

    public void setEmployID(int employID) {
        this.employID = employID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public double getPayHoursWorked() {
        return payHoursWorked;
    }

    public void setPayHoursWorked(double payHoursWorked) {
        this.payHoursWorked = payHoursWorked;
    }

    public double getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(double payTotal) {
        this.payTotal = payTotal;
    }
    
}
