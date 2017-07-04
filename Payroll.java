package BookIt_IS;


public class Payroll {
    private int payID; 
    private static int nextID = 0;
    private int employID; 
    private int storeID; 
    private double payHoursWorked; 
    private double payTotal; 

    public Payroll() {
        this.payID = 0;
        this.employID = 0;
        this.storeID = 0;
        this.payHoursWorked = 0;
        this.payTotal = 0;
    }

    public Payroll(int employID, int storeID, double payHoursWorked, double payTotal) {
        this.payID = ++nextID;
        this.employID = employID;
        this.storeID = storeID;
        this.payHoursWorked = payHoursWorked;
        this.payTotal = payTotal;
    }

    public Payroll(int payID, int employID, int storeID, double payHoursWorked, double payTotal) {
        this.payID = payID;
        this.employID = employID;
        this.storeID = storeID;
        this.payHoursWorked = payHoursWorked;
        this.payTotal = payTotal; 
        if(payID>nextID){ 
            nextID = payID;
        }
    }
    

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
