package BookIt_IS;

public class Employee {
    private int employID; 
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

    public String getEmployPayrate() {
        return employPayrate;
    }

    public void setEmployPayrate(String employPayrate) {
        this.employPayrate = employPayrate;
    }
    // Cole's Test
    
}
