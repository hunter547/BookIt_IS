package BookIt_IS;

public class Employee {
    private int employID; 
    private static int nextID = 0;
    private String employFirstName; 
    private String employLastName; 
    private String employPhone; 
    private double employPayrate; 
    
    public Employee(){ 
        this.employID=0; 
        this.employFirstName=""; 
        this.employLastName=""; 
        this.employPhone=""; 
        this.employPayrate=0.0;
    } 

    public Employee(String employFirstName, String employLastName, String employPhone, double employPayrate) {
        this.employID = ++ nextID;
        this.employFirstName = employFirstName;
        this.employLastName = employLastName;
        this.employPhone = employPhone;
        this.employPayrate = employPayrate;
    } 

    public Employee(int employID, String employFirstName, String employLastName, String employPhone, double employPayrate) {
        this.employID = employID;
        this.employFirstName = employFirstName;
        this.employLastName = employLastName;
        this.employPhone = employPhone;
        this.employPayrate = employPayrate; 
        if(employID>nextID){ 
            nextID = employID;
        }
    }
    
    
    
    

    public int getEmployID() {
        return employID;
    }

    public void setEmployID(int employID) {
        this.employID = employID;
    }

    public String getEmployFirstName() {
        return employFirstName;
    }

    public void setEmployFirstName(String employFirstName) {
        this.employFirstName = employFirstName;
    }

    public String getEmployLastName() {
        return employLastName;
    }

    public void setEmployLastName(String employLastName) {
        this.employLastName = employLastName;
    }

    public String getEmployPhone() {
        return employPhone;
    }

    public void setEmployPhone(String employPhone) {
        this.employPhone = employPhone;
    }

    public double getEmployPayrate() {
        return employPayrate;
    }

    public void setEmployPayrate(double employPayrate) {
        this.employPayrate = employPayrate;
    }    
}
